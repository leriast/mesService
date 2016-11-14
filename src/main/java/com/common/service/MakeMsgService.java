package com.common.service;


import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.inside.Message;

import java.util.ArrayList;

/**
 * Created by root on 10/13/16.
 */
public class MakeMsgService {

    public IncomingTask makeMsg(IncomingTask iTask) {

        IncomingTask exTask = new IncomingTask();
        exTask.setPriority(iTask.getPriority());
        ArrayList<Message> list = new ArrayList<Message>();

//        for(Recipient recipient:iTask.getRecipientList()){
//            list.add(new Message(recipient.getParam().getValuse(), recipient.getTelephone()));
//        }
        //      exTask.setMsgList(list);

        return exTask;
    }
}