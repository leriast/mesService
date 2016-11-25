package com.common.service.workingThread;


import com.common.dao.entity.queue.Queue;
import com.common.dao.insert.DAOInsertIncomingThread;
import com.common.dao.insert.DAOInsertThread;
import org.hibernate.SessionFactory;
import org.quartz.SchedulerException;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.ArrayList;
import java.util.logging.Logger;

/*
*
*
*
* */
public class Pool {

    /*public Pool() {
        if (Thread.activeCount() < 28) Start();
    }*/

    Logger logger = Logger.getLogger(String.valueOf(Pool.class));
    private SessionFactory sessionFactory;
    private int poolSize = 8;
    private AmqpTemplate template;

    public Pool(SessionFactory sessionFactory, int poolSize,AmqpTemplate template) {
        this.sessionFactory = sessionFactory;
        this.poolSize = poolSize;
        this.template=template;
        if (Thread.activeCount() < 21) Start();                 //spring initializer start all annotated classes twice

    }

    static ArrayList<Thread> list = new ArrayList<Thread>();
    public Queue queue = new Queue();

    public void Start() {
        System.out.println("Pool start");
        for (int someVarible = 0; someVarible < poolSize; someVarible++) {
            try {
                new ThreadConsumer(template,queue, "Thread number " + someVarible).start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }


        /*
        *
        * */
        new DAOInsertThread(sessionFactory).start();
        new DAOInsertThread(sessionFactory).start();
        new GetThread(sessionFactory).start();
        new ZeroPriorityThread().start();
        new ClearQueueThread().start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
        new DAOInsertIncomingThread(sessionFactory).start();
    }
}