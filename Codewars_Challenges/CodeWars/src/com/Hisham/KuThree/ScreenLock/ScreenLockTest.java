package com.Hisham.KuThree.ScreenLock;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ScreenLockTest {

    ScreenLock screenLock = new ScreenLock();

    @Test
    public void test(){
        assertEquals(0, screenLock.calculateCombinations('A',10));
        assertEquals(0, screenLock.calculateCombinations('A',0));
        assertEquals(0, screenLock.calculateCombinations('E',14));
        assertEquals(1, screenLock.calculateCombinations('B',1));
        assertEquals(5, screenLock.calculateCombinations('C',2));
        assertEquals(8, screenLock.calculateCombinations('E',2));
        assertEquals(256, screenLock.calculateCombinations('E',4));
    }
}