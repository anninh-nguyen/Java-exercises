package programming.java.javafx;

public class GoldReserve {
    int amount;

    public GoldReserve(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void storeGold(int amount) {
        this.amount += amount;
    }
}
