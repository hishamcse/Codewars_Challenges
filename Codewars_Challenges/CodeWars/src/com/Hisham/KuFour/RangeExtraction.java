package com.Hisham.KuFour;

/*
 * Challenge: https://www.codewars.com/kata/51ba717bb08c1cd60f00002f
 */

public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int count = 1;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i + 1] == arr[i] + 1) {
                if (i + 1 == arr.length - 1) {
                    if (count == 1) {
                        sb.append(arr[i]).append(",").append(arr[i + 1]).append(",");
                    } else {
                        sb.append(arr[i - count + 1]).append("-").append(arr[i + 1]).append(",");
                    }
                }
                count++;
            } else {
                if (count == 1) {
                    sb.append(arr[i]).append(",");
                } else if (count == 2) {
                    sb.append(arr[i - 1]).append(",").append(arr[i]).append(",");
                } else {
                    sb.append(arr[i - count + 1]).append("-").append(arr[i]).append(",");
                }
                if (i + 1 == arr.length - 1) {
                    sb.append(arr[i + 1]).append(",");
                }
                count = 1;
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
