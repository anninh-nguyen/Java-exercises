package programming.java.javafx;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;

public class ThreadCooperation {
  private static Account account = new Account();

  public static void main(String[] args) {

    System.out.println("Deposit\t\tWithdraw\t\tBalance");
    System.out.println("-----------------------------------------------------");
    // Create a thread pool with two threads
    ExecutorService executor = Executors.newFixedThreadPool(3);
    executor.execute(new DepositTask());
    executor.execute(new WithdrawTask());
    executor.execute(new WithdrawTask());
    executor.shutdown();
  }

  public static class DepositTask implements Runnable {
    @Override // Keep adding an amount to the account
    public void run() {
      try { 
        while (true) {
          // Deposit usually more than withdraw
          account.deposit((int)(Math.random() * 20) + 1);
          // Purposely delay it to let the withdraw method proceed
          Thread.sleep(2000);
        }
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
    }
  }

  public static class WithdrawTask implements Runnable {
    @Override
    public void run() {
      try {
        while (true) {
          account.withdraw((int)(Math.random() * 10) + 1);
          // Purposely delay it to let the deposit method proceed
          Thread.sleep(1000);
        }
      } catch (InterruptedException e) {
          e.printStackTrace();
        }
    }
  }

  // An inner class for account
  private static class Account {
    // Create a new lock
    private static Lock lock = new ReentrantLock();

    // Create a condition
    private static Condition newDeposit = lock.newCondition();

    private int balance = 0;

    public int getBalance() {
      return balance;
    }

    public void withdraw(int amount) {
      lock.lock(); // Acquire the lock
      try {
        while (balance < amount) {
          System.out.println("\t\tWithdraw " + amount + " (Wait!)");
          newDeposit.await();
        }
        
        balance -= amount;
        System.out.println("\t\tWithdraw " + amount + "\t\t" + getBalance());
      }
      catch (InterruptedException ex) {
        ex.printStackTrace();
      }
      finally {
        lock.unlock(); // Release the lock
      }
    }

    public void deposit(int amount) {
      lock.lock(); // Acquire the lock
      try {
        balance += amount;
        System.out.println("Deposit " + amount + "\t     \t\t\t" + getBalance());

        // Signal thread waiting on the condition
        newDeposit.signalAll();

        /*
         * Signal only one thread waiting on the condition
         */
        // newDeposit.signal();
      }
      finally {
        lock.unlock(); // Release the lock
      }
    }
  }
}