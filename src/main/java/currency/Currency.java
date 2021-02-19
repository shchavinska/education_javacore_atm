package currency;
import java.util.HashMap;


public class Currency {

    HashMap<Integer, Integer> money = new HashMap<>();

    public void addMoney(Integer currency, Integer amount){
        money.put(currency, amount);
    }

    public HashMap<Integer, Integer> getMoney(){
        return money;
    }
}
