package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import org.quartz.SchedulerException;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));
    public InsertQueue insertQueue=new InsertQueue();

    public ThreadConsumer(){}

    public Queue queue = new Queue();
//    public QuartzEx quartz=new QuartzEx();

        Message task = new Message();

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

   //         System.out.println("tasks for execution "+queue.getMainQueue().size());
            try {
                task = queue.getMainQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            executeTask(task);
        }
    }

    public void executeTask(Message task) {
     //
        try {
            System.out.println(name+"          "+task.getIdMessage()+"  "+Thread.activeCount());
            insertQueue.getMainQueue().add(task);
        }catch (Exception e){
            e.printStackTrace();
        }



















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