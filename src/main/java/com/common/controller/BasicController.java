package com.common.controller;


import com.common.dao.entity.company.Company;
import com.common.dao.entity.message.CommonMessage;
import com.common.dao.entity.queue.Queue;
import com.common.dao.entity.stencil.Stencil;
import com.common.service.company.CompanyService;
import com.common.service.message.MessageService;
import com.common.service.skype.ISkypeService;
import com.common.service.task.TaskService;
import com.common.service.udp.ISendUDP;
import com.common.service.user.UserService;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;


@Controller
public class BasicController {
    @Autowired
    public UserService userService;
    @Autowired
    public CompanyService companyService;
    @Autowired
    TaskService taskService;
    @Autowired
    MessageService messageService;
    @Autowired
    ISkypeService skypeService;
    @Autowired
    ISendUDP sendUDP;

    ArrayList<String> chanel = new ArrayList<>();
    int i = 1000;
    Logger logger = Logger.getLogger(String.valueOf(BasicController.class));
    @Autowired
    AmqpTemplate template;
    com.common.dao.entity.queue.Queue queue = new Queue();


    @RequestMapping(value = "/userpage")
    public ModelAndView userpage(HttpServletRequest request)
    {
        ModelAndView model=new ModelAndView("userpage");

        return model;
    }

    @RequestMapping(value = {"/getStencilForTask/{json}"},method = RequestMethod.GET)
    public List getStencilForTask(HttpServletRequest request,@PathVariable(value = "json")String json){
        String username=request.getRemoteUser();
        JSONParser parser=new JSONParser();
        JSONObject jsonObject=null;
        try {
             jsonObject= (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return taskService.getStencilList((String)jsonObject.get("language"),(String)jsonObject.get("duct"),username);

    }

    @RequestMapping(value = "/adm")
    public ModelAndView adm(HttpServletRequest request) {
        return new ModelAndView("adm");
    }

    @RequestMapping(value = "/companies")
    public ModelAndView companies(HttpServletRequest request) {

        ModelAndView model = new ModelAndView("companies");
        model.addObject("companies", companyService.getAllCompanies());
        return model;
    }

    @RequestMapping(value = "/users")
    public ModelAndView users(HttpServletRequest request) {

        ModelAndView model = new ModelAndView("users");
        model.addObject("users", userService.getAllUsers());
        model.addObject("companies", companyService.getAllCompanies());
        return model;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String processRegistration(@ModelAttribute("companyForm") Company company,
                                      Map<String, Object> model, HttpServletRequest request) {

        companyService.addCompany(company);
        return "/companies";
    }

    @RequestMapping(value = "/index")
    public ModelAndView index(Map<String, Object> map,
                              HttpServletRequest request) {
        map.put("User", userService.listContact(request
                .getUserPrincipal().getName()));
        return new ModelAndView("/index");
    }

    @RequestMapping(value = {"/", "/log**"}, method = RequestMethod.GET)
    public ModelAndView start(HttpServletRequest request) {

        return new ModelAndView("/log");
    }

    @RequestMapping(value = {"/task"}, method = RequestMethod.GET)
    public ModelAndView taskForm(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("task");
        model.addObject("tasks", taskService.departuredList());
        return model;
    }

    @RequestMapping(value = {"/getStatistic/{id}"}, method = RequestMethod.GET)
    public
    @ResponseBody
    /*public*/ ArrayList<String> getStatisstic(HttpServletRequest request, @PathVariable(value = "id") String id) {
        System.out.println("try to show statistic " + id);
        ArrayList<String> result = taskService.getStatistic(Long.parseLong(id));
        return result;
    }

    @RequestMapping(value = {"/getInfoByContact/{contact}"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<CommonMessage> getStatisticByContact(@PathVariable(value = "contact") String contact, HttpServletRequest request) {
        System.out.println(contact);
        ArrayList<CommonMessage> list = messageService.getAllMessageByContact(contact);
        System.out.println(list.get(0).getStatistic());
        System.out.println("basicController listSize= " + list.size());
        return list;
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









