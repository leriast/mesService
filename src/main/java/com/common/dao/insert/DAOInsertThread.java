package com.common.dao.insert;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.message.SentMessage;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    Queue q = new Queue();
    Message task = new Message();

    public DAOInsertThread() {
    }

    public DAOInsertThread(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void run() {
        System.out.println("dapinsert " +currentThread().getName());
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        while (true) {
            try {
                task = queue.getMainQueue().take();

                list.add(task);
                try {
                    if (list.size() == 10000) {

//                        session.clear();
                        for (int i = 0; i < list.size(); i++) {
                            task = list.get(i);
                            if (task != null) {
                                if (task.getStatus() == 2) {
                                    SentMessage mess = new SentMessage(task);
                                    session.save(mess);
                                } else {
                                    session.update(task);
                                }
                            }
                        }
                        session.getTransaction().begin();
                        session.getTransaction().commit();
                        System.out.println("insert queue=  " + queue.getMainQueue().size() + "   before workers=   " + q.getMainQueue().size() + "    DAOInsertThread commit  " + new Date());
                        list.clear();
                        //        session.close();
                        session.clear();

                    } else if (queue.getMainQueue().size() == 0 && list.size() != 0) {
                        for (int i = 0; i < list.size(); i++) {
                            task = list.get(i);
                            if (task.getStatus() == 2) {
                                try {
                                    session.saveOrUpdate(new SentMessage(task));
                                } catch (Exception e) {
                                    System.err.println("error id= " + task.getIdMessage());
                                }
                            } else {
                                session.update(task);
                            }

                        }
                        session.getTransaction().begin();
                        list.clear();
                        try {
                            System.out.println("insert if queue=  " + queue.getMainQueue().size() + "   before workers=   " + q.getMainQueue().size() + "    DAOInsertThread commit  " + new Date());
                            session.getTransaction().commit();
                            session.clear();
                            sleep(1500);
                            // session.close();
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