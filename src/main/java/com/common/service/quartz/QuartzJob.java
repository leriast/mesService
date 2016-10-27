package com.common.service.quartz;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.queue.Queue;
import com.common.listener.RabbitMqListener;
import com.common.service.SendSMS;
import com.common.service.workingThread.ThreadConsumer;
import org.quartz.*;

import javax.transaction.Synchronization;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * Created by root on 10/24/16.
 */
public class QuartzJob implements Job, Synchronization {
    SendSMS sms;
    Queue queue=new Queue();
    volatile IncomingTask task;

    public QuartzJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        Collection collection = jobExecutionContext.getJobDetail().getJobDataMap().values();
        synchronized (this) {
            for (Object a : collection) {
                task=(IncomingTask)a;
                //System.out.println(task.getPriority() + "   must   " + task.getDepartureTime() + "     now     " + new Date());
                task.setDepartureTime(null);
                queue.getMainQueue().add(task);
            }

        }
    }

    @Override
    public void beforeCompletion() {

    }

    @Override
    public void afterCompletion(int i) {

    }
}
