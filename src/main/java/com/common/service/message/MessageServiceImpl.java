package com.common.service.message;

import com.common.dao.entity.message.CommonMessage;
import com.common.dao.message.MessageDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageDAO mes;

    @Override
    public ArrayList<CommonMessage> getAllMessageByContact(String contact) {
       return mes.getAllMessageByContact(contact);
    }
}
