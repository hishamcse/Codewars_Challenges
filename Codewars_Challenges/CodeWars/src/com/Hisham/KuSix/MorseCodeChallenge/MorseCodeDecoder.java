package com.Hisham.KuSix.MorseCodeChallenge;

/*
 * Challenge: https://www.codewars.com/kata/54b724efac3d5402db00065e
 */

public class MorseCodeDecoder {

    public static String decode(String morseCode) {
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
        System.out.println(MorseCodeDecoder.decode(".... . -.--   .--- ..- -.. ."));
    }
}
