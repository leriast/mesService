package com.common.dao.task;

import com.common.dao.entity.journal.Journal;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import com.common.dao.entity.user.User;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */
public interface TaskDAO {
    void commonTaskList() throws ParseException;

    Stencil getStencilById(int id);

    void insertJournal(Journal journal);

    Language getLanguageByName(String name);

    ArrayList<Task> departuredList();
    ArrayList<String> getStatistic(Long id);

    List getAllStructuresById(User user);

    List getAllDucts();

    List getAllLanguages();

    Structure getStructure();

    void insertTask(Task task);

    void getStencilByStructure(Structure structure);

    void getStencilByDuct(Duct duct);

    Stencil getStencilByTask(Duct duct, Structure structure);

    Structure getStructureById(int id);

    Duct getDuctById(int id);

    Duct getDuctByName(String name);

    List<Stencil> getStencilList(String language,String duct,String username);
}
