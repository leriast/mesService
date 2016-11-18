package com.common.dao.task;

import com.common.dao.entity.incoming.IncomingTask;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */
public interface TaskDAO {
    ArrayList<IncomingTask> commonTaskList();

    ArrayList<IncomingTask> departuredList();

    ArrayList<IncomingTask> inProgresList();
}
