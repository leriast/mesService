package com.common.dao.entity.queue;


import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.message.Message1;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 10/13/16.
 */
public class Queue {
    public static final Queue INSTANCE = new Queue();
   //
   // public Pool pool=new Pool();
    private  PriorityBlockingQueue<Message1> mainQueue=new PriorityBlockingQueue<Message1>(/*100*/);
    public Queue(){}
    public Queue(IncomingTask ex){
    }

    public /*synchronized*/ PriorityBlockingQueue<Message1> getMainQueue() {
        return INSTANCE.mainQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Message1> mainQueue) {
        this.mainQueue = mainQueue;
    }
}