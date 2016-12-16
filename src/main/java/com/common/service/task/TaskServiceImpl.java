package com.common.service.task;

import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
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
    public ArrayList<Task> departuredList() {
        return taskDAO.departuredList();
    }

    @Override
    public ArrayList<Task> inProgresList() {
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

    @Override
    public void getAllStructure() {
        taskDAO.getStructure();
    }

    @Override
    public void getStencilByStructure(Structure structure) {
        taskDAO.getStencilByStructure(structure);
    }

    @Override
    public void getStencilByDuct(Duct duct) {
        taskDAO.getStencilByDuct(duct);
    }

    @Override
    public Stencil getStencilByTask(Duct duct, Structure structure) {
       return taskDAO.getStencilByTask(duct,structure);
    }

    @Override
    public Structure getStructureById(int id) {
        return taskDAO.getStructureById(id);
    }
    @Override
    public Duct getDuctById(int id){
        return taskDAO.getDuctById(id);
    }

    @Override
    public Duct getDuctByName(String name) {
        return taskDAO.getDuctByName(name);
    }

    @Override
    public ArrayList<String> getStatistic(Long id){
        return taskDAO.getStatistic(id);
    }

    @Override
    public List<Stencil> getStencilList(String language, String duct, String username) {
        return taskDAO.getStencilList(language,duct,username);
    }
}
