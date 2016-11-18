package com.common.dao.message;

import com.common.dao.entity.incoming.IncomingTask;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public interface MessageDAO {


    ArrayList<IncomingTask> commonMessageList();

    ArrayList<IncomingTask> departuredMessageList();

    ArrayList<IncomingTask> inProgresMessageList();
}
