package com.Hisham.KuFour;

/*
 * Challenge: https://www.codewars.com/kata/51b66044bce5799a7f000003
 */

import java.util.HashMap;
import java.util.Map;

public class RomanNumerals {

    private static final Map<Character, Integer> fromRomanMap = new HashMap<>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};

    private static String getPartifiedRoman(int a, char p, char q, char r) {
        StringBuilder res = new StringBuilder();
        if (a >= 1 && a <= 3) {
            res.append(String.valueOf(p).repeat(a));
        } else if (a == 4) {
            res.append(p).append(q);
        } else if (a >= 5 && a <= 8) {
            res.append(q);
            res.append(String.valueOf(p).repeat(a - 5));
        } else if (a == 9) {
            res.append(p).append(r);
        }
        return res.toString();
    }

    public static String toRoman(int n) {
        char[] numbers = String.valueOf(n).toCharArray();
        int t = numbers.length, l, a;
        StringBuilder res = new StringBuilder();
        String v;
        if (t >= 4) {
            for (int i = 0; i <= t - 4; i++) {
                v = numbers[i] + "0".repeat(t - i - 1);
                l = Integer.parseInt(v) / 1000;
                res.append("M".repeat(Math.max(0, l)));
            }
        }
        if (t >= 3) {
            a = Integer.parseInt(String.valueOf(numbers[t - 3]));
            res.append(getPartifiedRoman(a, 'C', 'D', 'M'));
        }

        if (t >= 2) {
            a = Integer.parseInt(String.valueOf(numbers[t - 2]));
            res.append(getPartifiedRoman(a, 'X', 'L', 'C'));
        }

        if (t >= 1) {
            a = Integer.parseInt(String.valueOf(numbers[t - 1]));
            res.append(getPartifiedRoman(a, 'I', 'V', 'X'));
        }

        return res.toString();
    }

    public static int fromRoman(String romanNumeral) {
        int s = 0;
        char[] chars = romanNumeral.toCharArray();
        for (int i = 0; i < chars.length - 1; i++) {
            int z = fromRomanMap.get(chars[i]);
            int r = fromRomanMap.get(chars[i + 1]);
            if (z < r) {
                s -= z;
            } else {
                s += z;
            }
        }
        return s + fromRomanMap.get(chars[chars.length - 1]);
    }

    public static void main(String[] args) {
        System.out.println(fromRoman("MDCLXVI"));
        System.out.println(toRoman(2008));
    }
}