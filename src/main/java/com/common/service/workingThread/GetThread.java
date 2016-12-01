package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.Queue;
import org.hibernate.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by root on 11/21/16.
 */
@Repository
@Transactional
public class GetThread extends Thread {
    Session session;
    List a = new ArrayList<Message>();

    public GetThread() {
    }

    Queue queue = new Queue();
    private SessionFactory sessionFactory;

    public GetThread(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void run() {
        //    System.out.println("GetThread");
        while (true) /*while (x<10)*/ {
            if (queue.getMainQueue().size() < 1000) {
                System.out.println(new Date());
                //      System.out.println(true);
                try {
                    session = sessionFactory.getCurrentSession();
                } catch (HibernateException e) {
                    while (sessionFactory.getStatistics().getSessionOpenCount() > 10) {
                    }
                    try {
                        session = sessionFactory.openSession();
                        ;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }

                }
                List list = null;
                try {
                    Query query = session.createQuery("from Message where status = :code ");
                    query.setParameter("code", 1);
                    query.setMaxResults(1000);
                    list = query.list();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Message aa;


                if (list.size() != 0) {
                    for (Object object : list) {
                        aa = (Message) object;
                        aa.setStatus(3);
                        session.saveOrUpdate(aa);
                    }
                    session.getTransaction().begin();
                    session.getTransaction().commit();
                    for (Object aaa : list) {
                        if (aaa != null) {
                            queue.getMainQueue().add((Message) aaa);
                        }
                    }
                }
                //session.close();
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
