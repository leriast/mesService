//package com.common.dao.entity.inside;
//
//
//import java.util.ArrayList;
//import java.util.Date;
//
///**
// * Created by root on 10/13/16.
// */
//public class InsideTask implements Comparable<InsideTask>{
//    private ArrayList<Message> msgList;
//    private String status;
//    private Date departureTime;
//    private Date relevant;
//    private int priority;
//    @Override
//    public String toString() {
//        return "InsideTask{" +
//                "msgList=" + msgList +
//                ", status='" + status + '\'' +
//                ", priority=" + priority +
//                '}';
//    }
//
//    public synchronized int getPriority() {
//        return priority;
//    }
//
//    public void setPriority(int priority) {
//        this.priority = priority;
//    }
//
//    public ArrayList<Message> getMsgList() {
//        return msgList;
//    }
//
//    public void setMsgList(ArrayList<Message> msgList) {
//        this.msgList = msgList;
//    }
//
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }
//
//    @Override
//    public int compareTo(InsideTask o) {
//        if(o.priority>priority) return -1;
//        if(priority>o.priority) return 1;
//        return 0;
//    }
//}