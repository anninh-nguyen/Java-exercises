package programming.java.javafx;

class TaskProcess implements Runnable {

    String text;

    public TaskProcess(String text) {
        this.text = text;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(text + (i+1) + "\n");
        }
    }
}
