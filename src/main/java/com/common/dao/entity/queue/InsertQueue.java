package com.common.dao.entity.queue;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.message.Message1;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 11/11/16.
 */
public class InsertQueue {
    public static final InsertQueue INSTANCE = new InsertQueue();
    //
    // public Pool pool=new Pool();
    private PriorityBlockingQueue<Message1> insertQueue=new PriorityBlockingQueue<Message1>(/*100*/);
    public InsertQueue(){}
    public InsertQueue(IncomingTask ex){
    }

    public /*synchronized*/ PriorityBlockingQueue<Message1> getMainQueue() {
        return INSTANCE.insertQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Message1> mainQueue) {
        this.insertQueue = mainQueue;
    }
}
