package com.common.dao.task;

import com.common.dao.entity.incoming.IncomingTask;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ArrayList<IncomingTask> commonTaskList() {
        return null;
    }

    @Override
    public ArrayList<IncomingTask> departuredList() {
        return null;
    }

    @Override
    public ArrayList<IncomingTask> inProgresList() {
        return null;
    }
}
