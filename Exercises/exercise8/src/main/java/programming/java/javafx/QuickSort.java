package programming.java.javafx;

import java.util.ArrayList;

public class QuickSort {
  public static void quickSort(ArrayList<Integer> list) {
    quickSort(list, 0, list.size() - 1);
  }

  public static void quickSort(ArrayList<Integer> list, int first, int last) {
    if (last > first) {
      int pivotIndex = partition(list, first, last);
      quickSort(list, first, pivotIndex - 1);
      quickSort(list, pivotIndex + 1, last);
    }
  }

  /** Partition the array list[first..last] */
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

  /** A test method */
  public static void main(String[] args) {
    
    ArrayList<Integer> list = new ArrayList<Integer>();
    int threadHold = 400;
    /*
     * 6 milliseconds for 400 elements
     */
    /*
     * 1 second for 400K elements
     */
    /*
     * 36.3 seconds for 4M elements
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

    if (threadHold < 500) {
      System.out.println("\nOriginal list: ");
      for (Integer integer : list) {
        System.out.print(integer + "\t");
      }
      System.out.println("\n");
    }

    // start counting time
    long startingTime = System.currentTimeMillis();
    quickSort(list);
    long timeElapsed = System.currentTimeMillis() - startingTime;

    if (threadHold < 500) {
      System.out.println("Sorted list: ");
      for (Integer integer : list) {
        System.out.print(integer + "\t");
      }
    }
    
    System.out.println("\nTime elapsed: " + timeElapsed + " milliseconds\n");
  }
}