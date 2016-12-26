package com.common.dao.insert;

import com.common.dao.entity.message.Message;
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
        System.out.println("INCOMINGinsert " +currentThread().getName());
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        while (true) {
            try {
                task = (Message) queue.getMainQueue().take();
                list.add(task);
                try {
                    if (list.size() == 50000) {
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
                            System.out.println("queue=  " + queue.getMainQueue().size() + "    DAOInsertIncomingThread commit 20k " + new Date());

                            session.clear();

                            //  session.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    } else if (queue.getMainQueue().size() == 0) {
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
                            System.out.println("ueue=  " + queue.getMainQueue().size() + "    DAOInsertIncomingThread commit  " + new Date());
                            //  session.close();
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
