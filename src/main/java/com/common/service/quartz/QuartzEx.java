package com.common.service.quartz;

import com.common.dao.entity.incoming.IncomingTask;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import javax.transaction.Synchronization;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

/**
 * Created by root on 10/25/16.
 */
public class QuartzEx implements Synchronization{
    private static volatile QuartzEx instance;
    public static Scheduler getSchedul() {
        synchronized (schedul){return schedul;}
    }
    private IncomingTask task;
    public static void setSchedul(Scheduler schedul) {
        QuartzEx.schedul = schedul;
    }

    private static Scheduler schedul;

    public void setTask(IncomingTask task) {
        this.task = task;
        startQuartz(task);
    }
    public QuartzEx(IncomingTask task)  {
        startQuartz(task);
    }

    public static QuartzEx getQuartz()  {
        if (instance == null) {
            synchronized (QuartzEx.class) {
                instance = new QuartzEx();
            }
        }
        return instance;
    }
    public QuartzEx() {
        try {
            setSchedul(new StdSchedulerFactory().getScheduler());

        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }


    public void startQuartz(IncomingTask task) {
//        Date currentTime=new Date();
//        if(task.getDepartureTime().before(currentTime)){
//          //  currentTime=new Date().getTime()+(5000);
//            task.setDepartureTime(new Date(/*(new Date().getTime()+(1000))*/));
//        }
        try {
            System.out.println(task.getDepartureTime()+"   preput   "+task.getPriority());
           Scheduler scheduler=new StdSchedulerFactory().getScheduler();
            //scheduler=new StdSchedulerFactory().getScheduler();
           // scheduler.getContext().put(String.valueOf(UUID.randomUUID()), task);
            JobDataMap map=new JobDataMap();
            map.put(String.valueOf(UUID.randomUUID()), task);
            scheduler.scheduleJob(JobBuilder.newJob(QuartzJob.class).usingJobData(map).build(), TriggerBuilder.newTrigger().startAt(task.getDepartureTime()).build());
            getSchedul().start();

        } catch (SchedulerException e) {
            System.out.println(new Date()+"        "+task.getDepartureTime());
            e.printStackTrace();
        }
    }

    public String getCronExpression(String date) {
/*

parse date to string

 */
        time = ("13:11 A");
        startDate = ("25-10-2016");
        String time = getTime();
        String[] time1 = time.split("\\:");
        String[] time2 = time1[1].split("\\ ");

        String hour = "";
        if (time2[1].equalsIgnoreCase("PM")) {
            Integer hourInt = Integer.parseInt(time1[0]) + 12;
            if (hourInt == 24) {
                hourInt = 0;
            }
            hour = hourInt.toString();
        } else {
            hour = time1[0];
        }

        String minutes = time2[0];
        String cronExp = "";
        if (isRecurring()) {
            String daysString = "";
            StringBuilder sb = new StringBuilder(800);
            boolean moreConditions = false;

            if (isSUN()) {
                sb.append("SUN");
                moreConditions = true;
            }

            if (isMON()) {
                if (moreConditions) {
                    sb.append(",");
                }
                sb.append("MON");
                moreConditions = true;
            }

            if (isTUE()) {
                if (moreConditions) {
                    sb.append(",");
                }

                sb.append("TUE");
                moreConditions = true;
            }

            if (isWED()) {
                if (moreConditions) {
                    sb.append(",");
                }

                sb.append("WED");
                moreConditions = true;
            }

            if (isTHU()) {
                if (moreConditions) {
                    sb.append(",");
                }
                sb.append("THU");
                moreConditions = true;
            }

            if (isFRI()) {
                if (moreConditions) {
                    sb.append(",");
                }
                sb.append("FRI");
                moreConditions = true;
            }

            if (isSAT()) {
                if (moreConditions) {
                    sb.append(",");
                }
                sb.append("SAT");
                moreConditions = true;
            }

            daysString = sb.toString();

            cronExp = "0 " + minutes + " " + hour + " ? * " + daysString;
        } else {
            String startDate = getStartDate();
            String[] dateArray = startDate.split("\\-");
            String day = dateArray[0];
            if (day.charAt(0) == '0') {
                day = day.substring(1);
            }

            String month = dateArray[1];

            if (month.charAt(0) == '0') {
                month = month.substring(1);
            }

            String year = dateArray[2];
            cronExp = "0 " + minutes + " " + hour + " " + day + " " + month
                    + " ? " + year;

        }
        return cronExp;
    }

    String startDate;
    String time;
    boolean recurring;
    boolean SUN;
    boolean MON;
    boolean TUE;
    boolean WED;
    boolean THU;
    boolean FRI;
    boolean SAT;

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * The date set should be of the format (MM-DD-YYYY for example 25-04-2011)
     *
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the time
     */
    public String getTime() {
        return time;
    }

    /**
     * The time set should be of the format (HH:MM AM/PM for example 12:15 PM)
     *
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    public boolean isRecurring() {
        return recurring;
    }

    public void setRecurring(boolean recurring) {
        this.recurring = recurring;
    }

    public boolean isSUN() {
        return SUN;
    }

    public void setSUN(boolean sUN) {
        SUN = sUN;
    }

    public boolean isMON() {
        return MON;
    }

    /**
     * @param mON the mON to set
     */
    public void setMON(boolean mON) {
        MON = mON;
    }

    public boolean isTUE() {
        return TUE;
    }

    public void setTUE(boolean tUE) {
        TUE = tUE;
    }

    public boolean isWED() {
        return WED;
    }

    public void setWED(boolean wED) {
        WED = wED;
    }

    public boolean isTHU() {
        return THU;
    }

    public void setTHU(boolean tHU) {
        THU = tHU;
    }

    public boolean isFRI() {
        return FRI;
    }

    public void setFRI(boolean fRI) {
        FRI = fRI;
    }

    public boolean isSAT() {
        return SAT;
    }

    public void setSAT(boolean sAT) {
        SAT = sAT;
    }

    @Override
    public void beforeCompletion() {

    }

    @Override
    public void afterCompletion(int i) {

    }


//
//    public int hashCode() {
//        return this.getCronExpression().hashCode();
//    }
//
//    public boolean equals(Object obj) {
//        if (obj == null) {
//            return false;
//        }
//        if (obj instanceof CronExprGenerator) {
//            if (((CronExprGenerator) obj).getCronExpression()
//                    .equalsIgnoreCase(this.getCronExpression())) {
//                return true;
//            }
//        } else {
//            return false;
//        }
//        return false;

//    }
}
/*
cronExpression: "s m h D M W Y"
                 | | | | | | `- Year [optional]
                 | | | | | `- Day of Week
                 | | | | `- Month
                 | | | `- Day of Month
                 | | `- Hour
                 | `- Minute
                 `- Second

    	 */

