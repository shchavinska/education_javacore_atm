
import atm.*;
import exceptions.*;
import operation.*;
import org.apache.log4j.*;

import java.io.IOException;

public class Executor {
    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static void main(String[] args) throws IOException, InputExceptions {
        System.out.println("Please insert your card.");
        int userCard = Input.inputInt();
        if (userCard >0){
             LOGGER.info("Card inserted.");
             ATM atm = new ATM();
             if (atm.dataInitialize(userCard)){
                 if(atm.validation()){
                     atm.showAtmMenu();
                 }
             }
         }
    }
}
