package com.common.service.file;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * Created by root on 11/14/16.
 */
@Component
public class DeleteFile {
public DeleteFile(){}
    public DeleteFile(File file){
        System.out.println(file);
        file.delete();
        if(true){
            System.out.println(file.getName() + " is deleted!");
        }else{
            System.out.println("Delete operation is failed.");
        }
        print();
    }
    public void print(){}
}
