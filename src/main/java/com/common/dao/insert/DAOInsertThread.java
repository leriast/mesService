package com.common.dao.insert;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by root on 11/11/16.
 */

@Repository
@Transactional
public class DAOInsertThread extends Thread /*implements IDAOInsertThread*/ {
    Logger logger = Logger.getLogger(String.valueOf(DAOInsertThread.class));
    ArrayList<Message> list = new ArrayList<>();
    Session session;
    private SessionFactory sessionFactory;
    InsertQueue queue = new InsertQueue();
    Message task = new Message();

    public DAOInsertThread() {
    }

    public DAOInsertThread(SessionFactory sf) {
        this.sessionFactory = sf;
    }
    public void run() {
    //    System.out.println("DAOInsertThread");
        while (true) {
            try {
                task= (Message) queue.getMainQueue().take();
                list.add(task);
                try {
                    if (list.size() == 10000) {
                        try {
                    //        System.out.println("DAOInsertThread!!");
                            session = sessionFactory.getCurrentSession();
                        } catch (HibernateException e) {
                            while (sessionFactory.getStatistics().getSessionOpenCount() > 70) {
                                logger.info("to many DB sessions");
                            }
                            session = sessionFactory.openSession();
                        }
                        try {
                            for (int i = 0; i < list.size(); i++) {
                                task = list.get(i);
                                if (task != null) {
                                    session.update(task);
                                }
                            }
                            session.getTransaction().begin();
                            session.getTransaction().commit();
                            list.clear();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                        try {
                            session.beginTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            session.getTransaction().commit();
                            session.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (queue.getMainQueue().size() == 0) {
                     //   System.out.println("DAOInsertThread!!");
                         try {
                            session = sessionFactory.getCurrentSession();
                        } catch (HibernateException e) {
                            session = sessionFactory.openSession();
                        }
                        for (int i = 0; i < list.size(); i++) {
                            task = list.get(i);
                            if (task != null) {session.update(task);
                               }
                        }
                        session.getTransaction().begin();
                        session.getTransaction().commit();
                        list.clear();
                        try {
                            session.beginTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            session.getTransaction().commit();
                            session.close();
                      //      System.out.println("end   "+new Date());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (
                    InterruptedException e)

            {
                e.printStackTrace();
            }
        }
    }
}