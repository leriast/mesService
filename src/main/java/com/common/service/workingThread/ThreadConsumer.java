package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quartz.SchedulerException;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));
    JSONParser parser = new JSONParser();
    String name;

    public ThreadConsumer() {
    }

    public Queue queue = new Queue();
    InsertQueue insertQueue = new InsertQueue();

    public ThreadConsumer(Queue queue, String name) throws SchedulerException {
        this.queue = queue;
        this.name = name;

    }

    public ThreadConsumer(String name) throws SchedulerException {
        this.name = name;
    }

    public void run() {
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
                    //    System.out.println("send   "+message.getIdMessage()+"        /     "+message.getDuct()[i]);
                    if (Math.round(Math.random()) == 1) {
                        message.setStatus(2);
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