package com.common.dao.message;

import com.common.dao.entity.incoming.IncomingTask;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ArrayList<IncomingTask> commonMessageList() {
        return null;
    }

    @Override
    public ArrayList<IncomingTask> departuredMessageList() {
        return null;
    }

    @Override
    public ArrayList<IncomingTask> inProgresMessageList() {
        return null;
    }
}
