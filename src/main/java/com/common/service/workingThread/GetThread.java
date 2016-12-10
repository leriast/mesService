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
    JSONParser parser=new JSONParser();
    long tw;
    List<Message> list = new ArrayList<>();
    List<Message> list2=new ArrayList<>();
    Message aa;
    Query query;
    Queue queue = new Queue();

    public GetThread() {
    }


    private SessionFactory sessionFactory;

    public GetThread(SessionFactory sessionFactory, long tw) {
        this.sessionFactory = sessionFactory;
        this.tw = tw;
    }

    public void run() {
        while (true) {
            System.out.println(queue.getMainQueue().size());
            if (queue.getMainQueue().size() < 10000) {
                try {
                    session = sessionFactory.getCurrentSession();
                } catch (HibernateException e) {
                    while (sessionFactory.getStatistics().getSessionOpenCount() > 25) {
                    }
                    session = sessionFactory.openSession();
                }

                query = session.createQuery("from Message where status = :code  and idMessage %2= :tw");
                query.setParameter("tw",tw);
                query.setParameter("code", 7);
                query.setMaxResults(1);
                list=query.list();
                if (!list.isEmpty()) {
                    try {
                        Message m = (Message) query.list().get(0);
                        m.setStatus(12);

                        query = session.createQuery("from Message where status = :code and  id_task=:idTask and idMessage %2= :tw and next_duct=:next_duct");
                        query.setParameter("code", 7);
                        query.setParameter("tw", tw);
                        query.setParameter("next_duct", m.getNext_duct());

                        query.setMaxResults(10000);
                        Task task = m.getId_task();
                        query.setParameter("idTask", task);
                        list = query.list();                        ///from message where status = 7 and id_task = same
                        list.add(m);
                        Structure structure = task.getStructure();
                        String duct = m.getNext_duct();
                   //     System.out.println("duct= " + duct);
                        Stencil stencil = selectStencilByStructure(structure, duct);
                    //    System.out.println("varibles = " + m.getStencil()+ " stencil= "+stencil.getStencil_entity());

                   //     System.out.println("getThread before foreach    "+new Date());
                        for (Message aa : list) {
                            JSONObject varibles = null;
                            try {
                                varibles = (JSONObject) parser.parse(aa.getStencil());
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            aa.setStatus(11);
                            aa.setMessage(readStencil(stencil.getStencil_entity(), varibles));
                            session.update(aa);
                            list2.add(aa);
                        }
                    //    System.out.println(aa.getMessage()+"    getThread end work with mess_new stencil    "+new Date());
                        session.flush();
                        session.getTransaction().begin();
                        session.getTransaction().commit();
                        session.close();
                        queue.getMainQueue().addAll(list2);
                        list.clear();
                        list2.clear();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    //hibernate.StaleStateException: Batch update returned unexpected row count from update [0]; actual row count: 0; expected: 1
                } else {
                    query = session.createQuery("from Message where status = :code and idMessage %2= :tw");
                    query.setParameter("code", 1);
                    query.setParameter("tw", tw);
                    query.setMaxResults(10000);
                    list=query.list();
                    if (!list.isEmpty()) {
                        list = query.list();
                        for (Object object : list) {
                            aa = (Message) object;
                            aa.setStatus(11);
                            session.update(aa);
                        }
                        session.getTransaction().begin();
                   //     session.flush();
                        session.getTransaction().commit();
                        session.close();

                        queue.getMainQueue().addAll(list);
                        list.clear();


                    } else {
                        session.close();
                        try {
                            sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                } //
            }
            else {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//queue size
        }

    }

    private Stencil selectStencilByStructure(Structure structure,String next) {
        Query q=session.createQuery("from Duct where name_duct=:name");
        q.setParameter("name",next);
        q.setMaxResults(1);
        Duct duct= (Duct) q.list().get(0);
     //   System.out.println("duct= "+duct.getName()+"  stencil  "+structure.getId());

        q = session.createQuery("from Stencil where id_structure=:id_str and id_d_duct=:iduct");

        q.setParameter("id_str", structure);
        q.setParameter("iduct", duct);
        Stencil stencil= (Stencil) q.list().get(0);
    //    System.out.println("duct= "+duct.getName()+"  stencil  "+stencil.getStencil_entity());
        return stencil;
    }

    public String readStencil(String st, JSONObject jsonObject){
        ArrayList<String> parts=new ArrayList<String>();
        ArrayList<String>varibles=new ArrayList<String>();
        StringBuffer sb1=new StringBuffer();
        boolean isFirst=false;
        Map<String,String> map=new HashMap<String,String>() ;

        String[] wordArray = st.split("[\\s,.:!?]+");

        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("(#[a-z0-9]{1,10})");

        if(p.matcher(wordArray[0]).matches()){
            isFirst=true;
        }

        for (int i = 0; i < wordArray.length; i++) {
            Matcher matcher = p.matcher(wordArray[i]);
            if (matcher.find()) {
                sb.append(map.get(wordArray[i])+" ");
                parts.add(sb1.toString());
                varibles.add(wordArray[i]);
                sb1.delete(0,sb1.capacity());

            } else {
                sb.append(wordArray[i]+" ");
                sb1.append(wordArray[i]+" ");
            }

        }
        parts.add(sb1.toString());
        return   generateMessage (parts,varibles,isFirst,jsonObject);
    }
    public static String generateMessage(ArrayList<String> parts, ArrayList<String> varibles, boolean isFirst,JSONObject jsonObject){
        StringBuffer mes = new StringBuffer();
        String part="";
        String varible="";
        if(isFirst){
            for (int i=0;i<varibles.size();i++){
                try{
                    part=parts.get(i+1);
                }catch (Exception e){
                    part="";
                }
                try{
                    varible=jsonObject.get(varibles.get(i))+" ";
                }catch (Exception e){
                    varible="";
                }
                mes.append(varible).append(part);
            }
        }else if(!isFirst){
            for (int i=0;i<parts.size();i++){
                try{
                    part=parts.get(i);
                }catch (Exception e){
                    part="";
                }
                try{
                    varible=jsonObject.get(removeLastChar(varibles.get(i)))+" ";
                }catch (Exception e){
                    varible="";
                }
                mes.append(part).append(varible);
            }
        }
        return mes.toString();
    }
    public static String removeLastChar(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        return s.substring(1, s.length());
    }

}
/*
* List results = session.createCriteria(Cat.class)
    .setProjection( Projections.projectionList()
        .add( Projections.rowCount(), "catCountByColor" )
        .add( Projections.avg("weight"), "avgWeight" )
        .add( Projections.max("weight"), "maxWeight" )
        .add( Projections.groupProperty("color"), "color" )
    )
    .addOrder( Order.desc("catCountByColor") )
    .addOrder( Order.desc("avgWeight") )
    .list();
* */