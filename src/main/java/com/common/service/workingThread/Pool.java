package com.common.service.workingThread;


import com.common.dao.insert.DAOInsertIncomingThread;
import com.common.dao.insert.DAOInsertThread;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.logging.Logger;

/*
*
*
*
* */

public class Pool {
    public Pool() {
        if (Thread.activeCount() < 28) Start();
    }


    Logger logger = Logger.getLogger(String.valueOf(Pool.class));
    @Autowired
    private SessionFactory sessionFactory;
    private int poolSize;
    private int getThreadCount = 2;

    public Pool(SessionFactory sessionFactory, int poolSize) {
        this.sessionFactory = sessionFactory;
        this.poolSize = poolSize;
        System.out.println("now threads count= " + Thread.activeCount());
        if (Thread.activeCount() < 26) Start();                 //spring initializer start all annotated classes twice

    }


    public void Start() {
        System.out.println("Pool start    ");

        for (int i = 0; i < poolSize; i++) {
            new ThreadConsumer(sessionFactory).start();
        }

        new JournalWorker(sessionFactory).start();

        new DAOInsertThread(sessionFactory).start();
        new DAOInsertThread(sessionFactory).start();

        //  new DAOInsertThread(sessionFactory).start();
//        new DAOInsertThread(sessionFactory).start();

        for (int i = 0; i < getThreadCount; i++) {
            new GetThread(sessionFactory, i, getThreadCount).start();
        }

        //   new GetThread(sessionFactory,2).start();
        //   new GetThread(sessionFactory,2).start();
        //        new GetThread(sessionFactory,2).start();

        //  new ZeroPriorityThread().start();
        //   new ClearQueueThread().start();

        //     new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        //        new DAOInsertIncomingThread(sessionFactory).start();
    }
}