package com.common.dao.task;


import com.common.dao.entity.journal.Journal;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import com.common.dao.entity.user.User;
import com.common.service.user.UserService;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 11/16/16.
 */

@Repository
@Transactional
public class TaskDAOImpl implements TaskDAO {
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    UserService userService;

    JSONParser parser = new JSONParser();
@Override
public void insertJournal(Journal journal){
    sessionFactory.getCurrentSession().save(journal);
    sessionFactory.getCurrentSession().getTransaction().begin();
    sessionFactory.getCurrentSession().getTransaction().commit();
}
    @Override
    public void commonTaskList() throws ParseException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class, "arr");
        List<Task> taskList=criteria.list();
        Task task=null;
        if (!taskList.isEmpty()) {
            task= (Task)taskList.get(0);
        }
        try {
            System.out.println("var=" + task.getVaribles());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> departuredList() {
        ArrayList<Task> list = (ArrayList<Task>) sessionFactory.getCurrentSession().createQuery("from Task l").list();
        return list;
    }

    @Override
    public ArrayList<String> getStatistic(Long id) {
        int count = 777;
        int count1 = 777;

        try {
            count = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from CommonMessage where id_task=:idTask").setParameter("idTask", getTaskById(id))
                    .uniqueResult()).intValue();
            count1 = ((Long) sessionFactory.getCurrentSession().createQuery("select count(*) from CommonMessage where id_task=:idTask and status=2").setParameter("idTask", getTaskById(id))
                    .uniqueResult()).intValue();
        } catch (Exception e) {
            e.printStackTrace();

        }

        System.out.println("count =" + count);

        ArrayList<String> list = new ArrayList<>();
        list.add("total " + count);
        list.add("sent " + count1);
        return list;
    }


    @Override
    public List getAllLanguages() {
        List criteria = sessionFactory.getCurrentSession().createQuery("from Language l").list();
        return criteria;
    }

    @Override
    public Structure getStructure() {
        Structure structure = (Structure) sessionFactory.getCurrentSession().load(Structure.class, 1);
        return structure;
    }

    @Override
    public void insertTask(Task task) {
        try {
            sessionFactory.getCurrentSession().save(task);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void getStencilByStructure(Structure structure) {
        List list;
        Query query = sessionFactory.getCurrentSession().createQuery("from Stencil where id_structure = :id ");
        query.setParameter("id", structure.getId());
        Stencil stencil = (Stencil) query.list().get(0);
        //    System.out.println(stencil.getStencil_entity());
    }

    @Override
    public void getStencilByDuct(Duct duct) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Stencil where id_d_duct = :id ");
        query.setParameter("id", duct.getId());
        Stencil stencil = (Stencil) query.list().get(0);
        //   System.out.println(stencil.getStencil_entity());
    }

    @Override
    public Stencil getStencilByTask(Duct duct, Structure structure) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Stencil where id_d_duct=:id_d_duct and id_structure=:id_structure");
        query.setParameter("id_d_duct", duct.getId());
        query.setParameter("id_structure", structure.getId());
        List<Stencil>stencilLis=query.list();
        Stencil stencil=null;
        if(!stencilLis.isEmpty()){
            stencil=stencilLis.get(0);
        }
        return stencil;
        // System.out.println(stencil.getStencil_entity());
    }

    @Override
    public Structure getStructureById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Structure where id_structure = :id ");
        query.setParameter("id", id);
        if (query.list().isEmpty()) {
            return null;
        } else {
            Structure structure = (Structure) query.list().get(0);
            return structure;
        }
    }

    @Override
    public Duct getDuctById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Duct where id_d_duct=:id");
        query.setParameter("id", id);
        if(query.list().isEmpty()){
            return null;
        }else {
            Duct duct= (Duct) query.list().get(0);
            return duct;
        }
    }

    @Override
    public Duct getDuctByName(String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Duct where name_duct=:name");
        query.setParameter("name", name);
        if(query.list().isEmpty()){
            return null;
        }
        else {
            return (Duct) query.list().get(0);
        }
    }

    public Task getTaskById(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Task where id=:idTask");
        query.setParameter("idTask", id);
        query.setMaxResults(1);
        if(query.list().isEmpty()){
            return null;
        }
        return (Task) query.list().get(0);
    }


    public List<Stencil> getStencilList(String language, String duct, String username) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("SELECT STENCIL.* FROM STENCIL INNER JOIN STRUCTURE ON STENCIL.ID_STRUCTURE=STRUCTURE.ID_STRUCTURE\n" +
                "INNER JOIN COMPANY ON STRUCTURE.ID_COMPANY=COMPANY.ID_COMPANY\n" +
                "INNER JOIN CONTACT_PERSON ON COMPANY.ID_COMPANY=CONTACT_PERSON.ID_COMPANY\n" +
                "INNER JOIN D_DUCT ON STENCIL.ID_D_DUCT=D_DUCT.ID_D_DUCT\n" +
                "INNER JOIN LANGUAGE ON STRUCTURE.ID_LANGUAGE=LANGUAGE.ID_LANGUAGE WHERE CONTACT_PERSON.username='" + username + "' AND D_DUCT.NAME_DUCT='" + duct + "' AND LANGUAGE.NAME='" + language + "'");
        query.addEntity(Stencil.class);
        List<Stencil> list = query.list();
        return query.list();
    }

    @Override
    public List getAllStructuresById(User user) {
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery("select structure.* from structure inner join company on structure.id_company=company.id_company\n" +
                "inner join contact_person on contact_person.id_company=company.id_company\n" +
                "where contact_person.id_contact_person='" + user.getIdUser() + "'");
        query.addEntity(Structure.class);
        return query.list();
    }

    @Override
    public List getAllDucts() {
        return sessionFactory.getCurrentSession().createQuery("from Duct").list();
    }

    @Override
    public Stencil getStencilById(int id) {

        if(sessionFactory.getCurrentSession().createQuery("from Stencil where id=:id").setParameter("id", id).list().isEmpty()){
            return null;
        }
        return (Stencil)sessionFactory.getCurrentSession().createQuery("from Stencil where id=:id").setParameter("id", id).list() .get(0);
    }

    @Override
    public Language getLanguageByName(String name){
        Language lng=null;
        List<Language>lngList= sessionFactory.getCurrentSession().createQuery("from Language where name=:name").setParameter("name",name).list();
        if(!lngList.isEmpty()){
            lng=lngList.get(0);
        }
        return lng;
    }
}
