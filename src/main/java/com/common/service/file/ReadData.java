package com.common.service.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by root on 11/14/16.
 */
/*

class for reading from csv
 */
public class ReadData {
    int someMagic=0;
    private static final char DEFAULT_SEPARATOR = ',';
    private static final char DEFAULT_QUOTE = '"';
    private String path;
    public void readData(String path1) {
    path=path1;
        final File folder = new File(path1);
        try {
            listFilesForFolder(folder);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void listFilesForFolder(final File folder) throws FileNotFoundException {
        String word = "";
        String title="";
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                String csvFile = path+fileEntry.getName();
                Scanner scanner = new Scanner(new File(csvFile));
                while (scanner.hasNext()) {
                    List<String> line=new ArrayList<>();
                    line.add(scanner.nextLine());

                    if (line != null) for (int i = 0; i < line.size(); i++) {word="";
                        if(someMagic==0)     {                  title += line.get(i);someMagic++;}
                        else {word+=" "+line.get(i);}
                    }
                    System.out.println(title);System.out.println(word);
                }
                scanner.close();
                new DeleteFile(fileEntry);

            }


        }
    }
}


