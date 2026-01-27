package programming.java.javafx;

public class Adder implements Runnable {

    private GoldReserve goldReserve;

    public Adder(GoldReserve goldReserve) {
        this.goldReserve = goldReserve;
    }

    public void run() {
        // Submit runnable tasks to the executor
        for (int i = 0; i < 10; i++) {
            goldReserve.storeGold(1);
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
