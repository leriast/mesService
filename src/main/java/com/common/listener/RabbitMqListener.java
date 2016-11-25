package com.common.listener;

import com.common.dao.entity.JSONT;
import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.IncomingInsertQueue;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import com.common.service.workingThread.Pool;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;


@EnableRabbit
@Component
public class RabbitMqListener {
    int a = 0;
    int testIterator=0;
    public InsertQueue insertQueue = new InsertQueue();
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    AmqpTemplate template;
    int poolSize = 8;
    public IncomingInsertQueue incomingInsertQueue=new IncomingInsertQueue();


  //  Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    public Queue queue = new Queue();
    //   public QuartzEx quartz;
    ArrayList<Message> list = new ArrayList<>();

    @RabbitListener(queues = "getData")
    public void processQueue1(byte[] message) throws IOException, ClassNotFoundException {
        try {
            if (a == 0) {

                new Pool(sessionFactory, poolSize,template);
                a = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        IncomingTask task = (IncomingTask) new ObjectInputStream(new ByteArrayInputStream(message)).readObject();
        //  System.out.println(task.getRecipientList().size());
        for (int iter = 0; iter < task.getRecipientList().size(); iter++) {
            //   System.out.println(iter);
            try {
                queue.getMainQueue().add(new Message(iter, new Date(), task.getDepartureTime(), task.getRelevant(), new Date(), new String[]{"asd", "asd"}, "message", "address"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    /*


        here must be db service


         */
        /*if (task.getDepartureTime() != null) {
            quartz = QuartzEx.getQuartz();
            quartz.setTask(task);
        } else {
            queue.getMainQueue().add(task);
            queue.getMainQueue().comparator();
        }*/
    }



    @RabbitListener(queues = "json")
    public void getFileData(byte[] json) throws IOException, ClassNotFoundException {
        if (a == 0) {

            new Pool(sessionFactory, poolSize,template);
            a = 1;
        }
        System.out.println("start rabbit json   "+new Date());
        JSONArray obj1= (JSONArray) new ObjectInputStream(new ByteArrayInputStream(json)).readObject();
        try{
            for(Object o:obj1){
                JSONObject q= (JSONObject) o;
//                if(testIterator%5000==0){
//                    System.out.println(q+"      "+testIterator+"     "+new Date());
//                }
                testIterator++;
                incomingInsertQueue.getMainQueue().add(new JSONT(q.get("idRole").toString()));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("stop rabbit json   "+new Date());
    }

}