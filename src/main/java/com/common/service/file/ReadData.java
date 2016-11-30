package com.common.service.file;
import com.common.dao.entity.queue.IncomingInsertQueue;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by root on 11/14/16.
 */
/*

class for reading from csv
 */
public class ReadData {
    IncomingInsertQueue queue=new IncomingInsertQueue();
    private String path;
    private String fileName;

    public JSONArray readData(String path, String fileName) {
        this.path = path;
        this.fileName = fileName;
        try {
          return  listFilesForFolder(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public JSONArray listFilesForFolder(String fileName) throws FileNotFoundException {
        System.out.println("start parse csv   "+new Date());
        ArrayList<String> word = new ArrayList<String>();
        String autorParam = "";
        ArrayList<String> title = new ArrayList<String>();
        JSONObject obj = new JSONObject();
        JSONObject obj0 = new JSONObject();
        JSONArray obj1 = new JSONArray();
        int propertyRow = 0;
        BufferedReader fileReader = null;
        final String DELIMITER = ",";
        try {
            String line = "";
            fileReader = new BufferedReader(new FileReader(path + fileName));
            while ((line = fileReader.readLine()) != null) {
                if (propertyRow == 0) {
                    propertyRow++;
                    String[] tokens = line.split(DELIMITER);
                    for (String token : tokens) {
                        autorParam += (token + " ");
                    }
                } else if (propertyRow == 1) {
                    propertyRow++;
                    String[] tokens = line.split(DELIMITER);
                    for (String token : tokens) {
                        title.add(token);
                    }
                } else {
                    String[] tokens = line.split(DELIMITER);
                    for (String token : tokens) {
                        word.add(token);
                    }
                }
            }
            try {
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        int x = 0;
        int y = word.size() / title.size();
        for (int i = 0; i < y; i++) {
            for (String t : title) {
                obj0.put(t, word.get(x));
                x++;
            }
       //     queue.getMainQueue().add(new JSONT(obj0.toJSONString()));


            obj1.add(obj0.clone());

         }
        System.out.println("   end parse, start return    "+new Date());
        return obj1;
    }
}


