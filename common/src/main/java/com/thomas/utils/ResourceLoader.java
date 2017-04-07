package com.thomas.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by hadoop on 4/7/17.
 */
public class ResourceLoader {
    public static void cmdParser(Properties props, String []args) {
        for (String str: args) {
            String [] d = str.split("=");
            props.put(d[0], d[1]);
        }
    }

    public static void propsFileParser(Properties props, String path) {
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(path));
            props.load(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
