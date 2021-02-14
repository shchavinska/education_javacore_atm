package atm;

import card.*;
import exceptions.*;
import operation.*;
import org.apache.log4j.*;
import utils.*;

public class AtmOperations {
    private final static Logger LOGGER = Logger.getLogger(AtmOperations.class);
    public enum OperationResult {
        BACKTOMENU, FINISH, UPDATEDATABASE
    }

    public static OperationResult showBalance(Card workingCard) throws InputExceptions{
        System.out.println("The balance on your card: " + workingCard.getBalance() + "$");
        System.out.println("Press 1 - to back to menu.\n" +
                "Press 2 - to finish.\n" +
                "Enter your choice, please: ");
        int selection = Input.inputInt();
        switch (selection) {
            case 1:
                return OperationResult.BACKTOMENU;
            case 2:
                System.out.println("Please don't forget your card.");
                return OperationResult.FINISH;
            default:
                throw new InputExceptions("Input incorrect.");
        }
    }

    public static OperationResult getMoneyMenu(Card workingCard) throws InputExceptions{
        while (true){
            System.out.println("Entre sum with you want to get. The sum should be multiple 50: ");
            System.out.println("Press 1 - to back to menu.\n" +
                    "Press 2 - to finish.");

            int selection = Input.inputInt();

            if (selection == 1){
                return OperationResult.BACKTOMENU;
            }
            else if(selection == 2){
                System.out.println("Please don't forget your card.");
                return OperationResult.FINISH;
            }
            else if(selection%50 == 0){
                return getMoney(workingCard, selection);
            }
            else{
                System.out.println("Your input incorrect. Try again.");
                continue;
            }
        }
    }

    public static OperationResult getMoney(Card workingCard, int selection) throws InputExceptions {

        if (workingCard.getBalance()>=selection){
            System.out.println("Do you want to take: " + selection + "$ ?\n" +
                    "Press 1 - to confirm.\nPress 2 - to go back.");
            int answer = Input.inputInt();
            if(answer == 1){
                double currentBalance = workingCard.getBalance() - selection;
                workingCard.setBalance(currentBalance);
                WriteReadFile receipt = new WriteReadFile();
                receipt.writeToFile("recipe.txt", selection + "$ was withdrawn from the account. Current balance: " + currentBalance);
                System.out.println(receipt.readFromFile("receipt.txt"));
                return OperationResult.UPDATEDATABASE;
            }
        }
        else{
            System.out.println("Sorry, don't have enough money on your balance.");
        }
        return OperationResult.BACKTOMENU;
    }
}
