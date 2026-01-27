package programming.java.javafx;

public class TaskThreadDemo {
  public static void main(String[] args) {
    
    //int cores = Runtime.getRuntime().availableProcessors();
    //System.out.println(cores);
    
    // Create tasks
    PrintChar printA = new PrintChar('a', 100);
    PrintChar printB = new PrintChar('b', 100);
    PrintNum print100 = new PrintNum(100);

    // Create threads
    Thread thread1 = new Thread(printA);
    Thread thread2 = new Thread(printB);
    Thread thread3 = new Thread(print100);
    
    // Start threads
    thread1.start();
    thread2.start();
    thread3.start();
  }
}

// The task for printing a specified character in specified times
class PrintChar implements Runnable {
  private char charToPrint; // The character to print
  private int times; // The times to repeat

  public PrintChar(char c, int t) {
    charToPrint = c;
    times = t;
  }

  public void run() {
      for (int i = 0; i < times; i++)
        System.out.print(" "+charToPrint);
  } 
}

// The task class for printing number from 1 to n for a given n
class PrintNum implements Runnable {
  private int lastNum;

  /** Construct a task for printing 1, 2, ... i */
  public PrintNum(int n) {
    lastNum = n;
  }

  public void run2() {
    //try{
    for (int i = 1; i <= lastNum; i++)
    {
        System.out.print(" "+i);
        //Thread.sleep(10);
        //Thread.yield();
    } 
    //}
    //catch(InterruptedException ex)
    //{ System.out.println(ex); }
  }
  public void run() {
    Thread thread4 = new Thread(new PrintChar('c',40));
    thread4.start();
    
    try{
      for (int i = 1; i <= lastNum; i++)
      {
          System.out.print(" "+i);
          if (i == 50) thread4.join();
      }
    }
    catch(InterruptedException ex)
    { System.out.println(ex); }
  }
}
  