//package com.common.listener;
//
//import com.common.dao.entity.message.Message;
//import com.common.dao.entity.queue.InsertQueue;
//import org.springframework.amqp.rabbit.annotation.EnableRabbit;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//import java.io.ObjectInputStream;
//
///**
// * Created by root on 11/23/16.
// */
//@EnableRabbit
//@Component
//public class RabbitMQInsertData {
//    public InsertQueue insertQueue = new InsertQueue();
//    @RabbitListener(queues = "insertStat")
//    public void processQueue1(byte[] message) throws IOException, ClassNotFoundException {
//        Message task = (Message) new ObjectInputStream(new ByteArrayInputStream(message)).readObject();
//        insertQueue.getMainQueue().add(task);
//    }
//}
