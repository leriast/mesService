package com.common.service.workingThread;

import com.common.dao.entity.journal.Journal;
import org.hibernate.*;
import org.hibernate.criterion.Restrictions;

import java.util.Date;

/**
 * Created by root on 12/23/16.
 */
public class JournalWorker extends Thread {
    private SessionFactory sessionFactory;

    public JournalWorker() {
    }

    public JournalWorker(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    Session session;

    public void run() {
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }

        while (true) {
            Journal journal = new Journal();
            Criteria criteria = session.createCriteria(Journal.class);
            criteria.add(Restrictions.lt("time", new Date()));
            criteria.add(Restrictions.eq("status", 0));
            if (!criteria.list().isEmpty()) {
                Journal journal1Result = (Journal) criteria.list().get(0);
                SQLQuery query = session.createSQLQuery("update message set priority=" + journal1Result.getPriority() + " where idmessage<>" + journal1Result.getTaskId());
                query.executeUpdate();
                session.delete(journal1Result);
                session.getTransaction().begin();
                session.getTransaction().commit();
                System.out.println(journal1Result.getTaskId() + "        journal     " + journal1Result.getTime());
            }else {
                try {
                    sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
