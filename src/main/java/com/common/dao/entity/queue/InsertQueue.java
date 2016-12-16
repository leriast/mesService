package com.common.dao.entity.queue;

import com.common.dao.entity.message.Message;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 11/11/16.
 */
public class InsertQueue {
    public static final InsertQueue INSTANCE = new InsertQueue();
    //
    // public Pool pool=new Pool();
    private PriorityBlockingQueue<Message> insertQueue=new PriorityBlockingQueue<Message>(/*100*/);
    public InsertQueue(){}


    public /*synchronized*/ PriorityBlockingQueue<Message> getMainQueue() {
        return INSTANCE.insertQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Message> mainQueue) {
        this.insertQueue = mainQueue;
    }
}
