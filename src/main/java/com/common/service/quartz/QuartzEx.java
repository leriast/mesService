//package com.common.service.quartz;
//
//import com.common.dao.entity.incoming.IncomingTask;
//import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//
//import java.util.Date;
//import java.util.UUID;
//
//import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
//import static org.quartz.TriggerBuilder.newTrigger;
//
///**
// * Created by root on 10/25/16.
// */
//public class QuartzEx {
//    private static volatile QuartzEx instance;
//
//    public static Scheduler getSchedul() {
//        synchronized (schedul) {
//            return schedul;
//        }
//    }
//
//    private IncomingTask task;
//
//    public static void setSchedul(Scheduler schedul) {
//        QuartzEx.schedul = schedul;
//    }
//
//    private static Scheduler schedul;
//
//    public void setTask(IncomingTask task) {
//        this.task = task;
//        startQuartz(task);
//    }
//
//    public QuartzEx(IncomingTask task) {
//        startQuartz(task);
//    }
//
//    public static QuartzEx getQuartz() {
//        if (instance == null) {
//            synchronized (QuartzEx.class) {
//                instance = new QuartzEx();
//            }
//        }
//        return instance;
//    }
//
//    public QuartzEx() {
//        try {
//            setSchedul(new StdSchedulerFactory().getScheduler());
//
//        } catch (SchedulerException e) {
//            e.printStackTrace();
//        }
//    }
//
//
//    public void startQuartz(IncomingTask task) {
////        Date currentTime=new Date();
////        if(task.getDepartureTime().before(currentTime)){
////          //  currentTime=new Date().getTime()+(5000);
////            task.setDepartureTime(new Date(/*(new Date().getTime()+(1000))*/));
////        }
//        try {
//            System.out.println(task.getDepartureTime() + "   preput   " + task.getPriority());
//            Scheduler scheduler = new StdSchedulerFactory().getScheduler();
//
//            //scheduler=new StdSchedulerFactory().getScheduler();
//            // scheduler.getContext().put(String.valueOf(UUID.randomUUID()), task);
//
//            JobDataMap map = new JobDataMap();
//            map.put(String.valueOf(UUID.randomUUID()), task);
//            if(task.getLoop()!=0){
//                /*.repeatForever()*/
//                TriggerBuilder<SimpleTrigger> trigger =newTrigger().startAt(task.getDepartureTime()).withSchedule(simpleSchedule().withRepeatCount(task.getLoop()).withIntervalInHours(1));
//                scheduler.scheduleJob(JobBuilder.newJob(QuartzJob.class).usingJobData(map).withIdentity(new JobKey(task.getId()+"")).build(), (Trigger) trigger.build());
//
//         //       scheduler.scheduleJob(JobBuilder.newJob(QuartzJob.class).usingJobData(map).build(), newTrigger().startAt(new Date(task.getDepartureTime().getTime()+1000)).build());
//            }
//            else{
//                scheduler.scheduleJob(JobBuilder.newJob(QuartzJob.class).withIdentity(UUID.randomUUID()+"").usingJobData(map).build(), newTrigger().startAt(task.getDepartureTime()).build());
//
//
//
//                //      killTask(task.getId());
//            }
//
//            getSchedul().start();
//
//        } catch (SchedulerException e) {
//            System.out.println(new Date() + "        " + task.getDepartureTime());
//            e.printStackTrace();
//        }
//
//    }
//
//
//    private void killTask(int id){
//        try {
//            new StdSchedulerFactory().getScheduler("DefaultQuartzScheduler").deleteJob(new JobKey(id+""));
//
//            /*
//            *
//            *
//            * here we will write interrupt proc into db
//            *
//            * */
//          //  sch.deleteJob(new JobKey(id+""));
//            System.out.println(id + "  was killed");
//        } catch (SchedulerException e) {
//        //    e.printStackTrace();
//        }
//
//    }
//
//}
//
