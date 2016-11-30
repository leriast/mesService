package com.common.dao.task;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */
public interface TaskDAO {
    void commonTaskList() throws ParseException;

    ArrayList<IncomingTask> departuredList();

    ArrayList<IncomingTask> inProgresList();

    List getAllLanguages();

    Structure getStructure();

    void insertTask(Task task);
}
