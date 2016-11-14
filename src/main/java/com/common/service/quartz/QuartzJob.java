//package com.common.service.quartz;
//
//import com.common.dao.entity.incoming.IncomingTask;
//import com.common.dao.entity.queue.Queue;
//import org.quartz.Job;
//import org.quartz.JobExecutionContext;
//import org.quartz.JobExecutionException;
//
//import javax.transaction.Synchronization;
//import java.util.Collection;
//import java.util.Date;
//
///**
// * Created by root on 10/24/16.
// */
//public class QuartzJob implements Job, Synchronization {
//    Queue queue = new Queue();
//    volatile IncomingTask task;
//
//    public QuartzJob() {
//    }
//
//    @Override
//    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
//        Collection collection = jobExecutionContext.getJobDetail().getJobDataMap().values();
////        synchronized (this) {
//            for (Object a : collection) {
//                task = (IncomingTask) a;
//                System.out.println(task.getPriority() + "   must   " + task.getDepartureTime() + "     now     " + new Date());
//                //task.setDepartureTime(null);
//                queue.getMainQueue().add(task);
//                queue.getMainQueue().comparator();
//          //  }
//        }
//    }
//
//    @Override
//    public void beforeCompletion() {
//    }
//
//    @Override
//    public void afterCompletion(int i) {
//    }
//}
