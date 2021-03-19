package com.Hisham.KuFour;

/*
  Challenge: https://www.codewars.com/kata/5629db57620258aa9d000014
 */

import java.util.*;

public class Mixing {

    private static Map<Character, Integer> formulateMap(char[] chars, Set<Character> set) {
        Map<Character, Integer> map = new HashMap<>();
        Arrays.sort(chars);
        int m = 0;
        int i;
        for (i = 0; i < chars.length; i++) {
            set.add(chars[i]);
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                m++;
            }
            if (i != chars.length - 1 && chars[i] != chars[i + 1]) {
                if (m > 1) {
                    map.put(chars[i], m);
                }
                m = 0;
            }
        }
        if (m > 1) {
            map.put(chars[i - 1], m);
        }
        return map;
    }

    static class Item implements Comparable<Item> {

        int id;
        Character c;
        int n;

        public Item(int id, Character c, int n) {
            this.id = id;
            this.c = c;
            this.n = n;
        }

        @Override
        public int compareTo(Item o) {
            if (this.n > o.n) {
                return -1;
            }
            if (this.n < o.n) {
                return 1;
            }
            if (this.id > o.id) {
                return 1;
            }
            if (this.id < o.id) {
                return -1;
            }
            return this.c.compareTo(o.c);
        }
    }

    public static String mix(String s1, String s2) {
        Set<Character> set = new HashSet<>();
        Map<Character, Integer> map1 = formulateMap(s1.toCharArray(), set);
        Map<Character, Integer> map2 = formulateMap(s2.toCharArray(), set);
        List<Item> list = new ArrayList<>();

        StringBuilder sb = new StringBuilder();
        int i, j;
        for (char c : set) {
            i = map1.getOrDefault(c, 0);
            j = map2.getOrDefault(c, 0);
            if (i != 0 || j != 0) {
                if (i > j) {
                    list.add(new Item(1, c, i));
                } else if (i < j) {
                    list.add(new Item(2, c, j));
                } else {
                    list.add(new Item(3, c, i));
                }
            }
        }
        if (list.size() == 0) {
            return "";
        }
        Collections.sort(list);
        for (Item item : list) {
            if (item.id == 3) {
                sb.append("=:").append(String.valueOf(item.c).repeat(Math.max(0, item.n))).append("/");
            } else {
                sb.append(item.id).append(":").append(String.valueOf(item.c).repeat(Math.max(0, item.n))).append("/");
            }
        }
        return sb.substring(0, sb.length() - 1);
    }
}
