package com.Hisham.KuFour;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConwayLifeTest {

    @Test
    public void testGlider() {
        int[][][] gliders = {
                {{1, 0, 0},
                        {0, 1, 1},
                        {1, 1, 0}},
                {{0, 1, 0},
                        {0, 0, 1},
                        {1, 1, 1}}
        };
        System.out.println("Glider");
        ConwayLife.LifeDebug.print(gliders[0]);
        int[][] res = ConwayLife.getGeneration(gliders[0], 1);
        assertTrue("Got \n" + ConwayLife.LifeDebug.htmlize(res) + "\ninstead of\n" + ConwayLife.LifeDebug.htmlize(gliders[1]), ConwayLife.LifeDebug.equals(res, gliders[1]));
    }
}