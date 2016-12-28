package com.common.controller;


import com.common.dao.entity.company.Company;
import com.common.dao.entity.journal.Journal;
import com.common.dao.entity.message.CommonMessage;
import com.common.dao.entity.stencil.Duct;
import com.common.dao.entity.stencil.Stencil;
import com.common.dao.entity.task.Language;
import com.common.dao.entity.task.Structure;
import com.common.dao.entity.task.Task;
import com.common.dao.entity.task.WrapperTaskStructure;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

        //  Language language=taskService.getLanguageByName("blabla");
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
        readRequest(preJson, request);
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


    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String processUploadPreview(
            @RequestParam("file") MultipartFile file, @RequestParam("data") String prejosn, HttpServletRequest request/*, @RequestBody String preJson*/
    ) {

        WrapperTaskStructure wrapperTaskStructure=readRequest(prejosn, request);
        Task task=wrapperTaskStructure.getTask();
        Structure structure=wrapperTaskStructure.getStructure();
        //   new UDPClient().start();
    //    System.out.println("uploadfile");

        // System.out.println(preJson);
        String fileName = UUID.randomUUID() + ".csv";

    //    System.out.println(file.getOriginalFilename() + "      " + path + "   " + file.getContentType());

        try {
            FileOutputStream out = new FileOutputStream(path + fileName);
            out.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //readData.readData(path,fileName);
    //    System.out.println("try to start lisener    ");
        //      listener.init(readData.readData(path, fileName), userService.getUserByLogin(request.getRemoteUser()),fileName);

//        byte[] data = SerializationUtils.serialize(readData.readData(path,fileName));
        return "redirect:index";
    }


    public WrapperTaskStructure readRequest(String preJson, HttpServletRequest request) {
        int algItter=0;

        Task task = new Task();
        Structure structure = new Structure();
        Language language;
        User user = userService.getUserByLogin(request.getRemoteUser());

        JSONObject incomingjson = null;
        JSONObject incomingDuct;
        JSONObject incomingDuctsParams;

        JSONArray params=new JSONArray();

        long delivery_time = 0;
        long delay;
        long ttl = 0;
        int priority = 0;

        try {
            incomingjson = (JSONObject) parser.parse(preJson);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        language = taskService.getLanguageByName((String) incomingjson.get("language"));
        structure.setName((String) incomingjson.get("label"));
        structure.setLanguage(language);
        structure.setCreator(user);
        structure.setCompany(user.getCompany());
        task.setUser(user);
        task.setLanguage(language);
        //task.setStructure();
        incomingDuct = (JSONObject) incomingjson.get("duct");
        String[] algoritm=new String[incomingDuct.size()];

        try {
            for (Object chanel_ : incomingDuct.keySet()) {
                JSONObject chanelParams=new JSONObject();
                String chanel = (String) chanel_;
                algoritm[algItter]=chanel;
                algItter++;
                incomingDuctsParams = (JSONObject) incomingDuct.get(chanel);

                Date date = new Date();
                if (incomingDuctsParams.get("delivery_time") != null) {
                    delivery_time = Long.parseLong(String.valueOf(incomingDuctsParams.get("delivery_time")));
                    date.setTime(delivery_time);
                    if(algItter==1){
                        task.setDepartureTime(date);
                    }
                    chanelParams.put("departuretime",date);
                }
                if (incomingDuctsParams.get("delay") != null) {
                    delay = Long.parseLong(String.valueOf(incomingDuctsParams.get("delay")));
                    chanelParams.put("delay",delay);
                }
                if (incomingDuctsParams.get("time_to_live") != null) {
                    ttl = Long.parseLong(String.valueOf(incomingDuctsParams.get("time_to_live")));
                    date.setTime(ttl);
                    if(algItter==algoritm.length){
                        task.setRelevant_time(date);
                    }
                }
                if (incomingDuctsParams.get("duct_id") != null) {
                    long id_duct = Long.parseLong(String.valueOf(incomingDuctsParams.get("duct_id")));
                }
                if (incomingDuctsParams.get("template_id") != null) {
                    long id_stencil = Long.parseLong(String.valueOf(incomingDuctsParams.get("template_id")));
                }
                if (incomingDuctsParams.get("template_body") != null) {
                    String stencilEntity = String.valueOf(incomingDuctsParams.get("template_body"));
                }
                if (incomingDuctsParams.get("priority") != null) {
                    priority = Integer.parseInt(String.valueOf(incomingDuctsParams.get("priority")));
                }
                if (incomingDuctsParams.get("lang") != null) {
                    long id_language = Long.parseLong(String.valueOf(incomingDuctsParams.get("lang")));
                }
                if (incomingDuctsParams.get("frequency") != null) {
                    long frequency = Long.parseLong(String.valueOf(incomingDuctsParams.get("frequency")));
                    chanelParams.put("frequence",frequency);
                }
                params.add(chanelParams);




                /*
                *
                * create record in journal nedds to up priority
                *
                * */
                if (priority != 0 && (priority - user.getPriority()) > 0) {
                    int count = (int) ((ttl - delivery_time) / (priority - user.getPriority()));
                    for (int i = 1; i < priority - user.getPriority(); i++) {
                        date.setTime(delivery_time + (count * i));
                        taskService.insertJournal(new Journal(date, 0, priority - i, 246380413));
                        System.out.println(date);
                        System.out.println(count);
                        System.out.println(i);
                    }
                }
            }


            structure.setAlgoritm(algoritm);
            task.setAlgoritm(algoritm);

            if(priority>user.getPriority()){
                structure.setPriority(priority);
                task.setPriority(priority);
            }else {
                structure.setPriority(user.getPriority());
                task.setPriority(priority);
            }
            structure.setParams(params.toJSONString());
            task.setParams(params.toJSONString());


            System.out.println("str    "+structure.toString());
            System.out.println("task    "+task.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new WrapperTaskStructure(task,structure);
    }
}









