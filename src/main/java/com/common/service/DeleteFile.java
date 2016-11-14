package com.common.service;

import java.io.File;

/**
 * Created by root on 11/14/16.
 */
public class DeleteFile {

    public DeleteFile(File file){
        if(file.delete()){
            System.out.println(file.getName() + " is deleted!");
        }else{
            System.out.println("Delete operation is failed.");
        }
    }
}
