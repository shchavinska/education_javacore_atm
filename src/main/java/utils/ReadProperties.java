package utils;

import java.io.*;
import java.util.*;


public class ReadProperties {
    public Integer getValueFromProperties (String path, Integer key) {
        Properties properties = new Properties();
        try{
            FileInputStream fileStream = new FileInputStream(path);
            properties.load(fileStream);
            fileStream.close();
            return Integer.parseInt(properties.getProperty(Integer.toString(key)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
