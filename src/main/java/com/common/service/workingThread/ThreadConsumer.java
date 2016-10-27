package com.common.service.workingThread;


import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.inside.Message;
import com.common.dao.entity.queue.Queue;
import com.common.service.quartz.CronExprGenerator;
import com.common.service.quartz.QuartzEx;
import com.common.service.quartz.QuartzJob;
import com.common.service.quartz.QuartzJobListener;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

import java.util.Date;

/**
 * Created by root on 10/20/16.
 */
public class ThreadConsumer extends Thread {
    public Queue queue=new Queue();
    public QuartzEx quartz;
    IncomingTask task =new IncomingTask();
    public ThreadConsumer(Queue queue, String name) throws SchedulerException {
        this.queue = queue;
        this.name = name;
    }

    public ThreadConsumer(String name) throws SchedulerException {
        this.name = name;
    }

    String name;

    public void run() {
        while (true) {
                queue.getMainQueue().comparator();
            //IncomingTask task = null;
            try {
                task = queue.getMainQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeTask(task);
        }
    }

    public void executeTask(IncomingTask task) {
        System.out.println(name + " priority: " + task.getPriority() + "  " + Thread.activeCount());

//        for (Message m : task.getMsgList()) {
//            //    System.out.println(name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount());
//            String a=name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount();
//        }

    }
}