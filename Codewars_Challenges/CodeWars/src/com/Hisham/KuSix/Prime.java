package com.Hisham.KuSix;

/*
 * Challenge: https://www.codewars.com/kata/5262119038c0985a5b00029f/java
 */
public class Prime {

    public static boolean isPrime(int num) {
        if (num <= 1 || (num > 2 && num % 2 == 0)) {
            return false;
        }
        int h = (int) (Math.sqrt(num) + 1);
        for (int i = 3; i <= h; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
