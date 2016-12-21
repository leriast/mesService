package com.common.listener;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.IncomingInsertQueue;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Task;
import com.common.service.task.TaskService;
import com.common.service.user.UserService;
import com.common.service.workingThread.Pool;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


//@EnableRabbit
@Component
public class Transformer implements IListener {
    int a = 0;                      // startPool
    public InsertQueue insertQueue = new InsertQueue();
    @Autowired
    SessionFactory sessionFactory;
    //    @Autowired
//    AmqpTemplate template;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    int poolSize = 10;
    public IncomingInsertQueue incomingInsertQueue = new IncomingInsertQueue();
    Stencil stencil = new Stencil();


    //  Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    public Queue queue = new Queue();
    //   public QuartzEx quartz;
    ArrayList<Message> list = new ArrayList<>();

    public void init(JSONArray obj) {
//        if (a == 0) {
            new Pool(sessionFactory, poolSize);
//            a = 1;
//        }
        JSONArray obj1 = obj;
        try {
            Task task = new Task(userService.getUserByCompany("NP"), taskService.getStructure(), new String[]{"PUSH", "TELEGRAM", "SMS"}, obj1.toJSONString(), 1, (Language) taskService.getAllLanguages().get(0),
                    "[{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000},{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000},{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000}]");//,{"frequence":2,"departuretime":"" + new Date(new Date().getTime() + 10000) + "","delay":5000}

            taskService.insertTask(task);
            stencil = taskService.getStencilByTask(taskService.getDuctByName(task.getAlgoritm()[0]), task.getStructure());

            JSONArray arr = new JSONArray();
            for (String alg : task.getAlgoritm()) {
                JSONObject a = new JSONObject();
                a.put(alg, 0);
                arr.add(a);
            }
            for (Object o : obj1) {
                JSONObject q = (JSONObject) o;

                incomingInsertQueue.getMainQueue().add(new Message(1, new Date(), new Date(), task.getAlgoritm(), q.toJSONString(), readStencil(stencil.getStencil_entity(), q), "address", task, 1, task.getParams(), arr.toJSONString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(incomingInsertQueue.getMainQueue().size() + "      stop rabbit json   " + new Date());
    }

    public String readStencil(String st, JSONObject jsonObject) {
        ArrayList<String> parts = new ArrayList<String>();
        ArrayList<String> varibles = new ArrayList<String>();
        StringBuffer sb1 = new StringBuffer();
        boolean isFirst = false;
        Map<String, String> map = new HashMap<String, String>();

        String[] wordArray = st.split("[\\s,.:!?]+");

        StringBuffer sb = new StringBuffer();
        Pattern p = Pattern.compile("(#[a-z0-9]{1,10})");

        if (p.matcher(wordArray[0]).matches()) {
            isFirst = true;
        }

        for (int i = 0; i < wordArray.length; i++) {
            Matcher matcher = p.matcher(wordArray[i]);
            if (matcher.find()) {
                sb.append(map.get(wordArray[i]) + " ");
                parts.add(sb1.toString());
                varibles.add(wordArray[i]);
                sb1.delete(0, sb1.capacity());

            } else {
                sb.append(wordArray[i] + " ");
                sb1.append(wordArray[i] + " ");
            }

        }
        parts.add(sb1.toString());
        return generateMessage(parts, varibles, isFirst, jsonObject);
    }

    public static String generateMessage(ArrayList<String> parts, ArrayList<String> varibles, boolean isFirst, JSONObject jsonObject) {
        StringBuffer mes = new StringBuffer();
        String part = "";
        String varible = "";
        if (isFirst) {
            for (int i = 0; i < varibles.size(); i++) {
                try {
                    part = parts.get(i + 1);
                } catch (Exception e) {
                    part = "";
                }
                try {
                    varible = jsonObject.get(varibles.get(i)) + " ";
                } catch (Exception e) {
                    varible = "";
                }
                mes.append(varible).append(part);
            }
        } else if (!isFirst) {
            for (int i = 0; i < parts.size(); i++) {
                try {
                    part = parts.get(i);
                } catch (Exception e) {
                    part = "";
                }
                try {
                    varible = jsonObject.get(removeLastChar(varibles.get(i))) + " ";
                } catch (Exception e) {
                    varible = "";
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
