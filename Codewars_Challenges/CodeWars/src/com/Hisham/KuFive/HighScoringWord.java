package com.Hisham.KuFive;

/*
 * Challenge: https://www.codewars.com/kata/57eb8fcdf670e99d9b000272
 */

public class HighScoringWord {

    private static int score(String str) {
        int s = 0;
        for (char c : str.toCharArray()) {
            s += Integer.parseInt(String.valueOf(c - 96));
        }
        return s;
    }

    public static String high(String s) {
        String[] splits = s.split(" ");
        String res = "";
        int highScore = 0, score = 0;
        for (String str : splits) {
            score = score(str);
            if (score > highScore) {
                highScore = score;
                res = str;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(HighScoringWord.high("man i need a taxi up to ubud"));
    }
}
