package com.common.dao.task;


import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
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
    public void commonTaskList() throws ParseException {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Task.class, "arr");
        Task task = (Task) criteria.list().get(0);
        try {
            System.out.println("var=" + task.getVaribles());
//            ByteArrayInputStream bytesIn = new ByteArrayInputStream(task.getVaribles());
//            ObjectInputStream ois = new ObjectInputStream(bytesIn);
//            Object obj = ois.readObject();
//            ois.close();
//          //  System.out.println("1"+obj);
//            JSONArray arr = null;
//
//            try {
//                arr = (JSONArray) obj;
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//            System.out.println("3");
//            for (Object o:arr){
//                JSONObject ob=(JSONObject) o;
//                System.out.println("taskDao  "+ob);
//            }
//            System.out.println("4");
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }

//        for(Object o:list) {
//            Task task = (Task)o;
            //    System.out.println(task.toString());
            //   JSONObject param= (JSONObject) parser.parse(task.getParams());
//            for(Object a:param.keySet()){
//                System.out.println("params  "+task.getId()+" k  "+a+"  v  "+param.get(a));
//            }
            /*try {
                JSONObject var= (JSONObject) parser.parse(task.getVaribles());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }*/
//            for(Object a:param.keySet()){
//                System.out.println("var  "+task.getId()+" k  "+a+"  v  "+param.get(a));
//            }
//        System.out.println(task.getPriority()+"       "+ob.keySet().toArray()[0]);
//        try {
//            JSONObject json= new JSONObject();
//            json.put("a", "b;");
//             task = new Task(userService.getUserByCompany("NP"), getStructure(), new String[]{"push", "telegram"},json.toJSONString() , 1, (Language) getAllLanguages().get(0),json.toJSONString());
//            sessionFactory.getCurrentSession().save(task);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//            JSONArray obj1 = null;
//            try {
//                System.out.println(task.getVaribles());
//                ;
//            } catch (IOException e) {
//                e.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            }
//            System.out.println(obj1.toJSONString());
            //}
         /*criteria.list()*/
            ;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Task> departuredList() {
        ArrayList<Task> list = (ArrayList<Task>) sessionFactory.getCurrentSession().createQuery("from Task l").list();
for(Task task:list){
    System.out.println("!!!!!!!!!   "+task.getId());
}
        return list;
    }

    @Override
    public ArrayList<String> getStatistic(Long id) {
        int count=777;
        int count1=777;

        try {
            count= ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from CommonMessage where id_task=:idTask").setParameter("idTask",getTaskById(id))
                    .uniqueResult()).intValue();
            count1= ((Long)sessionFactory.getCurrentSession().createQuery("select count(*) from CommonMessage where id_task=:idTask and status=2").setParameter("idTask",getTaskById(id))
                    .uniqueResult()).intValue();
        }catch (Exception e){
            e.printStackTrace();

        }

        System.out.println("count ="+count);

        ArrayList<String>list=new ArrayList<>();
        list.add("total "+count);
        list.add("sent "+count1);
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
        System.out.println(structure.getId());
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
        Stencil stencil = (Stencil)query.list().get(0);
    //    System.out.println(stencil.getStencil_entity());
    }

    @Override
    public void getStencilByDuct(Duct duct) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Stencil where id_d_duct = :id ");
        query.setParameter("id", duct.getId());
        Stencil stencil = (Stencil)query.list().get(0);
     //   System.out.println(stencil.getStencil_entity());
    }

    @Override
    public Stencil getStencilByTask(Duct duct, Structure structure) {
        Query query=sessionFactory.getCurrentSession().createQuery("from Stencil where id_d_duct=:id_d_duct and id_structure=:id_structure");
        query.setParameter("id_d_duct",duct.getId());
        query.setParameter("id_structure",structure.getId());
        return (Stencil)query.list().get(0);
       // System.out.println(stencil.getStencil_entity());
    }

    @Override
    public Structure getStructureById(int id) {
        Query query = sessionFactory.getCurrentSession().createQuery("from Structure where id_structure = :id ");
        query.setParameter("id", id);
        if(query.list().isEmpty()){
            System.out.println("structure is null");
            return null;
        }
        else {
            Structure structure = (Structure) query.list().get(0);
            return structure;
        }
    }

    @Override
    public Duct getDuctById(int id){
        Query query =sessionFactory.getCurrentSession().createQuery("from Duct where id_d_duct=:id");
        query.setParameter("id",id);
        return (Duct)query.list().get(0);
    }

    @Override
    public Duct getDuctByName(String name){
        Query query =sessionFactory.getCurrentSession().createQuery("from Duct where name_duct=:name");
        query.setParameter("name",name);
        return (Duct)query.list().get(0);
    }

    public Task getTaskById(Long id){
        Query query=sessionFactory.getCurrentSession().createQuery("from Task where id=:idTask");
        query.setParameter("idTask",id);
        query.setMaxResults(1);
        return (Task)query.list().get(0);
    }


    public List<Stencil> getStencilList(String language,String duct,String username){
        SQLQuery query=sessionFactory.getCurrentSession().createSQLQuery("SELECT STENCIL.* FROM STENCIL INNER JOIN STRUCTURE ON STENCIL.ID_STRUCTURE=STRUCTURE.ID_STRUCTURE\n" +
                "INNER JOIN COMPANY ON STRUCTURE.ID_COMPANY=COMPANY.ID_COMPANY\n" +
                "INNER JOIN CONTACT_PERSON ON COMPANY.ID_COMPANY=CONTACT_PERSON.ID_COMPANY\n" +
                "INNER JOIN D_DUCT ON STENCIL.ID_D_DUCT=D_DUCT.ID_D_DUCT\n" +
                "INNER JOIN LANGUAGE ON STRUCTURE.ID_LANGUAGE=LANGUAGE.ID_LANGUAGE WHERE CONTACT_PERSON.username='"+username+"' AND D_DUCT.NAME_DUCT='"+duct+"' AND LANGUAGE.NAME='"+language+"'");
        query.addEntity(Stencil.class);
        return query.list();
    }
}
