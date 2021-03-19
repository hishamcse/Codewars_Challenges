package com.Hisham.KuFive;

/*
 * Challenge: https://www.codewars.com/kata/55c04b4cc56a697bb0000048/java
 */

public class Scramblies {

    public static boolean scramble(String str1, String str2) {
        if (str2.length() == 0) {
            return true;
        }
        if (str2.length() > str1.length()) {
            return false;
        }
        int i = 0;
        char[] all = str1.toCharArray();
        for (char c : str2.toCharArray()) {
            for (int j = 0; j < all.length; j++) {
                if (c == all[j]) {
                    i++;
                    all[j] = ' ';
                    break;
                }
            }
            if (i == str2.length()) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(scramble("scriptingjava", "javascript"));
    }
}