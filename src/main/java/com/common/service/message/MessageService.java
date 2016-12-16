package com.common.service.message;


import com.common.dao.entity.message.CommonMessage;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public interface MessageService {

    ArrayList<CommonMessage> getAllMessageByContact(String contact);
}
