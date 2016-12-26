package com.common.service.workingThread;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.Queue;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by root on 11/21/16.
 */
@Repository
@Transactional
public class GetThread extends Thread {
    Session session;
    //   List a = new ArrayList<Message>();
    JSONParser parser = new JSONParser();
    long getThreadCount;
    long getThreadNum;
    List<Message> list = new ArrayList<>();
    List<Message> list2 = new ArrayList<>();
    Message messageTempVarible;
    Query query;
    Queue queue = new Queue();

    public GetThread() {
    }

    private SessionFactory sessionFactory;

    public GetThread(SessionFactory sessionFactory,long getThreadNum, long getThreadCount) {
        this.sessionFactory = sessionFactory;
        this.getThreadCount = getThreadCount;
        this.getThreadNum=getThreadNum;
    }

    public void run() {
        try {
            session = sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            session = sessionFactory.openSession();
        }
        while (true) {
            if (queue.getMainQueue().size() < 25000) {

                query = session.createQuery("from Message where status = :code  and idMessage %:getThreadCount= :getThreadNum order by priority");
                query.setParameter("getThreadNum", getThreadNum);
                query.setParameter("code", 7);
                query.setParameter("getThreadCount", getThreadCount);

                query.setMaxResults(1);
                list = query.list();
                if (!list.isEmpty()) {
          //          System.out.println("        " + currentThread().getName() + "        _______getThread status 7   " + new Date());
                    try {
                        Message messWithStatus7 = (Message) query.list().get(0);
                        String duct = messWithStatus7.getNext_duct();
                        query = session.createQuery("from Message where status = :code and  id_task=:idTask and idMessage %:getThreadCount= :getThreadNum and next_duct=:next_duct order by priority");
                        query.setParameter("code", 7);
                        query.setParameter("getThreadNum", getThreadNum);
                        query.setParameter("getThreadCount",getThreadCount);
                        query.setParameter("next_duct", duct);
                        query.setMaxResults(25000);

                        Task task = messWithStatus7.getId_task();
                        query.setParameter("idTask", task);
                        list = query.list();                        ///from message where status = 7 and id_task = sam
                        Structure structure = task.getStructure();

                        Stencil stencil = selectStencilByStructure(structure, duct);
                        for (Message messBeforeExecute : list) {
                            JSONObject varibles = null;
                            try {
                                varibles = (JSONObject) parser.parse(messBeforeExecute.getStencil());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            messBeforeExecute.setStatus(11);

                            String messageEntity = stencil.getStencil_entity();
                            for (Object var : varibles.keySet()) {
                                messageEntity = messageEntity.replaceAll("(\\{" + var + "\\})", String.valueOf(varibles.get(var)));
                            }

                            messBeforeExecute.setMessage(messageEntity);
                            session.update(messBeforeExecute);
                            list2.add(messBeforeExecute);
                        }
                        session.getTransaction().begin();
                        session.getTransaction().commit();
                        session.clear();
                        queue.getMainQueue().addAll(list2);
                        list.clear();
                        list2.clear();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                query = session.createQuery("from Message where status = :code and idMessage %:getThreadCount= :getThreadNum order by priority");
                query.setParameter("code", 1);
                query.setParameter("getThreadCount", getThreadCount);
                query.setParameter("getThreadNum", getThreadNum);
                query.setMaxResults(25000);
                list = query.list();
                if (!list.isEmpty()) {
                    list = query.list();
                    for (Object object : list) {
                        messageTempVarible = (Message) object;
                        messageTempVarible.setStatus(11);
                        session.update(messageTempVarible);
                    }
                    session.getTransaction().begin();
                    session.getTransaction().commit();
                    //     session.close();
                    queue.getMainQueue().addAll(list);
                    list.clear();
                    session.clear();
                } else {
                    try {
                        sleep(500);                     //too many connections to DB
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                try {
                    sleep(300);                             //many empty queries
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Stencil selectStencilByStructure(Structure structure, String next) {
        Query q = session.createQuery("from Duct where name_duct=:name");
        q.setParameter("name", next);
        q.setMaxResults(1);
        Duct duct = (Duct) q.list().get(0);
        q = session.createQuery("from Stencil where id_structure=:id_str and id_d_duct=:iduct");

        q.setParameter("id_str", structure);
        q.setParameter("iduct", duct);
        Stencil stencil = (Stencil) q.list().get(0);
        return stencil;
    }
}