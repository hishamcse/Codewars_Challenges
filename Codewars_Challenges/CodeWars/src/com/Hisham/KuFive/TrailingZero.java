package com.Hisham.KuFive;

/*
 * Challenge: https://www.codewars.com/kata/52f787eb172a8b4ae1000a34
 * ****
 * Resource: https://www.handakafunda.com/number-of-trailing-zeros
 * */

public class TrailingZero {
    public static int zeros(int n) {
        int s = 0;
        while (n > 0) {
            s += Math.floor((double) n / 5);
            n /= 5;
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(zeros(1000));
    }
}
