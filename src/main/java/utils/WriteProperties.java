package utils;

import java.io.*;
import java.util.Properties;


public class WriteProperties {
    FileOutputStream fileStream = null;

    public WriteProperties(String path) throws FileNotFoundException {
        fileStream = new FileOutputStream(path);
    }

    public void setValueToProperties (Integer key, Integer value) {
        Properties properties = new Properties();
        try{
            properties.setProperty(Integer.toString(key), Integer.toString(value));
            properties.store(fileStream, "");

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        fileStream.close();
    }
}
