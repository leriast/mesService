package com.common.service.task;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */
public interface TaskService {

    //void insertTask()

    void commonTaskList();

    ArrayList<IncomingTask> departuredList();

    ArrayList<IncomingTask> inProgresList();

    List getAllLanguages();

    Structure getStructure();

    void insertTask(Task task);

    void getAllStructure();



}
