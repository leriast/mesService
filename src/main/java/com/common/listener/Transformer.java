package com.common.listener;

import com.common.dao.entity.message.Message;
import com.common.dao.entity.queue.IncomingInsertQueue;
import com.common.dao.entity.queue.InsertQueue;
import com.common.dao.entity.queue.Queue;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Task;
import com.common.dao.entity.user.User;
import com.common.service.task.TaskService;
import com.common.service.user.UserService;
import com.common.service.workingThread.Pool;
import org.hibernate.SessionFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


//@EnableRabbit
@Component
public class Transformer implements IListener {                    // startPool
    public InsertQueue insertQueue = new InsertQueue();
    @Autowired
    SessionFactory sessionFactory;
    @Autowired
    UserService userService;
    @Autowired
    TaskService taskService;
    int poolSize = 8;
    public IncomingInsertQueue incomingInsertQueue = new IncomingInsertQueue();
    Stencil stencil = new Stencil();


    //  Logger logger = Logger.getLogger(String.valueOf(RabbitMqListener.class));
    public Queue queue = new Queue();

    public void init(JSONArray jsonTask, User user,String filename) {
        new Pool(sessionFactory, poolSize);
        try {
           // Task task = null;
//            System.out.println(jsonTask);
//            
            Task task = new Task(userService.getUserByCompany("NP"), taskService.getStructure(), new String[]{"PUSH", "TELEGRAM", "SMS"}, filename, 1, (Language) taskService.getAllLanguages().get(0),
                    "[{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000},{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000},{\"frequence\":2,\"departuretime\":\"" + new Date(new Date().getTime() + 10000) + "\",\"delay\":5000}]");//,{"frequence":2,"departuretime":"" + new Date(new Date().getTime() + 10000) + "","delay":5000}

            
            
            
            taskService.insertTask(task);

            stencil = taskService.getStencilByTask(taskService.getDuctByName(task.getAlgoritm()[0]), task.getStructure());

            JSONArray algoritmArr = new JSONArray();
            for (String alg : task.getAlgoritm()) {
                JSONObject duct = new JSONObject();
                duct.put(alg, 0);
                algoritmArr.add(duct);
            }
            for (Object mess : jsonTask) {
                JSONObject jsonMessage = (JSONObject) mess;
                String mm = stencil.getStencil_entity();
                for (Object varibles : jsonMessage.keySet()) {
                    mm = mm.replaceAll("(\\{" + varibles/*.substring(1,b.length()-1)*/ + "\\})", String.valueOf(jsonMessage.get(varibles)));
                }
                incomingInsertQueue.getMainQueue().add(new Message(1, new Date(), new Date(), task.getAlgoritm(), jsonMessage.toJSONString(), mm, "address", task, 1, task.getParams(), algoritmArr.toJSONString()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(incomingInsertQueue.getMainQueue().size() + "      stop rabbit json   " + new Date());
    }
}
