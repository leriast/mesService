package com.common.controller;

import com.common.service.ReadData;
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
    @Autowired
    String path;
  public   ReadData readData=new ReadData();
    @RequestMapping(value = "/file", method = RequestMethod.GET)
    public String setupUploadFile(){
        return "upload";
    }
    @RequestMapping(value = "/uploadFile")
    public String processUploadPreview(
            @RequestParam("file") MultipartFile file
    ){
        System.out.println(file.getOriginalFilename()+"      "+path+"   "+file.getContentType());
        try {
            FileOutputStream out = new FileOutputStream(path+ UUID.randomUUID()+
                    ".csv");
            out.write(file.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        readData.readData(path);
        return "redirect:index";
    }
}