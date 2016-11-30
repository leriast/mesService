package com.common.service.task;

import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import com.common.dao.task.TaskDAO;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */
@Service
@Transactional
public class TaskServiceImpl implements TaskService {
    @Autowired
    TaskDAO taskDAO;
    @Override
    public void commonTaskList() {
        try {
            taskDAO.commonTaskList();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<IncomingTask> departuredList() {
        return null;
    }

    @Override
    public ArrayList<IncomingTask> inProgresList() {
        return null;
    }

    @Override
    public List getAllLanguages() {
        return taskDAO.getAllLanguages();
    }

    @Override
    public Structure getStructure() {
      return  taskDAO.getStructure();
    }

    @Override
    public void insertTask(Task task) {
        taskDAO.insertTask(task);
    }
}
