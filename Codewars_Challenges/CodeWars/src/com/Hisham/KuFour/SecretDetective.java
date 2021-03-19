package com.Hisham.KuFour;

/*
 * Challenge: https://www.codewars.com/kata/53f40dff5f9d31b813000774
 */

import java.util.*;

public class SecretDetective {

    private void order(List<String> list, char f, char s) {
        if (list.indexOf(String.valueOf(f)) > list.indexOf(String.valueOf(s))) {
            list.remove(String.valueOf(f));
            list.add(list.indexOf(String.valueOf(s)), String.valueOf(f));
        }
    }

    public String recoverSecret(char[][] triplets) {
        Set<String> set = new HashSet<>();
        for (char[] chars : triplets) {
            for (char c : chars) {
                set.add(String.valueOf(c));
            }
        }
        List<String> list = new ArrayList<>(set);
        for (char[] triplet : triplets) {
            order(list, triplet[1], triplet[2]);    // at first second and third, then first and second.
            order(list, triplet[0], triplet[1]);   // manually check this by hand. you will find out why
        }
        StringBuilder sb = new StringBuilder();
        for (String c : list) {
            sb.append(c);
        }
        return sb.toString();
    }
}
