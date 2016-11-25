package com.common.controller;

import com.common.service.file.ReadData;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

/**
 * Created by root on 11/14/16.
 */
@Controller
class FileUploadController {
    public ReadData readData = new ReadData();
    @Autowired
    String path;
    @Autowired
    AmqpTemplate template;

    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String setupUploadFile() {
        return "upload";
    }

    @RequestMapping(value = "/uploadFile")
    public String processUploadPreview(
            @RequestParam("file") MultipartFile file
    ) {
        String fileName=UUID.randomUUID()+".csv";
        System.out.println(file.getOriginalFilename() + "      " + path + "   " + file.getContentType());
        try {
            FileOutputStream out = new FileOutputStream(path + fileName);
            out.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] data = SerializationUtils.serialize(readData.readData(path,fileName));
        template.convertAndSend("json", data);
        return "redirect:index";
    }
}