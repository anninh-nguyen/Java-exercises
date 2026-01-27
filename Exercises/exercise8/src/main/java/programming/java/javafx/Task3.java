package programming.java.javafx;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task3 {

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(4);
        // Submit runnable tasks to the executor
        executor.execute(new TaskProcess("Distance I have run today: "));
        executor.execute(new TaskProcess("Steps I have been made today: "));
        executor.execute(new TaskProcess("Calories I have burned today: "));
        executor.execute(new TaskProcess("Time I have spent today: "));
        // Shut down the executor
        executor.shutdown();
    }
}
