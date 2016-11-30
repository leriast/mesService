package com.common.dao.entity.queue;

import com.common.dao.entity.message.Message;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 11/25/16.
 */
public class IncomingInsertQueue {
    public static final IncomingInsertQueue INSTANCE = new IncomingInsertQueue();
    //
    // public Pool pool=new Pool();
    private PriorityBlockingQueue<Message> incomingInsertQueue=new PriorityBlockingQueue<Message>(/*100*/);
    public IncomingInsertQueue(){}


    public /*synchronized*/ PriorityBlockingQueue<Message> getMainQueue() {
        return INSTANCE.incomingInsertQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Message> mainQueue) {
        this.incomingInsertQueue = mainQueue;
    }
}
