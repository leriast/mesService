package com.common.service;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.incoming.Recipient;

import javax.transaction.Synchronization;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by root on 10/24/16.
 */
public class SendSMS  {
    public SendSMS(IncomingTask task){
    //    System.out.println("here");
        System.out.println(task.getPriority()+"    depTime    "+task.getDepartureTime()+"     now    "+new Date()+"     recipientCount      "+task.getRecipientList());
        for(Recipient recipient:task.getRecipientList()){
            System.out.println(task.getPriority()+" sms send to "+recipient.getAddress()+" what about all param? "+recipient.getParam().getName());
        }
    }

}
