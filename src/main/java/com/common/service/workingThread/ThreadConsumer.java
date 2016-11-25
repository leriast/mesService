package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.Queue;
import org.quartz.SchedulerException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.utils.SerializationUtils;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));

private AmqpTemplate template;
    public ThreadConsumer() {
    }

    public Queue queue = new Queue();
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

            //         System.out.println("tasks for execution "+queue.getMainQueue().size());
            try {
                task = queue.getMainQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeTask(task,template);
        }
    }

    public void executeTask(Message task,AmqpTemplate template) {
        //
        try {
            System.out.println(name + "          " + task.getIdMessage() + "  " + task.getPriority() + "        " + Thread.activeCount());
      //      System.out.println(queue.getMainQueue().size() + "               " + insertQueue.getMainQueue().size());
            //     sleep(100);

        } catch (Exception e) {
            e.printStackTrace();
        }
        byte[] data = SerializationUtils.serialize(task);
        template.convertAndSend("insertStat", data);


















/*

       if(
               task.getRelevant().after(new Date())){
           System.out.println(task.getRelevant()+"          "+new Date());
            */
/*for(int i=0;i<task.getChanel().size();i++){

        }*//*


           System.out.println(name + " priority: " + task.getPriority() + "  " + Thread.activeCount() +" and deadTime "+task.getRelevant());
//        try {
//            sleep(11000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        for(Recipient r:task.getRecipientList()){
             String aaa=name + " priority: " + task.getPriority() + "  ";

         //      System.out.println(r.getAddress());
        }
//        for (Message m : task.getMsgList()) {
//            //    System.out.println(name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount());
//            String a=name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount();
//        }
       }else{
           System.out.print(task.getPriority()+"   was thrown");
           System.out.println(task.getRelevant()+"          "+new Date());
           */
/*
           *
           *
           *
           * 
           *
           *
           *
           * *//*

       }
*/

    }
}