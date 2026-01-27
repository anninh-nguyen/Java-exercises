package programming.java.javafx;

import java.util.ArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

public class ParallelQuickSort implements AutoCloseable {
  
    /** A test method */
    public static void main(String[] args) {
      
      ArrayList<Integer> list = new ArrayList<Integer>();
      int threadHold = 400;
      /*
       * 24 milliseconds for 400 elements :))
       */
      /*
       * Got error
       */
      /*
       * Got error
       */
      for (int i = 0; i < threadHold; i++) {
        list.add((int)(Math.random() * threadHold));
      }
      if (threadHold < 500) {
        System.out.println("\nOriginal list: ");
        for (Integer integer : list) {
          System.out.print(integer + "\t");
        }
        System.out.println("\n");
      }
  
      // start counting time
      long startingTime = System.currentTimeMillis();
      parallelQuickSort(list);
      long timeElapsed = System.currentTimeMillis() - startingTime;
  
      if (threadHold < 500) {
        System.out.println("Sorted list: ");
        for (Integer integer : list) {
          System.out.print(integer + "\t");
        }
      }

      System.out.println("\nTime elapsed: " + timeElapsed + " milliseconds\n");
    }

    private static void parallelQuickSort(ArrayList<Integer> list) {
      RecursiveAction mainTask = new SortTask(list, 0, list.size() - 1);
      @SuppressWarnings("resource")
      ForkJoinPool pool = new ForkJoinPool();
      try {
          pool.invoke(mainTask);
      } finally {
          pool.shutdown();
      }
    }

    @Override
    public void close() throws Exception { }

    private static class SortTask extends RecursiveAction {
      private ArrayList<Integer> list;
      private int first;
      private int last;

      public SortTask(ArrayList<Integer> list, int first, int last) {
        this.list = list;
        this.first = first;
        this.last = last;
      }

      @Override
      protected void compute() {
        if (last > first) {
          int pivotIndex = partition(list, first, last);
          invokeAll(new SortTask(list, first, pivotIndex - 1), 
                    new SortTask(list, pivotIndex + 1, last));
        }
      }

      private static int partition(ArrayList<Integer> list, int first, int last) {
        int pivot = list.get(first); // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search
    
        while (high > low) {
          // Search forward from left
          while (low <= high && list.get(low) <= pivot)
            low++;
    
          // Search backward from right
          while (low <= high && list.get(high) > pivot)
            high--;
    
          // Swap two elements in the list
          if (high > low) {
            int temp = list.get(high);
            list.set(high, list.get(low));
            list.set(low, temp);
          }
        }
    
        while (high > first && list.get(high) >= pivot)
          high--;
    
        // Swap pivot with list[high]
        if (pivot > list.get(high)) {
          list.set(first, high);
          list.set(high, pivot);
          return high;
        }
        else {
          return first;
        }
      }
    }
}
