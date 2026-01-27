package programming.java.javafx;

public class Task2 {

    public static void main(String[] args) {
        var taskProcess1 = new TaskProcess("Steps I have been made today: ");
        var taskProcess2 = new TaskProcess("Distance I have run today: ");
        var taskProcess3 = new TaskProcess("Calories I have burned today: ");
        var taskProcess4 = new TaskProcess("Time I have spent today: ");
        
        Thread thread1 = new Thread(taskProcess1);
        Thread thread2 = new Thread(taskProcess2);
        Thread thread3 = new Thread(taskProcess3);
        Thread thread4 = new Thread(taskProcess4);

        try {
            thread1.start();
            Thread.yield();
            thread2.start();
            Thread.sleep(1);
            thread3.start();
            thread3.setPriority(Thread.MAX_PRIORITY);
            thread4.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
