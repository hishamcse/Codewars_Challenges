package com.Hisham.KuFour.PokerHand;

/*
 * Challenge: https://www.codewars.com/kata/5739174624fc28e188000465
 * ****
 * Resource:
 *  https://en.wikipedia.org/wiki/Texas_hold_%27em#Hand_values
 */

import java.util.*;

public class PokerHandRank {

    public enum Result {TIE, WIN, LOSS}

    Map<String, Integer> hand_cards_state = new HashMap<>() {{
        put("High Card", 1);
        put("Single Pair", 2);
        put("Double Pair", 3);
        put("Three Of Kind", 4);
        put("Straight", 5);
        put("Flush", 6);
        put("Full House", 7);
        put("Four Of Kind", 8);
        put("Straight Flush", 9);
        put("Royal Flush", 10);
    }};

    String card, state;
    List<Integer> list;

    Map<Character, Integer> card_value = new HashMap<>() {{
        put('T', 10);
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};

    PokerHandRank(String hand) {
        state = "";
        card = hand;
        list = this.getHandValues();
    }

    public String getState() {
        if (royal_flush()) state = "Royal Flush";
        else if (straight_flush()) state = "Straight Flush";
        else if (four_of_kind()) state = "Four Of Kind";
        else if (full_house()) state = "Full House";
        else if (flush()) state = "Flush";
        else if (straight()) state = "Straight";
        else if (three_of_kind()) state = "Three Of Kind";
        else if (double_pair()) state = "Double Pair";
        else if (pair()) state = "Single Pair";
        else state = "High Card";
        return state;
    }

    public List<Integer> getHandValues() {
        String[] all = this.card.split(" ");
        List<Integer> list = new ArrayList<>();
        for (String s : all) {
            int n = card_value.getOrDefault(s.charAt(0), 0);
            if (n != 0) {
                list.add(n);
            } else {
                list.add(Integer.parseInt(String.valueOf(s.charAt(0))));
            }
        }
        list.sort(Collections.reverseOrder());
        return list;
    }

    boolean straight() {
        List<Integer> temp_list = this.getHandValues();
        Collections.sort(temp_list);        // as was reversely sorted.so, sort again
        for (int i = 1; i < temp_list.size(); i++) {
            if (temp_list.get(i) != temp_list.get(i - 1) + 1) {
                return false;
            }
        }
        return true;
    }

    boolean flush() {
        String[] all = this.card.split(" ");
        char c = all[0].charAt(1);
        for (String s : all) {
            if (s.charAt(1) != c) {
                return false;
            }
        }
        return true;
    }

    boolean straight_flush() {
        return straight() && flush();
    }

    boolean royal_flush() {
        if (!straight_flush()) {
            return false;
        }
        return list.get(0) == 14;
    }

    boolean four_of_kind() {
        return Collections.frequency(list, list.get(0)) == 4 || Collections.frequency(list, list.get(1)) == 4;
    }

    boolean three_of_kind() {
        return Collections.frequency(list, list.get(0)) == 3 || Collections.frequency(list, list.get(1)) == 3
                || Collections.frequency(list, list.get(2)) == 3;
    }

    boolean pair() {
        for (int i : list) {
            if (Collections.frequency(list, i) == 2) return true;
        }
        return false;
    }

    boolean double_pair() {
        int c = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            if (Collections.frequency(list, list.get(i)) == 2) {
                c++;
                i++;
            }
        }
        return c == 2;
    }

    boolean full_house() {
        return three_of_kind() && pair();
    }

    public Result compareWith(PokerHandRank hand) {
        int selfState = hand_cards_state.get(this.getState());
        int opponentState = hand_cards_state.get(hand.getState());
        if (selfState > opponentState) {
            return Result.WIN;
        } else if (selfState < opponentState) {
            return Result.LOSS;
        } else {
            List<Integer> selfList = this.getHandValues();
            List<Integer> opponentList = hand.getHandValues();
            for (int i = 0; i < selfList.size(); i++) {
                int self = selfList.get(i);
                int opponent = opponentList.get(i);
                if (self > opponent) return Result.WIN;
                else if (self < opponent) return Result.LOSS;
            }
        }
        return Result.TIE;
    }

    public static void main(String[] args) {
        PokerHandRank hand = new PokerHandRank("2D AC 3H 4H 5S");
        PokerHandRank opponent = new PokerHandRank("AH AC 5H 6H 7S");
        System.out.println(hand.compareWith(opponent));
    }
}