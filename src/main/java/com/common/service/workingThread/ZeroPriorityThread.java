//package com.common.service.workingThread;
//
//import com.common.dao.entity.incoming.IncomingTask;
//import com.common.dao.entity.incoming.Recipient;
//import com.common.dao.entity.queue.Queue;
//
//import java.util.Date;
//
///**
// * Created by root on 10/28/16.
// */
//public class ZeroPriorityThread extends Thread{
//    String name="checker";
//
//    private Queue queue=new Queue();
//    IncomingTask task;
//
//    public void run(){
//        while(true){
//            try {
//                queue.getMainQueue().comparator();
//                task=queue.getMainQueue().take();
//                if(task.getPriority()==0){
//                    if(task.getRelevant().after(new Date(new Date().getTime()+60000))){
//                        System.out.println(task.getRelevant()+"  task isn`t relevant   "+new Date(new Date().getTime()+60000));
//                    }
//                    else {
//                        executeTask(task);
//                    }
//                }
//                else{
//                    queue.getMainQueue().add(task);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//    public void executeTask(IncomingTask task) {
//        System.out.println(name + "   id  "+task.getId()+" priority: " + task.getPriority() + "  " + Thread.activeCount());
//        for(Recipient r:task.getRecipientList()){
//            String aaa=name + " priority: " + task.getPriority() + "  ";
//        }
////        for (Message m : task.getMsgList()) {
////            //    System.out.println(name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount());
////            String a=name+" priority: "+task.getPriority() + "  " + m.generateTextMessage()+"  "+Thread.activeCount();
////        }
//
//    }
//}
