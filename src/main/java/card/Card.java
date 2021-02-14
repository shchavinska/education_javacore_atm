package card;


public class Card {

    private int number;
    private String status;
    private int password;
    private String name;
    private String expareDatee;
    private int cvv;
    private double balance;

    public void setNumber(int number) { this.number = number; }
    public int getNumber() { return number; }

    public void setStatus(String status) { this.status = status; }
    public String getStatus() { return status; }

    public void setPassword(int password) { this.password = password; }
    public int getPassword() { return password; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setExpareDatee(String expareDatee) { this.expareDatee = expareDatee; }
    public String getExpareDatee() { return expareDatee; }

    public void setCvv(int cvv) { this.cvv = cvv; }
    public int getCvv() { return cvv; }

    public void setBalance(double balance) { this.balance = balance; }
    public double getBalance() { return balance; }


    public Card(){
        number = 0000;
        status = "unknown";
        password = 0000;
        name = "Ivan";
        expareDatee = "00/00";
        cvv = 000;
        balance = 0000.00;
    }

    public Card( int number, String status, int password, String name, String expareDatee, int cvv, double balance){
        this.number = number;
        this.status = status;
        this.password = password;
        this.name = name;
        this.expareDatee = expareDatee;
        this.cvv = cvv;
        this.balance = balance;
    }
}
