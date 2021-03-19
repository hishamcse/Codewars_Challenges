package com.Hisham.KuThree;

/*
 * Challenge: https://www.codewars.com/kata/53e57dada0cb0400ba000688
 * *********
 * Resource: https://www.careerbless.com/calculators/rank/index.php
 */

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Anagrams {

    public static BigDecimal factorial(int number) {
        BigDecimal factorial = BigDecimal.ONE;
        for (int i = number; i > 0; i--) {
            factorial = factorial.multiply(BigDecimal.valueOf(i));
        }
        return factorial;
    }

    private static BigDecimal multiplier(char[] all, int k, int small_count) {
        if (small_count == 0) {
            return BigDecimal.ZERO;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (int i = k; i < all.length; i++) {
            map.put(all[i], 1 + map.getOrDefault(all[i], 0));
        }

        BigDecimal res = new BigDecimal(String.valueOf(small_count));
        BigDecimal mult = BigDecimal.ONE;
        for (char c : map.keySet()) {
            mult = mult.multiply(factorial(map.get(c)));
        }
        res = res.multiply(BigDecimal.ONE.divide(mult, 1500, RoundingMode.HALF_EVEN));
        return res;
    }

    public BigInteger listPosition(String word) {
        if (word.length() == 1) {
            return BigInteger.ONE;
        }

        char[] all = word.toCharArray();
        char[] sorted = word.toCharArray();
        Arrays.sort(sorted);
        Map<Character, Integer> numbering_map = new HashMap<>();
        int numbering = 1;
        for (char c : sorted) {
            if ((numbering_map.putIfAbsent(c, numbering) == null)) {
                numbering++;
            }
        }

        // position wise factorials
        BigDecimal[] factorials = new BigDecimal[all.length];
        for (int i = 0; i < all.length; i++) {
            factorials[i] = factorial(all.length - i - 1);
        }

        BigDecimal total = BigDecimal.ONE;
        for (int i = 0; i < all.length; i++) {
            int rank = numbering_map.get(all[i]);
            int small_count = 0;
            for (int j = i + 1; j < all.length; j++) {
                if (rank > numbering_map.get(all[j])) {
                    small_count++;
                }
            }
            total = total.add(factorials[i].multiply(multiplier(all, i, small_count)));
        }
        return total.toBigInteger();
    }

    public static void main(String[] args) {
        Anagrams anagrams = new Anagrams();
        System.out.println(anagrams.listPosition("YWGXDRERRNGDMZEIQJNPITSLX"));
    }
}