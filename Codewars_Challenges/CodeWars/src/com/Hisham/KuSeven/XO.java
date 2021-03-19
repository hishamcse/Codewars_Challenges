package com.Hisham.KuSeven;

/*
 * Challenge: https://www.codewars.com/kata/55908aad6620c066bc00002a/java
 */

public class XO {

    public static boolean getXO(String str) {
        int resX = 0;
        int resO = 0;
        for (char c : str.toCharArray()) {
            if (c == 'o' || c == 'O') {
                resO++;
            } else if (c == 'x' || c == 'X') {
                resX++;
            }
        }
        return resO == resX;
    }
}
