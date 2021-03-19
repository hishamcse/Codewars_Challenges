package com.Hisham.KuFour.PokerHand;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class PokerHandSortTest {

    @Test
    public void pokerHandSortTest() {
        // Arrange
        ArrayList<PokerHandSort> expected = new ArrayList<>();
        expected.add(new PokerHandSort("KS AS TS QS JS"));
        expected.add(new PokerHandSort("2H 3H 4H 5H 6H"));
        expected.add(new PokerHandSort("AS AD AC AH JD"));
        expected.add(new PokerHandSort("JS JD JC JH 3D"));
        expected.add(new PokerHandSort("2S AH 2H AS AC"));
        expected.add(new PokerHandSort("AS 3S 4S 8S 2S"));
        expected.add(new PokerHandSort("2H 3H 5H 6H 7H"));
        expected.add(new PokerHandSort("2S 3H 4H 5S 6C"));
        expected.add(new PokerHandSort("2D AC 3H 4H 5S"));
        expected.add(new PokerHandSort("AH AC 5H 6H AS"));
        expected.add(new PokerHandSort("2S 2H 4H 5S 4C"));
        expected.add(new PokerHandSort("AH AC 5H 6H 7S"));
        expected.add(new PokerHandSort("AH AC 4H 6H 7S"));
        expected.add(new PokerHandSort("2S AH 4H 5S KC"));
        expected.add(new PokerHandSort("2S 3H 6H 7S 9C"));

        Random random = new Random();
        ArrayList<PokerHandSort> actual = createRandomOrderedList(random, expected);

        // Act
        Collections.sort(actual);
        System.out.println(actual.toString());

        // Assert
        Iterator a = actual.iterator();
        for (PokerHandSort e : expected) {
            assertEquals(e, a.next());
        }
    }

    private ArrayList<PokerHandSort> createRandomOrderedList(Random random, ArrayList<PokerHandSort> expected){
        ArrayList<PokerHandSort> actual = new ArrayList<>();
        for (PokerHandSort pokerHandSort : expected) {
            int j = random.nextInt(actual.size()+1);
            actual.add(j, pokerHandSort);
        }
        return actual;
    }
}