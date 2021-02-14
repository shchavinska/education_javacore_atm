package operation;
import exceptions.*;
import card.Card;
import org.apache.log4j.*;

public class CardValidator {
    private final static Logger LOGGER = Logger.getLogger(CardValidator.class);

    public static boolean checkStatus(Card workingCard){
        LOGGER.info("Checking card status.");
        return ("unblock".equals(workingCard.getStatus()));
    }

    public static boolean checkPin(Card workingCard) throws InputExceptions {
        LOGGER.info("Checking card PIN.");
        for(int tries =3; tries>0 ; tries-=1){
            int pin = Input.inputInt();
            if (pin == workingCard.getPassword()){
                return true;
            }
        }
        return false;
    }
}
