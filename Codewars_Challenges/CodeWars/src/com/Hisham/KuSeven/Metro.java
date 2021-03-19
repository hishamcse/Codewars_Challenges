package com.Hisham.KuSeven;

/*
 * Challenge: https://www.codewars.com/kata/5648b12ce68d9daa6b000099/java
 */

import java.util.ArrayList;

public class Metro {

    public static int countPassengers(ArrayList<int[]> stops) {
        int l = 0, r = 0;
        for (int[] st : stops) {
            l += st[0];
            r += st[1];
        }
        return l - r;
    }

    public static void main(String[] args) {
        ArrayList<int[]> list = new ArrayList<int[]>();
        list.add(new int[]{10, 0});
        list.add(new int[]{3, 5});
        list.add(new int[]{2, 5});
        System.out.println(countPassengers(list));
    }
}
