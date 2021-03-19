package com.Hisham.KuSeven;

/*
 * Challenge: https://www.codewars.com/kata/563b662a59afc2b5120000c6/java
 */

class Arge {

    public static int nbYear(int p0, double percent, int aug, int p) {
        int c=0;
        while(p0<p){
            p0 = p0 + (int)((percent/100) * p0) + aug;
            if(p0>=p){
                break;
            }
            c++;
        }
        return c+1;
    }
}