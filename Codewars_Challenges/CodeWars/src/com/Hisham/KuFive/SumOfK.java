package com.Hisham.KuFive;

/*
  Challenge: https://www.codewars.com/kata/55e7280b40e1c4a06d0000aa
 */

import java.util.ArrayList;
import java.util.List;

public class SumOfK {

    public static void findCombinations(Object[] A, List<String> strings, String out, int i, int n, int k) {
        if (k > n) {
            return;
        }

        if (k == 0) {
            strings.add(out.trim());
            return;
        }

        for (int j = i; j < n; j++) {
            findCombinations(A, strings, out + " " + (A[j]), j + 1, n, k - 1);
        }
    }

    private static Integer sum(String str) {
        String[] all = str.split(" ");
        int sum = 0;
        for (String s : all) {
            sum += Integer.parseInt(s);
        }
        return sum;
    }

    public static Integer chooseBestSum(int t, int k, List<Integer> ls) {
        int s;
        List<String> strings = new ArrayList<>();
        findCombinations(ls.toArray(), strings, "", 0, ls.size(), k);
        int max = -1;
        for (String str : strings) {
            s = sum(str);
            if (s > max && s <= t) {
                max = s;
            }
        }
        if (max != -1) {
            return max;
        }
        return null;
    }
}
