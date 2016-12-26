package com.common.controller;


import com.common.dao.entity.company.Company;
import com.common.dao.entity.journal.Journal;
import com.common.dao.entity.message.CommonMessage;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.user.User;
import com.common.listener.IListener;
import com.common.service.company.CompanyService;
import com.common.service.file.ReadData;
import com.common.service.message.MessageService;
import com.common.service.skype.ISkypeService;
import com.common.service.task.TaskService;
import com.common.service.udp.ISendUDP;
import com.common.service.user.UserService;
import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;


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
    @Autowired
    @Qualifier("pathToSave")
    String path;
    @Autowired
    IListener listener;
    public ReadData readData = new ReadData();

    JSONParser parser = new JSONParser();

    Logger logger = Logger.getLogger(String.valueOf(BasicController.class));
//    @Autowired
//    AmqpTemplate template;
//    com.common.dao.entity.queue.Queue queue = new Queue();


    @RequestMapping(value = "/userpage")
    public ModelAndView userpage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("userpage");
        return model;
    }

    @RequestMapping(value = {"/getStencilForTask/{json}"}, method = RequestMethod.GET)
    public List getStencilForTask(HttpServletRequest request, @PathVariable(value = "json") String json) {
        String username = request.getRemoteUser();
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = null;
        try {
            jsonObject = (JSONObject) parser.parse(json);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return taskService.getStencilList((String) jsonObject.get("language"), (String) jsonObject.get("duct"), username);

    }

    @RequestMapping(value = "/adm")
    public ModelAndView adm(HttpServletRequest request) {
        //VKService vk=new VKService();
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
        getPrincipal();
        return new ModelAndView("/index");
    }

    @RequestMapping(value = {"/", "/log**"}, method = RequestMethod.GET)
    public ModelAndView start(HttpServletRequest request) {
//
//        Language language = (Language) taskService.getAllLanguages().get(0);
//        Structure structure= taskService.getStructure();
//        Duct duct= (Duct) taskService.getAllDucts().get(0);
//        Stencil stn= (Stencil) taskService.getStencilById(1);
//        System.out.println("stncl     "+stn.getCreator().getUsername());
//        System.out.println("lng     "+language.getCreator().getUsername());
//        System.out.println("str     "+structure.getCreator().getUsername());
//        System.out.println("duct     "+duct.getCreator().getUsername());
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
        ArrayList<String> result = taskService.getStatistic(Long.parseLong(id));
        return result;
    }

    @RequestMapping(value = {"/getInfoByContact/{contact}"}, method = RequestMethod.GET)
    public
    @ResponseBody
    ArrayList<CommonMessage> getStatisticByContact(@PathVariable(value = "contact") String contact, HttpServletRequest request) {
        ArrayList<CommonMessage> list = messageService.getAllMessageByContact(contact);
        ;
        return list;
    }

    @RequestMapping(value = "/app/{data}", method = RequestMethod.GET)
    @ResponseBody
    public String getData(HttpServletRequest request, @RequestParam(value = "ID", defaultValue = "") String id) {
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

    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }

    @RequestMapping(value = "/newUser", method = RequestMethod.GET)
    public String saveRegistration() {

        User user = userService.getUserById(1);
        user.setIdUser(100);
        user.setUsername("login");
        user.setPassword("12345");
        userService.insertUser(user);


        return "adm";
    }

    @RequestMapping(value = "/j_spring_security_logout", method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:log";
    }


    @RequestMapping(value = "/event")
    public ModelAndView Event(HttpServletRequest request, HttpServletResponse response) {
        JSONObject arr = new JSONObject();
        JSONObject arr1 = new JSONObject();
        JSONObject arr3 = new JSONObject();
        JSONObject arr2 = new JSONObject();
        ModelAndView model = new ModelAndView();

        List<Language> languages = taskService.getAllLanguages();
        List<Structure> str = taskService.getAllStructureById(userService.getUserByLogin(request.getUserPrincipal().getName()));
        List<Duct> ducts = taskService.getAllDucts();
        for (Language l : languages) {
            arr.put(l.getId(), l.getName());
        }
        for (Structure l : str) {
            arr1.put("1", l.getId());
            arr1.put("2", l.getName());
            arr1.put("3", l.getAlgoritm());
            arr1.put("4", l.getParams());
            arr1.put("5", l.getId_structure());

        }
        for (Duct l : ducts) {
            arr3.put(l.getId(), l.getName());
        }
        arr2.put("duct", arr3.clone());
        arr2.put("structure", arr1.clone());
        arr2.put("language", arr.clone());
        arr2.put("priority", userService.getUserByLogin(request.getRemoteUser()).getPriority());
        model.addObject("R2D2", arr2);
        return model;
    }

    @RequestMapping(value = "/eventStencil", method = RequestMethod.POST)
    public
    @ResponseBody
    String eventStencil(/*@RequestParam("json") String json,*/HttpServletRequest request, @RequestBody String preJson) {
        System.out.println(" " + preJson);
        JSONObject incomingjson = null;
        JSONObject incomingDuct;
        JSONObject incomingDuctsParams;
        long delivery_time=0;
        long delay;
        long ttl = 0;
        int priority=0;

        try {
            incomingjson = (JSONObject) parser.parse(preJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        incomingDuct = (JSONObject) incomingjson.get("duct");
        try {
            for (Object chanel_ : incomingDuct.keySet()) {
                String chanel = (String) chanel_;
                incomingDuctsParams = (JSONObject) incomingDuct.get(chanel);
                System.out.println(chanel + "        " + incomingDuctsParams);
                Date date=new Date();
                if (incomingDuctsParams.get("delivery_time") != null) {
                     delivery_time=  Long.parseLong(String.valueOf(incomingDuctsParams.get("delivery_time")));
                    date.setTime(delivery_time);
                    System.out.println("delivery_time   "+date);
                }
                if (incomingDuctsParams.get("delay") != null) {
                     delay =  Long.parseLong(String.valueOf(incomingDuctsParams.get("delay")));
                    date.setTime(delay);
                    System.out.println("delay   "+date);
                }
                if (incomingDuctsParams.get("time_to_live") != null) {
                    ttl = Long.parseLong(String.valueOf(incomingDuctsParams.get("time_to_live")));
                    date.setTime(ttl);
                    System.out.println("ttl   "+date);
                }
                if (incomingDuctsParams.get("duct_id") != null) {
                    long id_duct = Long.parseLong(String.valueOf(incomingDuctsParams.get("duct_id")));
                    System.out.println("duct   "+id_duct);
                }
                if (incomingDuctsParams.get("template_id") != null) {
                    long id_stencil = Long.parseLong(String.valueOf(incomingDuctsParams.get("template_id")));
                    System.out.println("stencil   "+id_stencil);
                }
                if (incomingDuctsParams.get("template_body") != null) {
                    String stencilEntity = String.valueOf(incomingDuctsParams.get("template_body"));
                    System.out.println("entity   "+stencilEntity);
                }
                if (incomingDuctsParams.get("priority") != null) {
                    priority = Integer.parseInt(String.valueOf(incomingDuctsParams.get("priority")));
                    System.out.println("priority   "+priority);
                }
                if (incomingDuctsParams.get("lang") != null) {
                    long id_language = Long.parseLong(String.valueOf(incomingDuctsParams.get("lang")));
                    System.out.println("language   "+id_language);
                }
                if (incomingDuctsParams.get("frequency") != null) {
                    long frequency = Long.parseLong(String.valueOf(incomingDuctsParams.get("frequency")));
                    System.out.println("freuence   "+frequency);
                }
                User currentUser=userService.getUserByLogin(request.getRemoteUser());
                if(priority!=0 && (priority-currentUser.getPriority())>0){
                    int count = (int) ((ttl - delivery_time) / (priority - currentUser.getPriority()));
                    for(int i=1;i<priority-currentUser.getPriority();i++){
                        date.setTime(delivery_time+(count*i));
                        taskService.insertJournal(new Journal(date,0,priority-i,246380413));
                        System.out.println(date);
                        System.out.println(count);
                        System.out.println(i);
                    }
//
//
//                    System.out.println(count);
//                    long newdate=delivery_time+count;
//                    date.setTime(newdate);
//                    Date tesst=new Date();
//                    tesst.setTime(delivery_time);
//                    System.out.println(tesst+"     /      "+date);
                }
//                else {
//
//                    System.out.println(false);
//                }
                //taskService.insertJournal()




            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "ok";
    }

    //Secure
    @RequestMapping(value = "/getStencils", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONArray getStencils(HttpServletRequest request, @RequestBody String preJson) {
//        System.out.println("prejson"+preJson);
        JSONObject obj = new JSONObject();
        JSONArray arr = new JSONArray();
        List<Stencil> list = null;
        try {
            list = taskService.getStencilList("UA", "PUSH", request.getUserPrincipal().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (Stencil st : list) {
            obj.put("id", st.getId());
            obj.put("val", st.getName());
        }
//        System.out.println(obj);
        arr.add(obj);
        return arr;
    }


    @RequestMapping(value = "/getStencilEntity", method = RequestMethod.POST)
    public
    @ResponseBody
    JSONObject getStencilEntity(HttpServletRequest request, @RequestBody String preJson) {

        JSONObject result = new JSONObject();
        result.put("data", taskService.getStencilById(3).getStencil_entity());
        //  System.out.println(result);
        return result;
    }
}









