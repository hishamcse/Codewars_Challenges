package com.Hisham.KuSeven;

/*
 * Challenge: https://www.codewars.com/kata/554e4a2f232cdd87d9000038/java
 */
public class DnaStrand {

    public static String makeComplement(String dna) {
        StringBuilder sb = new StringBuilder();
        for (char c : dna.toCharArray()) {
            if (c == 'A') {
                sb.append('T');
            } else if (c == 'T') {
                sb.append('A');
            } else if (c == 'G') {
                sb.append('C');
            } else if (c == 'C') {
                sb.append('G');
            }
        }
        return sb.toString();
    }
}
