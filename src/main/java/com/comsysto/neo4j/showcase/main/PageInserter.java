package com.comsysto.neo4j.showcase.main;

import java.io.*;

/**
 * Created by okyrych on 1/23/14.
 */
public class PageInserter {

    public static void main(String[] args) throws IOException {
        File file = new File("web-Google.txt");
        BufferedReader bf = new BufferedReader(new FileReader(file));
        String line = bf.readLine();
        String [] ids = line.split("\t");
        for(String id: ids) {
            System.out.println(id);
        }

//        while ((line = bf.readLine()) != null) {
//            System.out.println(line);
//        }
    }
}
