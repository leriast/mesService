package com.common.service.workingThread;


import com.common.dao.entity.DAOInsertThread;
import com.common.dao.entity.queue.Queue;
import org.hibernate.SessionFactory;
import org.quartz.SchedulerException;

import java.util.ArrayList;
import java.util.logging.Logger;


//@Component
//@Transactional
//@Repository
public class Pool {
    Logger logger = Logger.getLogger(String.valueOf(Pool.class));
    private SessionFactory sessionFactory;
    private int poolSize;
    public Pool(SessionFactory sessionFactory,int poolSize) {
        this.sessionFactory=sessionFactory;
        this.poolSize=poolSize;
        Start();
    }
    static ArrayList<Thread> list = new ArrayList<Thread>();
    public Queue queue=new Queue();

    public void Start() {
        for (int someVarible = 0; someVarible < poolSize; someVarible++) {
            try {
           //    list.add(new Thread(new ThreadConsumer(queue, "Thread number " + someVarible)));
                new Thread(new ThreadConsumer(queue, "Thread number " + someVarible)).start();
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }

        new Thread(new DAOInsertThread(sessionFactory)).start();
        new Thread(new DAOInsertThread(sessionFactory)).start();
    //    System.out.println(sessionFactory);
//        list.add(new ZeroPriorityThread());
//        list.add(new ClearQueueThread());
        /*checker.start();*/
    //   list.add(new DAOInsertThread());
//        for (int i = 0; i < list.size(); i++) {
//            try {
//                    list.get(i).start();
//                System.out.println(list.get(i).getName());
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        }
    }
}