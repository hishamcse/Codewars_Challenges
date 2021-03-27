package com.Hisham.KuFour;

import java.util.HashMap;
import java.util.Map;

/*
 * Challenge: https://www.codewars.com/kata/525c7c5ab6aecef16e0001a5
 */

public class Parser {

    static Map<String, Integer> map = new HashMap<>() {{
        put("zero", 0);
        put("one", 1);
        put("two", 2);
        put("three", 3);
        put("four", 4);
        put("five", 5);
        put("six", 6);
        put("seven", 7);
        put("eight", 8);
        put("nine", 9);
        put("ten", 10);
        put("eleven", 11);
        put("twelve", 12);
        put("thirteen", 13);
        put("fourteen", 14);
        put("fifteen", 15);
        put("sixteen", 16);
        put("seventeen", 17);
        put("eighteen", 18);
        put("nineteen", 19);
        put("twenty", 20);
        put("thirty", 30);
        put("forty", 40);
        put("fifty", 50);
        put("sixty", 60);
        put("seventy", 70);
        put("eighty", 80);
        put("ninety", 90);
    }};

    public static int parseInt(String numStr) {
        System.out.println(numStr);
        String[] all = numStr.split(" ");
        int small = 0;
        int sum = 0;
        int last = 0;
        for (String s : all) {
            int x = map.getOrDefault(s, -1);
            if (x == -1) {
                String[] other = s.split("-");
                for (String t : other) {
                    int y = map.getOrDefault(t, -1);
                    if (y != -1) {
                        sum += y;
                        last = y;
                    } else {
                        int z = map.getOrDefault(t, -1);
                        if (z != -1) {
                            sum += z;
                            last = z;
                        } else {
                            if (t.equals("hundred") && small == 0) {
                                sum *= 100;
                                small = 100;
                            } else if (t.equals("hundred")) {
                                sum += ((last * 100) - last);
                            } else if (t.equals("thousand") && small != 1000) {
                                sum *= 1000;
                                small = 1000;
                            } else if (t.equals("thousand")) {
                                sum += ((last * 1000) - last);
                            } else if (t.equals("million")) {
                                sum += ((last * 1000000) - last);
                                small = 1000000;
                            }
                        }
                    }
                }
            } else {
                sum += x;
                last = x;
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(Parser.parseInt("seven hundred eighty-three thousand nine hundred and nineteen"));
    }
}