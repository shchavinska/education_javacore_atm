package operation;

import exceptions.InputExceptions;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Input {
    private final static Logger LOGGER = Logger.getLogger(Input.class);

    public static int inputInt() throws InputExceptions {
        LOGGER.info("InputInt calling.");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            return Integer.parseInt(reader.readLine());
        }catch (IOException e){
            throw new InputExceptions(e.getMessage());
        }catch (NumberFormatException e){
            throw new InputExceptions("Can't parse integer.");
        }
    }
}
