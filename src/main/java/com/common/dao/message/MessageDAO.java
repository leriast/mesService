package com.common.dao.message;

import com.common.dao.entity.message.CommonMessage;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public interface MessageDAO {

    ArrayList<CommonMessage> getAllMessageByContact(String contact);


}
