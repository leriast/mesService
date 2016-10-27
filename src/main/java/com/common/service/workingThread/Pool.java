package com.common.service.workingThread;


import com.common.dao.entity.queue.Queue;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.quartz.SchedulerException;

import java.util.ArrayList;

/**
 * Created by root on 10/20/16.
 */

//    @Aspect
public class Pool {
  //    public static final Pool INSTANCE = new Pool();
    static ArrayList<Thread> list = new ArrayList<Thread>();
public Queue queue;

    public Pool(Queue queue) {
        this.queue=queue;
        Start(queue);
    }

    //    @Pointcut("execution(* com.common.listener.RabbitMqListener.processQueue1(..))")
//   public void test(){
//   }
    public void Start(Queue queue) {
        queue.getMainQueue().comparator();
        for (int i = 0; i < 8; i++) {
            try {
                list.add(new Thread(new ThreadConsumer(queue, "Thread number " + i)));
            } catch (SchedulerException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < list.size(); i++) {
            list.get(i).start();
        }
    }
}