package programming.java.javafx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task4 {
    public static void main(String[] args) {
        var goldReserve = new GoldReserve(0);
        ExecutorService executor = Executors.newFixedThreadPool(500);
        for (int i = 0; i < 500; i++) {
            executor.execute(new Adder(goldReserve));
        }
        executor.shutdown();

        System.out.println(goldReserve.getAmount());
    }
}
