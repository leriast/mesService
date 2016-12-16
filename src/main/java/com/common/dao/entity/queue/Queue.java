package com.common.dao.entity.queue;


import com.common.dao.entity.message.Message;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 10/13/16.
 */
public class Queue {
    public static final Queue INSTANCE = new Queue();
   //
   // public Pool pool=new Pool();
    private  PriorityBlockingQueue<Message> mainQueue=new PriorityBlockingQueue<Message>(/*100*/);
    public Queue(){}


    public /*synchronized*/ PriorityBlockingQueue<Message> getMainQueue() {
        return INSTANCE.mainQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Message> mainQueue) {
        this.mainQueue = mainQueue;
    }
}