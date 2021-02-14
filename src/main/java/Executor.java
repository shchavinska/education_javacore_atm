
import atm.*;
import exceptions.*;
import org.apache.log4j.*;

import java.io.IOException;

public class Executor {
    private final static Logger LOGGER = Logger.getLogger(Executor.class);

    public static void main(String[] args) throws IOException, InputExceptions {
        System.out.println("Please insert your card.");

        boolean card = true;

         if (card == true){
             int userCard = 1234;

             LOGGER.info("Card inserted.");
             ATM atm = new ATM();
             atm.dataInitialize(userCard);

             if(atm.validation() == true){
                 atm.show();
             }
         }
    }
}
