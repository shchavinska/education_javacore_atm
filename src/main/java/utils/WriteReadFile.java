package utils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class WriteReadFile {
    public void writeToFile (String fileName, String value) {

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
