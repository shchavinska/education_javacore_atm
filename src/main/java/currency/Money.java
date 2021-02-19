package currency;

import org.apache.log4j.*;
import utils.*;
import java.io.*;
import java.util.*;


public class Money {
    private final static Logger LOGGER = Logger.getLogger(Money.class);

    public int banknote50 = 0;
    public int banknote100 = 0;
    public int banknote200 = 0;
    public int banknote500 = 0;
    public int ingeneral = 0;

    public void moneyInitialize(){
        LOGGER.info("moneyInitialize caused");
        ReadProperties val = new ReadProperties();

        banknote50 = val.getValueFromProperties("currency.txt",50);
        banknote100 = val.getValueFromProperties("currency.txt",100);
        banknote200 = val.getValueFromProperties("currency.txt",200);
        banknote500 = val.getValueFromProperties("currency.txt",500);

        ingeneral = 50*banknote50 + 100*banknote100 + 200*banknote200 + 500*banknote500;
    }


    public String separateMoney(Integer moneyToGet){
        int amount500toGet = 0;
        int amount200toGet = 0;
        int amount100toGet = 0;
        int amount50toGet = 0;

        while (moneyToGet>0){
            if (moneyToGet>=500 && banknote500!=0){
                amount500toGet = moneyToGet/500;
                if (amount500toGet>banknote500){
                    amount500toGet = banknote500;
                    banknote500-=amount500toGet;
                    moneyToGet = moneyToGet - 500*amount500toGet;
                }
                else{
                    moneyToGet = moneyToGet%500;
                    banknote500-=amount500toGet;
                }
            }
            else if (moneyToGet>=200 && banknote200!=0){
                amount200toGet = moneyToGet/200;
                if (amount200toGet>banknote200){
                    amount200toGet = banknote200;
                    banknote200-=amount200toGet;
                    moneyToGet = moneyToGet - 200*amount200toGet;
                }
                else{
                    moneyToGet = moneyToGet%200;
                    banknote200-=amount200toGet;
                }
            }
            else if (moneyToGet>=100 && banknote100!=0){
                amount100toGet = moneyToGet/100;
                if (amount100toGet>banknote100){
                    amount100toGet = banknote100;
                    banknote100-=amount100toGet;
                    moneyToGet = moneyToGet - 100*amount100toGet;
                }
                else{
                    moneyToGet = moneyToGet%100;
                    banknote100-=amount100toGet;
                }
            }
            else if (moneyToGet>=50 && banknote50!=0){
                amount50toGet = moneyToGet/50;
                if (amount50toGet>banknote50){
                    amount50toGet = banknote50;
                    banknote50-=amount50toGet;
                    moneyToGet = moneyToGet - 50*amount50toGet;
                }
                else{
                    moneyToGet = moneyToGet%50;
                    banknote50-=amount50toGet;
                }
            }
        }
        String infoForCheck = Integer.toString(amount500toGet) + " of 500 was taken, " + Integer.toString(amount200toGet) +
                " of 200 was taken, " + Integer.toString(amount100toGet) + " of 100 was taken, " + Integer.toString(amount50toGet) +
                " of 50 was taken.";
        return infoForCheck;
    }


    public void setMoneyToProperty() throws IOException {
        Currency currencyHashMap = new Currency();
        currencyHashMap.addMoney(500, banknote500);
        currencyHashMap.addMoney(200, banknote200);
        currencyHashMap.addMoney(100, banknote100);
        currencyHashMap.addMoney(50, banknote50);

        WriteProperties val1 = new WriteProperties("currency.txt");

        for (HashMap.Entry<Integer, Integer> entry : currencyHashMap.getMoney().entrySet()) {
            Integer currency = entry.getKey();
            Integer amount = entry.getValue();
            val1.setValueToProperties(currency, amount);
        }
        val1.close();
    }
}
