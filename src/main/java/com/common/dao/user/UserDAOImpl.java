package com.common.dao.user;


import com.common.dao.entity.message.Message;
import com.common.dao.entity.user.Role;
import com.common.dao.entity.user.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;
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
        }
        beginCommitTransaction();
    }


    @Override
    public void search() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Message.class, "arr");
        Message aa  = (Message) criteria.list().get(0);
        System.out.println("search   " + aa.getPriority());
    }

    @Override
    public User insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        sessionFactory.getCurrentSession().save(user);
        beginCommitTransaction();
        return user;
    }

    @Override
    public Role getRoleById(int id) {
        Role role = (Role) sessionFactory.getCurrentSession().load(Role.class, id);
        return role;
    }

    @Override
    public List getContactsDictonary() {
        return sessionFactory.getCurrentSession().createQuery("from ContactsDictonary").list();
    }

    @Override
    public void getContactsByType() {
        String hql = "SELECT CONTACT_PERSON.* FROM CONTACT_PERSON INNER JOIN CONTACTS_CONTACT_PERSON AS CCP\n" +
                "ON CONTACT_PERSON.ID_CONTACT_PERSON=CCP.ID_CONTACT_PERSON\n" +
                "INNER JOIN D_CONTACT_TYPE AS D\n" +
                "ON CCP.ID_CONTACT_TYPE=D.ID_CONTACT_TYPE \n" +
                "WHERE D.NAME='SMS'";
        SQLQuery query = sessionFactory.getCurrentSession().createSQLQuery(hql);
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);

         Query query1 = sessionFactory.getCurrentSession().createSQLQuery(
                "select GetAllTasks /*(7)*/");
        List results = query1.list();
        System.out.println(results.get(0));
    }



    @Override
    public User getUserByCompany(String companyName) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User a join fetch a.company where a.company.company_name = :companyName").setParameter("companyName", companyName);
        List<User> list = query.list();
        return (User) list.get(0);
    }

    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void deleteUser(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }

    @Override
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public List<Role> getAllRoles() {
        List<Role> list=sessionFactory.getCurrentSession().createQuery("from Role").list();
        return list;
    }

    @Override
    public User getUserById(int id) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where idUser=:id").setParameter("id",id).list().get(0);
    }

    @Override
    public User getUserByLogin(String name) {
        return (User) sessionFactory.getCurrentSession().createQuery("from User where username=:id").setParameter("id",name).list().get(0);
    }

    public void beginCommitTransaction() {
        sessionFactory.getCurrentSession().getTransaction().begin();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }
}
