package com.Hisham.KuSix;

/*
 * Challenge: https://www.codewars.com/kata/5264d2b162488dc400000001/java
 */

public class SpinWords {

    public static String spinWords(String sentence) {
        //TODO: Code stuff here
        String[] all = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String s : all) {
            if (s.length() < 5) {
                sb.append(s).append(" ");
            } else {
                sb.append(new StringBuilder(s).reverse().toString()).append(" ");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}