package com.Hisham.KuFour;

/**
 * Challenge: https://www.codewars.com/kata/56c04261c3fcf33f2d000534
 **/

class Magnets {

    private static double magnetValue(int k, int maxn) {
        double m = 0.0;
        for (int n = 1; n <= maxn; n++) {
            m += (1 / (k * Math.pow(n + 1, 2 * k)));
        }
        return m;
    }

    public static double doubles(int maxk, int maxn) {
        double s = 0.0;
        for (int k = 1; k <= maxk; k++) {
            s += magnetValue(k, maxn);
        }
        return s;
    }

    public static void main(String[] args) {
        System.out.println(doubles(20, 10000));
    }
}
