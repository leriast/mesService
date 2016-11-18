package com.common.service.task;

import com.common.dao.entity.incoming.IncomingTask;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public interface TaskService {

    ArrayList<IncomingTask> commonTaskList();

    ArrayList<IncomingTask> departuredList();

    ArrayList<IncomingTask> inProgresList();


}
