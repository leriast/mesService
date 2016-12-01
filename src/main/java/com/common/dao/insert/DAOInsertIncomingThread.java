package com.common.dao.insert;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.IncomingInsertQueue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * Created by root on 11/25/16.
 */
public class DAOInsertIncomingThread extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(DAOInsertThread.class));
    ArrayList<Message> list = new ArrayList<>();
    Session session;
    private SessionFactory sessionFactory;
    IncomingInsertQueue queue = new IncomingInsertQueue();
    Message task = new Message();

    public DAOInsertIncomingThread() {
    }

    public DAOInsertIncomingThread(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void run() {
     //   System.out.println("DAOInsertIncomingThread");
//        System.out.println("i`m start?   "+queue.getMainQueue().size());
        while (true) {
            try {
                session = sessionFactory.getCurrentSession();
            } catch (HibernateException e) {
                while (sessionFactory.getStatistics().getSessionOpenCount() > 70) {
                    logger.info("to many DB sessions");
                }
                session = sessionFactory.openSession();
            }
            try {
                task = (Message) queue.getMainQueue().take();
                list.add(task);
                try {
                    if (list.size() == 10000) {
                  //      System.out.println(list.size());


                        for (int i = 0; i < list.size(); i++) {
                            task = list.get(i);
                            if (task != null) {

                               session.save(task);

                                /* session.createSQLQuery("INSERT INTO jsont (info) VALUES ('" + task.getJson() + "'" +
                                        ");");*//*.executeUpdate();*/
                            }
                        }
                        list.clear();
                        try {

                            session.beginTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            session.getTransaction().commit();
                            session.close();
//                            System.out.println(this +
//                                    "    commit    " + new Date() + "           " + Thread.activeCount()+"/"+sessionFactory.getStatistics().getSessionOpenCount()+" " + "           " + queue.getMainQueue().size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (queue.getMainQueue().size() == 0) {
                    //    System.out.println(list.size());

                        for (int i = 0; i < list.size(); i++) {
                            task = list.get(i);
                            if (task != null) {
                                session.save(task);
                            }
                        }
                        list.clear();
                        try {
                            session.beginTransaction();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            session.getTransaction().commit();
                            session.close();
                          //  System.out.println(this +
                          //          "    commit    " + new Date() + "           " + Thread.activeCount()+"/"+sessionFactory.getStatistics().getSessionOpenCount()+" " + "           " + queue.getMainQueue().size());
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
