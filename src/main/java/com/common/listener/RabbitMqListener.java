package com.common.listener;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import com.common.service.workingThread.Pool;
import org.hibernate.SessionFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;


@EnableRabbit
@Component
public class RabbitMqListener {
   boolean a;
   public InsertQueue insertQueue=new InsertQueue();
    @Autowired
    SessionFactory sessionFactory;
//    @Autowired @Qualifier("properties")
    int poolSize=8;


    Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    public Queue queue = new Queue();
    //   public QuartzEx quartz;
    ArrayList<Message> list = new ArrayList<>();

    @RabbitListener(queues = "queue1")
    public void processQueue1(byte[] message) throws IOException, ClassNotFoundException {

      try{  if (!a) {

          Pool pool= new Pool(sessionFactory,poolSize);
          a = true;
      }}catch (Exception e){/*NOP*/}

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

}