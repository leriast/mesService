package com.common.service.task;

import com.common.dao.entity.journal.Journal;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import com.common.dao.entity.user.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */
public interface TaskService {

    //void insertTask()


    Stencil getStencilById(int id);

    List getAllDucts();

    void insertJournal(Journal journal);

    void commonTaskList();

    ArrayList<Task> departuredList();

    ArrayList<Task> inProgresList();

    List getAllLanguages();

    Structure getStructure();                      // delete

    void insertTask(Task task);

    List getAllStructureById(User user);

    void getStencilByStructure(Structure structure);

    void getStencilByDuct(Duct duct);

    Stencil getStencilByTask(Duct duct, Structure structure);

    Structure getStructureById(int id);

    Duct getDuctById(int id);

    Duct getDuctByName(String name);

    ArrayList<String> getStatistic(Long id);

    List<Stencil> getStencilList(String language,String duct,String username);

    Language getLanguageByName(String name);
}
