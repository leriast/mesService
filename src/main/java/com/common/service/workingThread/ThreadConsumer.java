package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import org.quartz.SchedulerException;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));

private AmqpTemplate template;
    public ThreadConsumer() {
    }

    public Queue queue = new Queue();
    InsertQueue insertQueue=new InsertQueue();
//    public QuartzEx quartz=new QuartzEx();

    Message task = new Message();

    public ThreadConsumer(AmqpTemplate template,Queue queue, String name) throws SchedulerException {
        this.queue = queue;
        this.name = name;
        this.template=template;
    }

    public ThreadConsumer(String name) throws SchedulerException {
        this.name = name;
    }

    String name;

    public void run() {
        while (true) {

            try {
                task = queue.getMainQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeTask(task,template);
        }
    }

    public void executeTask(Message task,AmqpTemplate template) {
        task.setStatus(2);
     //   System.out.println(task.getIdMessage()+"  "+name);
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        insertQueue.getMainQueue().add(task);
    }
}