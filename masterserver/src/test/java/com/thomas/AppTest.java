package com.thomas;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AppTest {
    public static void main(String []args) {
        Properties prop = new Properties();
        try{
            InputStream in = new BufferedInputStream(new FileInputStream("conf/master.properties"));
            prop.load(in);
            System.out.println(prop.getProperty("worker"));
            for (Object key: prop.keySet()) {
                String k = (String)key;
                System.out.println(k + " " + prop.getProperty(k));
            }
            in.close();

            /*FileOutputStream oFile = new FileOutputStream("b.properties", true);
            prop.setProperty("phone", "10086");
            prop.store(oFile, "The New properties file");
            oFile.close();*/
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}
