package com.Hisham.KuFour;

/*
  Challenge: https://www.codewars.com/kata/52b7ed099cdc285c300001cd
 */

import java.util.*;

public class Interval {

    static class SortBySecond implements Comparator<Integer[]> {

        @Override
        public int compare(Integer[] o1, Integer[] o2) {
            return o1[0] - o2[0];
        }
    }

    public static int sumIntervals(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }
        List<Integer[]> list = new ArrayList<>();
        for (int[] interval : intervals) {
            list.add(Arrays.stream(interval).boxed().toArray(Integer[]::new));
        }
        list.sort(new SortBySecond());
        for (int i = 0; i < list.size() - 1; i++) {
            if (list.get(i + 1)[0] < list.get(i)[1]) {
                if (list.get(i)[1] <= list.get(i + 1)[1]) {
                    Integer[] integers = new Integer[]{list.get(i)[0], list.get(i + 1)[1]};
                    // removing both array and adding the new one
                    list.add(i, integers);
                    list.remove(list.get(i + 1));
                    list.remove(list.get(i + 1));
                    i--;
                } else {
                    list.remove(list.get(i + 1));  //  as whole interval intersects with another. so ,simply remove
                    i--;
                }
            }
        }
        int s = 0;
        for (Integer[] integers : list) {
            s += (integers[1] - integers[0]);
        }
        return s;
    }
}