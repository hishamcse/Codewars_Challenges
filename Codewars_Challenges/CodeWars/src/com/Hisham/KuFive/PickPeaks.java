package com.Hisham.KuFive;

/*
 * Challenge: https://www.codewars.com/kata/5279f6fe5ab7f447890006a7/java
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class PickPeaks {

    public static Map<String, List<Integer>> getPeaks(int[] arr) {
        List<Integer> posArr = new ArrayList<>();
        List<Integer> peakArr = new ArrayList<>();
        int previous = 0, current = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > arr[current]) {
                previous = current;
                current = i;
            } else {
                if (arr[i] < arr[current]) {
                    if (arr[previous] < arr[current]) {
                        posArr.add(current);
                        peakArr.add(arr[current]);
                    }
                    previous = current;
                    current = i;
                }
            }
        }
        Map<String, List<Integer>> map = new HashMap<>();
        map.put("pos", posArr);
        map.put("peaks", peakArr);
        return map;
    }

    public static void main(String[] args) {
        System.out.println(getPeaks(new int[]{3, 2, 3, 6, 4, 1, 2, 3, 2, 1, 2, 3}));
    }
}
