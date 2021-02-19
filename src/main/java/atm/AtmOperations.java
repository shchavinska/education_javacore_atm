package atm;

import card.*;
import currency.*;
import exceptions.*;
import operation.*;
import org.apache.log4j.*;
import utils.*;

import java.io.*;


public class AtmOperations {
    private final static Logger LOGGER = Logger.getLogger(AtmOperations.class);

    public enum OperationResult {
        BACKTOMENU, FINISH, UPDATEDATABASE
    }

    public static OperationResult showBalance(Card workingCard) throws InputExceptions{
        LOGGER.info("Showing balance.");
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


    public static OperationResult getMoneyMenu(Card workingCard) throws InputExceptions, IOException {
        while (true){
            LOGGER.info("getMoneyMenu caused.");
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


    public static OperationResult getMoney(Card workingCard, int selection) throws InputExceptions, IOException {
        Money money = new Money();
        money.moneyInitialize();
        int maxCurrencyAtATM = money.ingeneral;

        if (workingCard.getBalance()>=selection && maxCurrencyAtATM>=selection){
            System.out.println("Do you want to take: " + selection + "$ ?\n" +
                    "Press 1 - to confirm.\nPress 2 - to go back.");
            int answer = Input.inputInt();
            if(answer == 1){
                LOGGER.info("Getting money.");
                double currentBalance = workingCard.getBalance() - selection;
                workingCard.setBalance(currentBalance);

                String amountWasTaken = money.separateMoney(selection);
                money.setMoneyToProperty();

                WriteReadFile receipt = new WriteReadFile();
                receipt.writeToFile("recipe.txt", selection + "$ was withdrawn from the account. Current balance: " + currentBalance +
                        "$. " + amountWasTaken);
                System.out.println(receipt.readFromFile("recipe.txt"));
                return OperationResult.UPDATEDATABASE;
            }
        }
        else{
            System.out.println("Sorry, don't have enough money on your balance.");
        }
        return OperationResult.BACKTOMENU;
    }


    public static OperationResult addMoney(Card workingCard) throws InputExceptions, IOException {
        LOGGER.info("addMoney caused.");
        System.out.println("Press 1 - to entre 50.\n" +
                "Press 2 - to entre 100.\n" +
                "Press 3 - to entre 200.\n" +
                "Press 4 - to entre 500/\n" +
                "Press 5 - to finish.");

        int amount50toAdd = 0;
        int amount100toAdd = 0;
        int amount200toAdd = 0;
        int amount500toAdd = 0;
        int addToBalance = 0;

        boolean loopCheck = true;
        while (loopCheck == true){
            Integer amount = Input.inputInt();
            switch (amount){
                case 1:
                    amount50toAdd+=1;
                    addToBalance+=50;
                    break;
                case 2:
                    amount100toAdd+=1;
                    addToBalance+=100;
                    break;
                case 3:
                    amount200toAdd+=1;
                    addToBalance+=200;
                    break;
                case 4:
                    amount500toAdd+=1;
                    addToBalance+=500;
                    break;
                case 5:
                    loopCheck = false;
                    break;
                default:
                    return OperationResult.BACKTOMENU;
            }
        }

        workingCard.setBalance(workingCard.getBalance()+addToBalance);

        Money money = new Money();
        money.moneyInitialize();
        money.banknote50+=amount50toAdd;
        money.banknote100+=amount100toAdd;
        money.banknote200+=amount200toAdd;
        money.banknote500+=amount500toAdd;
        money.setMoneyToProperty();
        return OperationResult.UPDATEDATABASE;
    }
}
