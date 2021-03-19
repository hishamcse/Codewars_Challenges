package com.Hisham.KuThree;

/**
 * Challenge: https://www.codewars.com/kata/58c5577d61aefcf3ff000081
 * ***
 * Resource:
 * https://en.wikipedia.org/wiki/Rail_fence_cipher
 * http://article.nadiapub.com/IJFGCN/vol11_no2/3.pdf
 **/

public class RailFenceCipher {

    static String encode(String s, int n) {
        if (s.length() <= n) {
            return s;
        }
        StringBuilder[] strings = new StringBuilder[n];
        for (int i = 0; i < n; i++) {
            strings[i] = new StringBuilder();
            strings[i].append(s.charAt(i));
        }
        boolean reverse = true;
        int k = n - 1;
        for (int i = n; i < s.length(); i++) {
            if (reverse) {
                strings[--k].append(s.charAt(i));
                if (k == 0) {
                    reverse = false;
                }
            } else {
                strings[++k].append(s.charAt(i));
                if (k == n - 1) {
                    reverse = true;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (StringBuilder sb : strings) {
            res.append(sb);
        }
        return res.toString();
    }

    static String decode(String s, int n) {
        if (s.length() <= n) {
            return s;
        }
        char[] res = new char[s.length()];
        int cipher_iter = 0;
        for (int k = 0; k < n; k++) {
            int plain_iter = k;
            boolean reverse = false;
            while (plain_iter < s.length()) {
                res[plain_iter] = s.charAt(cipher_iter++);

                if (k == 0 || k == n - 1) {
                    plain_iter = plain_iter + 2 * (n - 1);
                } else if (!reverse) {
                    plain_iter = plain_iter + 2 * (n - k - 1);
                    reverse = true;
                } else {
                    plain_iter = plain_iter + 2 * k;
                    reverse = false;
                }
            }
        }
        return String.valueOf(res);
    }
}