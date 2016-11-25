package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.Queue;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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

    int x = 0;

    public void run() {
        while (true) /*while (x<10)*/ {
            if (queue.getMainQueue().size() < 500) {
                try {
                    session = sessionFactory.getCurrentSession();
                } catch (HibernateException e) {
                    while (sessionFactory.getStatistics().getSessionOpenCount() > 10) {
                    }
                    session = sessionFactory.openSession();
                }
                Criteria criteria = session.createCriteria(Message.class, "arr").add(Restrictions.eq("priority", 1)).setMaxResults(700);
              //  System.out.println(queue.getMainQueue().size() + "  SELECT   ");
                Message aa = new Message();
                a.clear();
                a = criteria.list();
                for (int i = 0; i < a.size(); i++) {
                    aa = (Message) a.get(i);
                    //  System.out.println(aa.getPriority());
                    if (aa != null) {
                  //         queue.getMainQueue().add(aa);

                    }
                }

                //}
                //  System.out.println(a.getUsername() + "   " + a.getPassword());
                x++;

//            try {
//                sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
                session.close();
            } /*else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }*/
        }
    }
}
