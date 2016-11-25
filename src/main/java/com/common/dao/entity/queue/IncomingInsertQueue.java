package com.common.dao.entity.queue;

import com.common.dao.entity.JSONT;
import com.common.dao.entity.incoming.IncomingTask;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 11/25/16.
 */
public class IncomingInsertQueue {
    public static final IncomingInsertQueue INSTANCE = new IncomingInsertQueue();
    //
    // public Pool pool=new Pool();
    private PriorityBlockingQueue<JSONT> incomingInsertQueue=new PriorityBlockingQueue<JSONT>(/*100*/);
    public IncomingInsertQueue(){}
    public IncomingInsertQueue(IncomingTask ex){
    }

    public /*synchronized*/ PriorityBlockingQueue<JSONT> getMainQueue() {
        return INSTANCE.incomingInsertQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<JSONT> mainQueue) {
        this.incomingInsertQueue = mainQueue;
    }
}
