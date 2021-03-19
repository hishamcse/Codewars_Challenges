package com.Hisham.KuFour;

/*
  Challenge: https://www.codewars.com/kata/51c8e37cee245da6b40000bd
 */

import java.util.ArrayList;
import java.util.List;

public class StripComments {

    public static String stripComments(String text, String[] commentSymbols) {
        String[] strings = text.split("\n");
        List<String> list = new ArrayList<>();
        StringBuilder sb;
        String sb2;
        for (String str : strings) {
            char[] chars = str.toCharArray();
            sb2 = "";
            for (String cmt : commentSymbols) {
                sb = new StringBuilder();
                for (char c : chars) {
                    if (String.valueOf(c).equals(cmt)) {
                        break;
                    } else {
                        sb.append(c);
                    }
                }
                sb.append("\n");
                if (sb2.length() >= sb.length() || sb2.length() == 0) {
                    sb2 = sb.toString();
                }
            }
            list.add(sb2);
        }
        sb = new StringBuilder();
        for (String s : list) {
            sb.append(s.replaceAll("\\s+$", "")).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}
