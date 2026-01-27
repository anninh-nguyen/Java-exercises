package programming.java.javafx;

public class Task1 {

    public static void main(String[] args) {
        var taskProcess1 = new TaskProcess("Steps I have been made today: ");
        var taskProcess2 = new TaskProcess("Distance I have run today: ");
        var taskProcess3 = new TaskProcess("Calories I have burned today: ");
        var taskProcess4 = new TaskProcess("Time I have spent today: ");
        
        new Thread(taskProcess1).start();
        new Thread(taskProcess2).start();
        new Thread(taskProcess3).start();
        new Thread(taskProcess4).start();
    }
}
