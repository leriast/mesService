package com.common.service.workingThread;

import com.common.dao.entity.queue.JSONArrQueue;
import com.common.listener.Wrapper;
import com.common.service.file.ReadData;

/**
 * Created by root on 12/27/16.
 */
public class FileReadThread extends Thread {
    private String filename;
    private JSONArrQueue queue = new JSONArrQueue();
    private Wrapper wrapper;
    private ReadData reader=new ReadData();
    public FileReadThread(String filename) {
        this.filename = filename;
    }

    public void run() {
        while (true) {
            if(queue.getMainQueue().size()>0){
                try {
                    wrapper=queue.getMainQueue().take();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }







            }
        }
    }
}
