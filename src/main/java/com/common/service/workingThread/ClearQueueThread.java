//package com.common.service.workingThread;
//
//import com.common.dao.entity.incoming.IncomingTask;
//import com.common.dao.entity.queue.Queue;
//
//import java.util.Date;
//
///**
// * Created by root on 11/4/16.
// */
//public class ClearQueueThread extends Thread {
//    private Queue queue = new Queue();
//    IncomingTask task;
//
//    public void run() {
//        while (true) {
//            //for (int i = 0; i < queue.getMainQueue().size(); i++) {
//                try {
//                    task = queue.getMainQueue().take();
//                    if (task.getRelevant().after(new Date())) {
//                        queue.getMainQueue().add(task);
//                    } else {
//                        System.out.println(task.getPriority() + " was delete by ClearQueueThread, now "+new Date()+" relevant "+task.getRelevant()+"  must "+task.getDepartureTime());
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//          //  }
//            queue.getMainQueue().comparator();
//       /*     try {
//             //   sleep(1000*60*60);
//           //     wait(10000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }*/
//        }
//
//    }
//}
