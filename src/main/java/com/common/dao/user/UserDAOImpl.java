package com.common.dao.user;


import com.common.dao.entity.message.Message;
import com.common.dao.entity.security.Role;
import com.common.dao.entity.security.User;
import com.common.service.company.CompanyService;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;
    @Autowired
    CompanyService companyService;
    //  Transaction ses=sessionFactory.getCurrentSession().beginTransaction();

    int i = 0;

    public User listContact(String name) {
        Criteria criteria = sessionFactory
                .getCurrentSession()
                .createCriteria(User.class, "user")
                .add(Restrictions.ilike("user.username", (name),
                        MatchMode.ANYWHERE));

        return (User) criteria.list().get(0);
    }


    public void insertMessage(Message m) {

        System.out.println("realTrue");
        sessionFactory.getCurrentSession().save(m);
        //     sessionFactory.getCurrentSession().save(new Message("asdsaa"+i,"asdsaad"+i,true));
        sessionFactory.getCurrentSession().getTransaction().begin();
        sessionFactory.getCurrentSession().getTransaction().commit();


    }

    @Override
    public void insertMessageList(ArrayList<Message> list) {
        System.out.println();
        for (int i = 0; i < list.size(); i++) {
            sessionFactory.getCurrentSession().save(list.get(i));
            if (sessionFactory.getCurrentSession().getTransaction() != null) {
            }
            try {
                sessionFactory.getCurrentSession().getTransaction().begin();
                sessionFactory.getCurrentSession().getTransaction().commit();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        sessionFactory.getCurrentSession().getTransaction().begin();
        sessionFactory.getCurrentSession().getTransaction().commit();

        System.out.println("end");
    }


    @Override
    public void search() {
   /*     Criteria criteria = sessionFactory
                .getCurrentSession()
                .createCriteria(User.class, "arr");
        User a = new User();
        a = (User) criteria.list().get(0);
        System.out.println(a.getUsername() + "   " + a.getPassword());
    */
    }

    @Override
    public void insertUser(User user) {
        //user.setCompany(companyService.getCompanyById(id_company));
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().getTransaction().begin();
        sessionFactory.getCurrentSession().getTransaction().commit();
    }

    @Override
    public Role getRoleBuId(int id) {
        Role role= (Role) sessionFactory.getCurrentSession().load(Role.class,id);
        return role;
    }
}
