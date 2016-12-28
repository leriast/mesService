package com.common.controller;

import com.common.listener.IListener;
import com.common.service.file.ReadData;
import com.common.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by root on 11/14/16.
 */
@Controller
class FileUploadController {
    public ReadData readData = new ReadData();
    @Autowired
    IListener listener;
    @Autowired
    @Qualifier("pathToSave")
    String path;
    @Autowired
    public UserService userService;



    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String setupUploadFile() {
        return "upload";
    }

    //@Secured("ROLE_ADMIN")


}