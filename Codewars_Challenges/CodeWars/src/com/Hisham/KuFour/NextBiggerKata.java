package com.Hisham.KuFour;

/*
  Challenge: https://www.codewars.com/kata/55983863da40caa2c900004e
 */

import java.util.*;

public class NextBiggerKata {

    private static void swap(Integer[] integers, int j, int k) {
        Integer temp = integers[j];
        integers[j] = integers[k];
        integers[k] = temp;
    }

    public static long nextBiggerNumber(long n) {
        char[] chars = String.valueOf(n).toCharArray();
        Integer[] integers = new Integer[chars.length];
        int i = 0;
        for (char c : chars) {
            integers[i++] = Integer.parseInt(String.valueOf(c));
        }
        int j = -1, k = integers.length - 1;
        for (i = chars.length - 2; i >= 0; i--) {
            int c = integers[i];
            int t = integers[i + 1];
            if (c < t) {
                j = i;
                break;
            }
        }
        if (j == -1) {
            return -1;
        }
        Arrays.sort(integers, j + 1, integers.length);
        int small = integers[integers.length - 1];
        for (int p = integers.length - 1; p > j; p--) {
            if (integers[p] <= small) {
                if (!integers[p].equals(integers[j]) && integers[p] > integers[j]) {
                    k = p;
                    small = integers[p];
                }
            }
        }
        swap(integers, j, k);
        Arrays.sort(integers, j + 1, integers.length);
        StringBuilder sb = new StringBuilder();
        for (int it : integers) {
            sb.append(it);
        }
        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(nextBiggerNumber(10990));
    }
}
