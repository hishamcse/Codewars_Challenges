package com.Hisham.KuSeven;

/*
 * Challenge: https://www.codewars.com/kata/54ff3102c1bad923760001f3/java
 */

public class Vowels {

    public static int getCount(String str) {
        int vowelsCount = 0;
        for (char c : str.toCharArray()) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                vowelsCount++;
        }
        return vowelsCount;
    }
}