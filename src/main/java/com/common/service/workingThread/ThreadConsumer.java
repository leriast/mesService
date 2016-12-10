package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import com.common.service.udp.SendUDP;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.quartz.SchedulerException;
import org.springframework.amqp.core.AmqpTemplate;

import java.util.logging.Logger;


public class ThreadConsumer extends Thread {
    Logger logger = Logger.getLogger(String.valueOf(ThreadConsumer.class));
    JSONParser parser = new JSONParser();
    //Task task=new Task();
    private AmqpTemplate template;

    public ThreadConsumer() {
    }

    public Queue queue = new Queue();
    InsertQueue insertQueue = new InsertQueue();
//    public QuartzEx quartz=new QuartzEx();

    Message message = new Message();

    public ThreadConsumer(AmqpTemplate template, Queue queue, String name) throws SchedulerException {
        this.queue = queue;
        this.name = name;
        this.template = template;
    }

    public ThreadConsumer(String name) throws SchedulerException {
        this.name = name;
    }

    String name;

    public void run() {
        while (true) {

            try {
                message = queue.getMainQueue().take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            executeTask(message, template);
        }
    }

    public void executeTask(Message message, AmqpTemplate template) {
        try {
            new SendUDP(message.getStatistic() + "   done");
            JSONArray paramsArr = (JSONArray) parser.parse(message.getParams());
            JSONArray statisticArr = (JSONArray) parser.parse(message.getStatistic());

            for (int i = 0; i < message.getDuct().length; i++) {
                JSONObject statistic = (JSONObject) statisticArr.get(i);
                JSONObject params = (JSONObject) paramsArr.get(i);
                Long ductStatistic = (Long) statistic.get(message.getDuct()[i]);
                Long frequenceCount = (Long) params.get("frequence");
                if (ductStatistic < frequenceCount) {   //statistic.getDuct return Set<String(Duct name),String(Statistic)>.getStatWhereDuctName
                  //  System.out.println(new Date()+"    loop    "+message.getIdMessage());
                    statistic.put(message.getDuct()[i], ductStatistic + 1);

                    if (Math.round(Math.random()) == 1) {
                        message.setStatus(2);
                    }
                    else {
                        if (ductStatistic == --frequenceCount&&i< message.getDuct().length-1) {
                            message.setNext_duct(message.getDuct()[i+1]);
                            message.setStatus(7);
                          //  System.out.println(message.getMessage()+"   must change stencil "+message.getIdMessage()+"  and next duct   "+message.getNext_duct());
                        }
                        else {
                            message.setStatus(1);
                        }
                    }
                    //return;
                   // break;
                    System.out.println("A "+message.getDuct()[message.getDuct().length - 1]);
                    System.out.println("B "+statistic.get(message.getDuct()[message.getDuct().length - 1]));
                    System.out.println("C "+params.get("frequence"));
                } else if (statistic.get(message.getDuct()[message.getDuct().length - 1]) == (Long) params.get("frequence")) {
                    message.setStatus(3);                   // message doesn`t sent
                }
            }
            message.setStatistic(statisticArr.toJSONString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
      //  System.out.println("add to insert queue");
        insertQueue.getMainQueue().add(message);

    }
}