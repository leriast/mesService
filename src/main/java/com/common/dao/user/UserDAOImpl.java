package com.common.dao.user;


import com.common.dao.entity.JSONT;
import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.ContactsDictonary;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public User listContact(String name) {
        Criteria criteria = sessionFactory
                .getCurrentSession()
                .createCriteria(User.class, "user")
                .add(Restrictions.ilike("user.username", (name),
                        MatchMode.ANYWHERE));

        return (User) criteria.list().get(0);
    }


    public void insertMessage(Message m) {
        sessionFactory.getCurrentSession().save(m);
         beginCommitTransaction();
    }

    @Override
    public void insertMessageList(ArrayList<Message> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            sessionFactory.getCurrentSession().save(list.get(i));
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
            }
            try {
                beginCommitTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        beginCommitTransaction();

        System.out.println("end");
    }


    @Override
    public void search() {
//        Criteria criteria = sessionFactory
//                .getCurrentSession()
//                .createCriteria(User.class, "arr");
//        User a = new User();
//        a = (User) criteria.list().get(0);
//        System.out.println(a.getUsername() + "   " + a.getPassword());
        Criteria criteria =   sessionFactory.getCurrentSession().createCriteria(Message.class,"arr");
        Message aa=new Message();
        aa=(Message) criteria.list().get(0);
        System.out.println("search   "+aa.getPriority());
    }

    @Override
    public User insertUser(User user) {
        //user.setCompany(companyService.getCompanyById(id_company));
        sessionFactory.getCurrentSession().save(user);
        beginCommitTransaction();
        return user;
    }

    @Override
    public Role getRoleBuId(int id) {
        Role role= (Role) sessionFactory.getCurrentSession().load(Role.class,id);
        return role;
    }

    @Override
    public void getContactsDictonary() {
        ContactsDictonary dictonary=(ContactsDictonary)sessionFactory.getCurrentSession().load(ContactsDictonary.class,100);
      //  System.out.println(dictonary.getName());
    }

    @Override
    public void getContactsByType() {
        String hql= "SELECT CONTACT_PERSON.* FROM CONTACT_PERSON INNER JOIN CONTACTS_CONTACT_PERSON AS CCP\n" +
                "ON CONTACT_PERSON.ID_CONTACT_PERSON=CCP.ID_CONTACT_PERSON\n" +
                "INNER JOIN D_CONTACT_TYPE AS D\n" +
                "ON CCP.ID_CONTACT_TYPE=D.ID_CONTACT_TYPE \n" +
                "WHERE D.NAME='SMS'";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List results = query.list();
        User a =new User();
       // a=(User)results.get(0);
      //  System.out.println(results.get(0));

       // insertUser(new User())
        Query query1 = sessionFactory.getCurrentSession().createSQLQuery(
                "select GetAllTasks (7)");
                //.setParameter("in_id", 7);

        results=query1.list();
        System.out.println(results.get(0));
    }

    @Override
    public void insertJ(JSONT j) {
        try {
            sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO jsont (info) VALUES ('"+ j.getJson()+"'" +
                    ");").executeUpdate();
            /*System.out.println(new Date());
            JSONT jsont=(JSONT)  sessionFactory.getCurrentSession().load(JSONT.class,1);
            JSONObject jsonObj = new JSONObject(jsont.getJson());
            jsonObj.get("idRole");
            System.out.println(new Date());*/
            //System.out.println(jsonObj.get("idRole"));
        }catch (Exception e){
            e.printStackTrace();
        }
        beginCommitTransaction();
    }

    public void beginCommitTransaction(){
        sessionFactory.getCurrentSession().getTransaction().begin();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
