package com.common.dao.entity.queue;

import com.common.listener.Wrapper;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by root on 12/27/16.
 */
public class JSONArrQueue {
    public static final JSONArrQueue INSTANCE = new JSONArrQueue();

    private PriorityBlockingQueue<Wrapper> mainQueue=new PriorityBlockingQueue<Wrapper>(/*100*/);
    public JSONArrQueue(){}


    public /*synchronized*/ PriorityBlockingQueue<Wrapper> getMainQueue() {
        return INSTANCE.mainQueue;
    }

    public synchronized void setMainQueue(PriorityBlockingQueue<Wrapper> mainQueue) {
        this.mainQueue = mainQueue;
    }
}
