package com.common.service.workingThread;

import com.common.dao.entity.queue.Queue;

import java.util.Date;

/**
     *delete
 */
public class ClearQueueThread extends Thread {
    private Queue queue = new Queue();
    com.common.dao.entity.message.Message task;

    public void run() {
        while (true) {
                            try {
                    task = queue.getMainQueue().take();
                    if (task.getRelevantTime().after(new Date())) {
                        queue.getMainQueue().add(task);
                    } else {
                        System.out.println(task.getPriority() + " was delete by ClearQueueThread, now "+new Date()+" relevant "+task.getRelevantTime()+"  must "+task.getDepartureTime());
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            queue.getMainQueue().comparator();
        }
    }
}


