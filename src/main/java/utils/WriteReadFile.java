package utils;

import org.apache.log4j.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class WriteReadFile {
    private final static Logger LOGGER = Logger.getLogger(WriteReadFile.class);

    public void writeToFile (String fileName, String value) {
        LOGGER.info("writeToFile caused");
        try{
            File file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            PrintWriter pw = new PrintWriter(file);
            pw.println(value);
            pw.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public String readFromFile (String path) {
        LOGGER.info("readFromFile caused");
        String res = "";
        BufferedReader rf = null;
        try{
            rf = new BufferedReader(new FileReader(path));
            String line;
            while ((line = rf.readLine()) != null){
                res += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                rf.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        return res;
    }
}
