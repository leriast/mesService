package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.Queue;

import java.util.Date;

/**
 * Created by root on 10/28/16.
 */
public class ZeroPriorityThread extends Thread{
    String name="checker";

    private Queue queue=new Queue();
    Message task;

    public void run(){
        while(true){
            try {
                queue.getMainQueue().comparator();
                task=queue.getMainQueue().take();
                if(task.getPriority()==0){
                    if(task.getRelevantTime().after(new Date(new Date().getTime()+60000))){
                        System.out.println(task.getRelevantTime()+"  task isn`t relevant   "+new Date(new Date().getTime()+60000));
                    }
                    else {
                        executeTask(task);
                    }
                }
                else{
                    queue.getMainQueue().add(task);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void executeTask(Message task) {
        System.out.println(name + "   id  "+task.getIdMessage()+" priority: " + task.getPriority() + "  " + Thread.activeCount());
//        for(Recipient r:task.getRecipientList()){
//            String aaa=name + " priority: " + task.getPriority() + "  ";
//        }
//        for (Message m : task.getMsgList()) {
//            //    System.out.println(name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount());
//            String a=name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount();
//        }

    }
}
