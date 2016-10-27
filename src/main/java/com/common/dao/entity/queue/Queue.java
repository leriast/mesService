package com.common.dao.entity.queue;


import com.common.dao.entity.incoming.IncomingTask;
import com.common.service.workingThread.Pool;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 10/13/16.
 */
public class Queue {
    public static final Queue INSTANCE = new Queue();
   //
   // public Pool pool=new Pool();
    private PriorityBlockingQueue<IncomingTask> mainQueue=new PriorityBlockingQueue<IncomingTask>();
    public Queue(){}
    public Queue(IncomingTask ex){
    }

    public synchronized PriorityBlockingQueue<IncomingTask> getMainQueue() {
        return INSTANCE.mainQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<IncomingTask> mainQueue) {
        this.mainQueue = mainQueue;
    }
}