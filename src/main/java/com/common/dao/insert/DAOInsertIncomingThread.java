package com.common.dao.insert;

import com.common.dao.entity.JSONT;
import com.common.dao.entity.queue.IncomingInsertQueue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

/**
 * Created by root on 11/25/16.
 */
public class DAOInsertIncomingThread extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(DAOInsertThread.class));
    ArrayList<JSONT> list = new ArrayList<>();
    Session session;
    private SessionFactory sessionFactory;
    IncomingInsertQueue queue = new IncomingInsertQueue();
    JSONT task = new JSONT();

    public DAOInsertIncomingThread() {
    }

    public DAOInsertIncomingThread(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    public void run() {
        System.out.println("work");
        while (true) {
            try {
                session = sessionFactory.getCurrentSession();
            } catch (HibernateException e) {
                while (sessionFactory.getStatistics().getSessionOpenCount() > 50) {
                    logger.info("to many DB sessions");
                }
                session = sessionFactory.openSession();
            }
            try {
                task = (JSONT) queue.getMainQueue().take();
                list.add(task);
                try {
                    if (list.size() == 10000) {
                        System.out.println(list.size());
                        /*try {
                            session = sessionFactory.getCurrentSession();
                        } catch (HibernateException e) {
                            while (sessionFactory.getStatistics().getSessionOpenCount() > 50) {
                                logger.info("to many DB sessions");
                            }
                            session = sessionFactory.openSession();
                        }*/
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
                          //  session.close();
                            System.out.println(this +
                                    "    commit    " + new Date() + "           " + Thread.activeCount() + "           " + queue.getMainQueue().size());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (queue.getMainQueue().size() == 0) {
                        System.out.println(list.size());
                       /* try {
                            session = sessionFactory.getCurrentSession();
                        } catch (HibernateException e) {
                            session = sessionFactory.openSession();
                        }*/
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
                           // session.close();
                            System.out.println(this +
                                    "    commit    " + new Date() + "           " + Thread.activeCount() + "           " + queue.getMainQueue().size());
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