package com.common.dao.message;


import com.common.dao.entity.message.CommonMessage;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

/**
 * Created by root on 11/16/16.
 */

@Repository
@Transactional
public class MessageDAOImpl implements MessageDAO {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public ArrayList<CommonMessage> getAllMessageByContact(String contact) {
        ArrayList<CommonMessage> list=new ArrayList<>();
        try {
            list.addAll(sessionFactory.getCurrentSession().createQuery("from CommonMessage where address=:address").setParameter("address", contact).list());
            System.out.println("messagedaoimpl resultListSize= "+list.size());
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
