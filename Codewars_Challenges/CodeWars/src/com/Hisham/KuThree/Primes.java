package com.Hisham.KuThree;

/*
 * Challenge: https://www.codewars.com/kata/5519a584a73e70fa570005f5
 * *****
 * Resource:
 * https://stackoverflow.com/questions/11966520/how-to-find-prime-numbers-between-0-100/41434702#41434702
 */

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class Primes {

    public static IntStream stream() {
        List<Long> list = new ArrayList<>();
        long n = 10000000;
        boolean[] falsyIndices = new boolean[(int) n+1];
        long max_i = (long) ((Math.sqrt(4 + 8 * n) - 2) / 4);
        for (long i = 1; i <= max_i; i++) {
            long max_j = (n - i) / (1 + 2 * i);
            for (long j = i; j <= max_j; j++) {
                falsyIndices[(int) (i + j + 2 * i * j)] = true;
            }
        }
        list.add(2L);
        for (long i = 1; i <= n; i++) {
            if (!falsyIndices[(int) i]) {
                list.add(2 * i + 1);
            }
        }
        return list.stream().mapToInt(Long::intValue);
    }

    public static void main(String[] args) {
        stream();
    }
}

// more effecient
//        for(long i=1;i<(n-1)/3;i++){
//            int_list[(int) (3*i+1)] = true;
//        }
//        for (long i = 2; i <= max_i; i++) {
//            long max_j = (n - i) / (1 + 2 * i);
//            if(i%3-1!=0) {
//                for (long j = i; j < max_j; j++) {
//                    int_list[(int) (i + j + 2 * i * j)] = true;
//                }
//            }
//        }