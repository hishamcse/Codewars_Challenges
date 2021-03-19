package com.Hisham.KuSix;

/*
 * Challenge: https://www.codewars.com/kata/555615a77ebc7c2c8a0000b8/java
 */

public class Line {

    public static String Tickets(int[] peopleInLine) {
        int twentyFive = 0, fifty = 0;
        for (int i : peopleInLine) {
            if (i == 25) {
                twentyFive++;
            } else if (i == 50) {
                fifty++;
                if (twentyFive == 0) {
                    return "NO";
                }
                twentyFive--;
            } else {
                if (fifty >= 1 && twentyFive >= 1) {
                    fifty--;
                    twentyFive--;
                } else if (twentyFive >= 3) {
                    twentyFive -= 3;
                } else {
                    return "NO";
                }
            }
        }
        return "YES";
    }
}
