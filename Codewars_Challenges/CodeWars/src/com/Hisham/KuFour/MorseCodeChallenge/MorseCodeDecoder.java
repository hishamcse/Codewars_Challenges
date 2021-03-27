package com.Hisham.KuFour.MorseCodeChallenge;

/*
 * Challenge: https://www.codewars.com/kata/54b72c16cd7f5154e9000457
 */

import java.util.*;

public class MorseCodeDecoder {

    static class MorseDigit {
        char type;
        int count;

        public MorseDigit(char type, int count) {
            this.type = type;
            this.count = count;
        }
    }

    public static String decodeBits(String bits) {
        int i = 0, j = 0;
        int n = bits.length();
        char c = bits.charAt(0);
        while (c == '0') {
            i++;
            c = bits.charAt(i);
        }
        c = bits.charAt(n - 1);
        while (c == '0') {
            j++;
            c = bits.charAt(n - j - 1);
        }

        int count = 0;
        char prev = '1';
        List<MorseDigit> list = new ArrayList<>();
        Set<Integer> set = new HashSet<>();
        String original = bits.substring(i, n - j);
        for (char c1 : original.toCharArray()) {
            if (c1 == prev) {
                count++;
            } else {
                list.add(new MorseDigit(prev, count));
                set.add(count);
                prev = (prev == '0') ? '1' : '0';
                count = 1;
            }
        }
        list.add(new MorseDigit(prev, count));
        int min = Integer.MAX_VALUE;
        for (int s : set) {
            if (s < min) {
                min = s;
            }
        }

        StringBuilder sb = new StringBuilder();
        for (MorseDigit digit : list) {
            if (digit.type == '1') {
                if (digit.count == 3 * min) {
                    sb.append("-");
                } else {
                    sb.append(".");
                }
            } else {
                if (digit.count == 3 * min) {
                    sb.append(" ");
                } else if (digit.count == 7 * min) {
                    sb.append("   ");
                }
            }
        }

        System.out.println(sb.toString());
        return sb.toString();
    }

    public static String decodeMorse(String morseCode) {
        StringBuilder sb = new StringBuilder();
        String[] splits = morseCode.split("   ");
        for (String s : splits) {
            String[] all = s.trim().split(" ");
            for (String t : all) {
                if (MorseCode.get(t.trim()) != null) {
                    sb.append(MorseCode.get(t.trim()).trim());
                }
            }
            sb.append(" ");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println(decodeMorse(decodeBits("1110111")));
    }
}