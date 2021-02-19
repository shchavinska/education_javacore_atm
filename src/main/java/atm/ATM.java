package atm;

import card.*;
import exceptions.*;
import operation.*;
import org.apache.log4j.Logger;
import utils.*;

import java.io.*;


public class ATM {
    private final static Logger LOGGER = Logger.getLogger(ATM.class);
    JsonConverter converter = new JsonConverter();
    DataBase cards = null;
    Card workingCard = null;

    public ATM() throws IOException {
        cards = converter.readJsonToCard("cardtest.json");
    }

    public boolean dataInitialize(int userCard) {
        for (Card checkCard : cards.getCards()){
            if(checkCard.getNumber() == userCard){
                workingCard = checkCard;
                LOGGER.info("Card found.");
                return true;
            }
        }
        if (workingCard == null){
            LOGGER.info("Card not found.");
            System.out.println("Sorry can't work with your card.");
        }
        return false;
    }

    public boolean validation() throws InputExceptions {

        boolean validStatus = CardValidator.checkStatus(workingCard);
        boolean validPin = CardValidator.checkPin(workingCard);

        if (validStatus == true && validPin == true) {
            LOGGER.info("Validation successful.");
            return true;
        }
        else{
            LOGGER.info("Validation unsuccessful.");
            System.out.println("Sorry, your card is blocked. Please contact with the manager of your bank.");
            System.out.println("Please don't forget your card.");
            workingCard.setStatus("block");
            converter.convertToJsonFile(cards, "cardtest.json");
            return false;
        }
    }

    public void showAtmMenu() {
        LOGGER.info("Program started. Show ATM menu.");
        AtmOperations.OperationResult action = AtmOperations.OperationResult.FINISH;
        boolean loopCheck = true;

        while(loopCheck) {
            System.out.println("Press 1 - to check balance.\n" +
                    "Press 2 - to get cash.\n" +
                    "Press 3 - to add money.\n" +
                    "Press 4 - to finish.\n" +
                    "Entre your choice please: ");

            try {
                int selection = Input.inputInt();
                switch (selection) {
                    case 1:
                        action = AtmOperations.showBalance(workingCard);
                        break;
                    case 2:
                        action = AtmOperations.getMoneyMenu(workingCard);
                        break;
                    case 3:
                        action = AtmOperations.addMoney(workingCard);
                        break;
                    case 4:
                        System.out.println("Finish. Please don't forget your card.");
                        break;
                }
            } catch (InputExceptions | IOException e) {
                LOGGER.error("Input incorrect: " + e.getMessage());
            }

            switch (action){
                case BACKTOMENU:
                    loopCheck = true;
                    break;
                case FINISH:
                    loopCheck = false;
                    break;
                case UPDATEDATABASE:
                    converter.convertToJsonFile(cards, "cardtest.json");
                    loopCheck = false;
                    break;
            }
        }
    }
}
