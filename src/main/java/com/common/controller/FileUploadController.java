package com.common.controller;

import com.common.listener.IListener;
import com.common.service.file.ReadData;
import com.common.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

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

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String processUploadPreview(
            @RequestParam("file") MultipartFile file, HttpServletRequest request
    ) {
        //   new UDPClient().start();
        System.out.println("uploadfile");
        String fileName = UUID.randomUUID() + ".csv";

        System.out.println(file.getOriginalFilename() + "      " + path + "   " + file.getContentType());

        try {
            FileOutputStream out = new FileOutputStream(path + fileName);
            out.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //readData.readData(path,fileName);
        listener.init(readData.readData(path, fileName), userService.getUserByLogin(request.getRemoteUser()),fileName);
//        byte[] data = SerializationUtils.serialize(readData.readData(path,fileName));
        return "redirect:index";
    }
}