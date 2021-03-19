package com.Hisham.KuFive;

/*
 * Challenge: https://www.codewars.com/kata/54d512e62a5e54c96200019e
 */

public class PrimeDecomp {

    public static String factors(int n) {
        int i, t;
        StringBuilder sb = new StringBuilder();
        for (i = 2; i <= n; i++) {
            t = 0;
            while (n % i == 0) {
                t++;
                n /= i;
                if (n == 0) {
                    break;
                }
            }
            if (t > 1) {
                sb.append("(").append(i).append("**").append(t).append(")");
            } else if (t == 1) {
                sb.append("(").append(i).append(")");
            }
            if (i >= 3) {    // for avoiding the checking by even numbers.
                i++;
            }
        }
        if (sb.length() == 0) {
            return "(" + n + ")";
        }
        return sb.toString();
    }
}
