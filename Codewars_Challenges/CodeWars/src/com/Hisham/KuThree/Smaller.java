package com.Hisham.KuThree;

/*
 * Challenge: https://www.codewars.com/kata/56a1c63f3bc6827e13000006
 * ********
 * Resource:
 * Solve using BST (Method 3) / AVL_ST (Method 2):
 * https://www.geeksforgeeks.org/count-smaller-elements-on-right-side/
  */

import java.util.*;

public class Smaller {

    static class Item {

        int val;
        int index;

        public Item(int val, int index) {
            this.val = val;
            this.index = index;
        }
    }

    public static void merge(Item[] items, int[] aux, int low, int lowEnd, int high, int highEnd) {

        int m = highEnd - low + 1;
        Item[] sorted = new Item[m];
        int rightCounter = 0;
        int lowPtr = low, highPtr = high;
        int index = 0;

        // Loop to store the count of smaller
        // Elements on right side when both
        // Array have some elements
        while (lowPtr <= lowEnd && highPtr <= highEnd) {
            if (items[lowPtr].val > items[highPtr].val) {
                rightCounter++;
                sorted[index++] = items[highPtr++];
            } else {
                aux[items[lowPtr].index] += rightCounter;
                sorted[index++] = items[lowPtr++];
            }
        }

        // Loop to store the count of smaller
        // elements in right side when only
        // left array have some element
        while (lowPtr <= lowEnd) {
            aux[items[lowPtr].index] += rightCounter;
            sorted[index++] = items[lowPtr++];
        }

        // Loop to store the count of smaller
        // elements in right side when only
        // right array have some element
        while (highPtr <= highEnd) {
            sorted[index++] = items[highPtr++];
        }

        System.arraycopy(sorted, 0, items, low, m);
    }

    public static void sort(Item[] items, int[] aux, int lo, int hi) {

        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;
        sort(items, aux, lo, mid);
        sort(items, aux, mid + 1, hi);

        merge(items, aux, lo, mid, mid + 1, hi);
    }

    public static int[] smaller(int[] unsorted) {
        int n = unsorted.length;

        Item[] items = new Item[n];

        for (int i = 0; i < n; i++) {
            items[i] = new Item(unsorted[i], i);
        }

        int[] aux = new int[n];
        sort(items, aux, 0, n - 1);

        return aux;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(smaller(new int[]{5, 4, 7, 9, 2, 4, 4, 5, 6})));
    }
}