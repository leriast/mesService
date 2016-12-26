package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {

    public ThreadConsumer(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private SessionFactory sessionFactory;
    //private ArrayList<Message> messagesForExecute=new ArrayList<>();
    private Session session;
    Queue queue = new Queue();
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));
    JSONParser parser = new JSONParser();
    InsertQueue insertQueue = new InsertQueue();
    public void run() {
        System.out.println("consumer " + currentThread().getName());
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        while (true) {
            try {
                executeTask(queue.getMainQueue().take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void executeTask(Message message) {
        try {
            //         new SendUDP(message.getStatistic() + "   done");
            JSONArray paramsArr = (JSONArray) parser.parse(message.getParams());
            JSONArray statisticArr = (JSONArray) parser.parse(message.getStatistic());
            for (int i = 0; i < message.getDuct().length; i++) {
                JSONObject statistic = (JSONObject) statisticArr.get(i);
                JSONObject params = (JSONObject) paramsArr.get(i);
                Long ductStatistic = (Long) statistic.get(message.getDuct()[i]);
                Long frequenceCount = (Long) params.get("frequence");
                if (ductStatistic < frequenceCount) {   //statistic.getDuct return Set<String(Duct name),String(Statistic)>.getStatWhereDuctName
                    statistic.put(message.getDuct()[i], ductStatistic + 1);
               //     System.out.println("try send    "+message.getIdMessage());
                    if (Math.round(Math.random()) == 1) {
                        message.setStatus(2);
               //         System.out.println("send   "+message.getIdMessage()+"        /     "+message.getDuct()[i]);
             //           System.out.println(message.getMessage()+"       /           "+message.getNext_duct());
                        message.setStatistic(statisticArr.toJSONString());
                        insertQueue.getMainQueue().add(message);
                        return;
                    } else {
                        if (ductStatistic == --frequenceCount && i < message.getDuct().length - 1) {
                            message.setNext_duct(message.getDuct()[i + 1]);
                            message.setStatus(7);
                        } else {
                            message.setStatus(1);
                        }
                        message.setStatistic(statisticArr.toJSONString());
                        insertQueue.getMainQueue().add(message);
                        return;
                    }
                } else if (statistic.get(message.getDuct()[message.getDuct().length - 1]) == (Long) params.get("frequence")) {
                    message.setStatus(3);                                   // message doesn`t sent
                    message.setStatistic(statisticArr.toJSONString());
                    insertQueue.getMainQueue().add(message);
                    return;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}