package com.common.controller;

import com.common.dao.entity.incoming.Autor;
import com.common.dao.entity.incoming.IncomingTask;
import com.common.dao.entity.incoming.Param;
import com.common.dao.entity.incoming.Recipient;
import com.common.dao.entity.queue.Queue;
import com.common.dao.entity.user.User;
import com.common.service.company.CompanyService;
import com.common.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;


@Controller
public class BasicController {
    @Autowired
    public UserService userService;
    @Autowired
    public CompanyService companyService;

    ArrayList<String> chanel = new ArrayList<>();
    int i = 1000;
    Logger logger = Logger.getLogger(String.valueOf(BasicController.class));
    @Autowired
    AmqpTemplate template;
    IncomingTask iTask = new IncomingTask();
    com.common.dao.entity.queue.Queue queue = new Queue();


    @RequestMapping(value = "/index")
    public ModelAndView index(Map<String, Object> map,
                              HttpServletRequest request) {

        map.put("User", userService.listContact(request
                .getUserPrincipal().getName()));
        System.out.println(request.getUserPrincipal().getName() + "   " + new Date());
        return new ModelAndView("/index");
    }


    @RequestMapping(value = {"/emit/{a}/{b}"}, method = RequestMethod.GET)
    @ResponseBody
    String queue1(HttpServletRequest request, @PathVariable("a") Integer a, @PathVariable("b") Integer b) {
        //      userService.search();
        //    System.out.println(request.getUserPrincipal().getName());


        long start = System.currentTimeMillis();
        for (int i = 0; i < a; i++) {
            byte[] data = SerializationUtils.serialize(generate(b));
            template.convertAndSend("getData", data);
        }
        long finish = System.currentTimeMillis();
        long timeConsumedMillis = finish - start;
        return timeConsumedMillis + "";
    }

    public IncomingTask generate(Integer b) {
        chanel.clear();
        Random r = new Random();
        int z = 5000000;
        // int x = 1000000 + r.nextInt() % 1000000;
        ArrayList<Recipient> list = new ArrayList<Recipient>();
        // list.add(new Recipient("someName", 1000000 + r.nextInt() % 1000000 + ""));
        for (int q = 0; q < b; q++) {

            list.add(new Recipient(1000000 + r.nextInt() % 1000000 + "", new Param("name", "someName")));
        }
        System.out.println("z= " + b);
        chanel.add("1");
        chanel.add("2");
        chanel.add("3");
        iTask.setId(i);
        iTask.setChanel(chanel);
        iTask.setAutor(new Autor("login", "password"));
        if ((2 + r.nextInt() % 2) % 2 == 0) {
            //    iTask.setDepartureTime(new Date((new Date().getTime() + 10/*00*//**60*/ * (120 + r.nextInt() % 120))));
        } else {
            //    iTask.setDepartureTime(null);
            iTask.setDepartureTime(new Date((new Date().getTime() + 10 * (120 + r.nextInt() % 120))));
        }//
        //iTask.setRelevant(new Date(new Date().getTime()+1000*60*60));
        iTask.setRelevant(new Date(new Date().getTime() + 1000 * 60 * ((20 + r.nextInt() % 20))));
        iTask.setLanguage("ua");
        iTask.setRecipientList(list);

        if ((3 + r.nextInt() % 2) % 2 == 0) {
            iTask.setPriority(i);
        } else {

            //    iTask.setPriority(0);
            iTask.setPriority(i);
        }


        iTask.setEvent(111);
        if ((3 + r.nextInt() % 2) % 2 == 0) {
            iTask.setLoop(0);
        } else {

            iTask.setLoop(0);
            //  iTask.setPriority(i);
        }


        i--;
        return iTask;
    }

    @RequestMapping(value = {"/", "/log**"}, method = RequestMethod.GET)
    public ModelAndView start(HttpServletRequest request) {
        userService.insertUser(new User("QWE","test",true,"test","test","test","test",1,companyService.getCompanyById(2), userService.getRoleById(1)));
        //userService.getContactsDictonary();

        //userService.getContactsByType();
        //companyService.getCompanies();
       // userService.search();
        return new ModelAndView("/log");
    }


    @RequestMapping(value = "/app/{data}", method = RequestMethod.GET)
    @ResponseBody
    public String getData(HttpServletRequest request, @RequestParam(value = "ID", defaultValue = "") String id) {
        System.out.println(id);
        String userAgent = request.getHeader("user-agent");
        userAgent += " " + " ";
        try {
            Cookie[] cookies = new Cookie[100];

            if (request.getCookies() != null) {
                cookies = request.getCookies();
            }
            for (int i = 0; i < cookies.length; i++) {
                if (cookies[i] != null) {
                    System.out.println(cookies[i].getValue());
                    userAgent += " /n" + cookies[i].getValue();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

/*
        try {
            if(request.getParts()!=null) {
                Iterator a = request.getParts().iterator();
                while (a.hasNext()) {
                    userAgent += " " + a.next();
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }*/
        Enumeration<String> headers = request.getHeaderNames();
        while ((headers.hasMoreElements())) {
            String a = request.getHeader(headers.nextElement());
            //   System.out.println(a);
            userAgent += " " + a;
        }
        //  userAgent+=" "+request.getHeader(request.getHeaderNames().nextElement());
        System.out.println(userAgent);
        return userAgent;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/emps",
            headers = "Accept=application/json")
    public String test() {

        return "";
    }

    @RequestMapping(value = "/wells/{apiValue}", method = RequestMethod.GET)
    public ResponseEntity<?> fetchWellData(@PathVariable String apiValue) {
        try {
            return new ResponseEntity<>("asd", HttpStatus.OK);
        } catch (Exception ex) {
            String errorMessage;
            errorMessage = ex + " <== error";
            return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
        }

    }
}









