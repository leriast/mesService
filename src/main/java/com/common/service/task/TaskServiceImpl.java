package com.common.service.task;

import com.common.dao.entity.incoming.IncomingTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired

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
