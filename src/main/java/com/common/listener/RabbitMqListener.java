package com.common.listener;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.queue.Queue;
import com.common.service.quartz.QuartzEx;
import com.common.service.workingThread.Pool;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.*;


@EnableRabbit
@Component
public class RabbitMqListener {

    //   Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    public Queue queue = new Queue();
    public QuartzEx quartz;
    int i = 0;

    @RabbitListener(queues = "queue1")
    public void processQueue1(byte[] message) throws IOException, ClassNotFoundException {
        if (i < 1) {
            Pool pool = new Pool(queue);

            i = 1;
        }

        IncomingTask task = (IncomingTask) new ObjectInputStream(new ByteArrayInputStream(message)).readObject();
        /*


        here must be db service


         */
        if (task.getDepartureTime() != null) {
            quartz = QuartzEx.getQuartz();
            quartz.setTask(task);
        } else {
            queue.getMainQueue().add(task);
            queue.getMainQueue().comparator();
        }
    }

}