package com.Hisham.KuThree.ScreenLock;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AndroidScreenLockTest {

    AndroidScreenLock screenLock = new AndroidScreenLock();

    @Test
    public void test(){
        assertEquals(389112 , screenLock.calculateCombinations());
    }
}