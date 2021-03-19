package com.Hisham.KuFour.PokerHand;

/*
 * Challenge: https://www.codewars.com/kata/586423aa39c5abfcec0001e6
 * ****
 * Resource:
 *  https://en.wikipedia.org/wiki/Texas_hold_%27em#Hand_values
 */

import java.util.*;

public class PokerHandSort implements Comparable<PokerHandSort> {

    enum Result {WIN, LOSS, TIE}

    String card;
    List<Integer> list;
    Map<Integer, Integer> map;
    PokerHandSort opponentPoker;
    int state;

    private static final Map<Character, Integer> card_value = new HashMap<>() {{
        put('T', 10);
        put('J', 11);
        put('Q', 12);
        put('K', 13);
        put('A', 14);
    }};

    PokerHandSort(String hand) {
        card = hand;
        list = this.getSortedHandValues();
        map = this.count_elements(list);       // for performance issues, we have to precalculate this
        opponentPoker = null;
        state = getState();
    }

    private List<Integer> getSortedHandValues() {
        String[] all = this.card.split(" ");
        List<Integer> valueList = new ArrayList<>();
        for (String string : all) {
            int n = card_value.getOrDefault(string.charAt(0), 0);
            if (n != 0) {
                valueList.add(n);
            } else {
                valueList.add(Integer.parseInt(String.valueOf(string.charAt(0))));
            }
        }
        Collections.sort(valueList);
        return valueList;
    }

    private Map<Integer, Integer> count_elements(List<Integer> cardValues) {
        Set<Integer> set = new HashSet<>(cardValues);
        Map<Integer, Integer> counters = new HashMap<>();

        for (Integer i : set) {
            int count = 0;
            for (Integer card : cardValues) {
                if (card.equals(i)) {
                    count++;
                }
            }
            counters.put(i, count);
        }
        return counters;
    }

    public int getState() {
        if (royal_flush()) return 10;
        else if (straight_flush()) return 9;
        else if (four_of_kind()) return 8;
        else if (full_house()) return 7;
        else if (flush()) return 6;
        else if (straight()) return 5;
        else if (three_of_kind()) return 4;
        else {               // no new or separated methods as other solution. just for performance issue
            int count = 0;
            for (Integer i : map.values()) {
                if (i == 2) count++;
            }

            if (count == 2) {
                return 3;       // double_pair
            } else if (count == 1) {
                return 2;       // single_pair
            } else {
                return 1;       // high_card
            }
        }
    }

    // modified than the other kata
    boolean straight() {
        if (list.contains(14) && list.contains(2) && list.contains(3) && list.contains(4) && list.contains(5)) {
            return true;
        }
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) != list.get(i - 1) + 1) {
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

    boolean royal_flush() {               // modified due to new condition for straight & straight_flush
        if (!straight_flush()) {
            return false;
        }
        return list.contains(13) && list.contains(14);
    }

    boolean four_of_kind() {           // modified due to performance issue
        return map.containsValue(4);
    }

    boolean full_house() {             // modified due to performance issue
        return map.containsValue(3) && map.containsValue(2);
    }

    boolean three_of_kind() {          // modified due to performance issue
        return map.containsValue(3);
    }

    Result break_straight_flush(List<Integer> selfList, List<Integer> opponentList) {
        boolean selfVar = selfList.contains(14) && selfList.contains(5);
        boolean opponentVar = opponentList.contains(14) && opponentList.contains(5);

        if (selfVar && !opponentVar) {
            return Result.LOSS;
        } else if (!selfVar && opponentVar) {
            return Result.WIN;
        } else if (selfVar) {         // oppo must be true here automatically
            return Result.TIE;
        } else {
            return breakTie(selfList, opponentList);
        }
    }

    Result break_full_house() {
        int selfThree = 0, opponentThree = 0;
        int selfTwo = 0, opponentTwo = 0;
        for (Integer i : map.keySet()) {
            if (map.get(i) == 3) {
                selfThree = i;
            } else {
                selfTwo = i;
            }
        }

        for (Integer i : opponentPoker.map.keySet()) {
            if (opponentPoker.map.get(i) == 3) {
                opponentThree = i;
            } else {
                opponentTwo = i;
            }
        }

        if (selfThree > opponentThree) {
            return Result.WIN;
        } else if (selfThree < opponentThree) {
            return Result.LOSS;
        } else {
            if (selfTwo > opponentTwo) {
                return Result.WIN;
            } else if (selfTwo < opponentTwo) {
                return Result.LOSS;
            } else {
                return Result.TIE;
            }
        }
    }

    Result break_four_of_kind(Map<Integer, Integer> selfMap, Map<Integer, Integer> opponentMap) {
        selfMap = new HashMap<>(selfMap);
        opponentMap = new HashMap<>(opponentMap);
        int selfFirst = 0, opponentFirst = 0;
        for (Integer i : selfMap.keySet()) {
            if (selfMap.get(i) == 4) {
                selfFirst = i;
                break;
            }
        }

        for (Integer i : opponentMap.keySet()) {
            if (opponentMap.get(i) == 4) {
                opponentFirst = i;
                break;
            }
        }

        if (selfFirst > opponentFirst) {
            return Result.WIN;
        } else if (selfFirst < opponentFirst) {
            return Result.LOSS;
        } else {
            selfMap.remove(selfFirst);
            opponentMap.remove(opponentFirst);
            List<Integer> self = new ArrayList<>(selfMap.keySet());
            List<Integer> opponent = new ArrayList<>(opponentMap.keySet());
            return breakTie(self, opponent);
        }
    }

    Result break_three_of_kind(Map<Integer, Integer> selfMap, Map<Integer, Integer> opponentMap) {
        selfMap = new HashMap<>(selfMap);
        opponentMap = new HashMap<>(opponentMap);
        int selfFirst = 0, opponentFirst = 0;
        for (Integer i : selfMap.keySet()) {
            if (selfMap.get(i) == 3) {
                selfFirst = i;
                break;
            }
        }

        for (Integer i : opponentMap.keySet()) {
            if (opponentMap.get(i) == 3) {
                opponentFirst = i;
                break;
            }
        }

        if (selfFirst > opponentFirst) {
            return Result.WIN;
        } else if (selfFirst < opponentFirst) {
            return Result.LOSS;
        } else {
            selfMap.remove(selfFirst);
            opponentMap.remove(opponentFirst);
            List<Integer> self = new ArrayList<>(selfMap.keySet());
            List<Integer> opponent = new ArrayList<>(opponentMap.keySet());
            Collections.sort(self);
            Collections.sort(opponent);
            return breakTie(self, opponent);
        }
    }

    Result break_double_pair(Map<Integer, Integer> selfMap, Map<Integer, Integer> opponentMap) {
        List<Integer> selfList = new ArrayList<>();
        List<Integer> opponentList = new ArrayList<>();
        Integer selfFirst = 0, opponentFirst = 0;

        for (Integer i : selfMap.keySet()) {
            if (selfMap.get(i) == 2) {
                selfList.add(i);
            } else {
                selfFirst = i;
            }
        }

        for (Integer card : opponentMap.keySet()) {
            if (opponentMap.get(card) == 2) {
                opponentList.add(card);
            } else {
                opponentFirst = card;
            }
        }

        Collections.sort(selfList);
        Collections.sort(opponentList);

        if (selfList.get(1) > opponentList.get(1)) {
            return Result.WIN;
        } else if (selfList.get(1) < opponentList.get(1)) {
            return Result.LOSS;
        } else {
            if (selfList.get(0) > opponentList.get(0)) {
                return Result.WIN;
            } else if (selfList.get(0) < opponentList.get(0)) {
                return Result.LOSS;
            } else {
                if (selfFirst > opponentFirst) {
                    return Result.WIN;
                } else if (selfFirst < opponentFirst) {
                    return Result.LOSS;
                } else {
                    return Result.TIE;
                }
            }
        }
    }

    Result break_single_pair(Map<Integer, Integer> selfMap, Map<Integer, Integer> opponentMap) {
        selfMap = new HashMap<>(selfMap);
        opponentMap = new HashMap<>(opponentMap);

        int selfFirst = 0;
        int opponentFirst = 0;
        for (Integer i : selfMap.keySet()) {
            if (selfMap.get(i) == 2) {
                selfFirst = i;
                break;
            }
        }

        for (Integer i : opponentMap.keySet()) {
            if (opponentMap.get(i) == 2) {
                opponentFirst = i;
                break;
            }
        }

        if (selfFirst > opponentFirst) {
            return Result.WIN;
        } else if (selfFirst < opponentFirst) {
            return Result.LOSS;
        } else {
            selfMap.remove(selfFirst);
            opponentMap.remove(opponentFirst);
            List<Integer> self = new ArrayList<>(selfMap.keySet());
            List<Integer> opponent = new ArrayList<>(opponentMap.keySet());
            Collections.sort(self);
            Collections.sort(opponent);
            return breakTie(self, opponent);
        }
    }

    private Result breakTie(List<Integer> selfList, List<Integer> opponentList) {   // ascending. So, iterate in opposite
        for (int i = selfList.size() - 1; i >= 0; i--) {
            int self = selfList.get(i);
            int opponent = opponentList.get(i);
            if (self > opponent) return Result.WIN;
            else if (self < opponent) return Result.LOSS;
        }
        return Result.TIE;
    }

    public Result compareWith(PokerHandSort hand) {
        opponentPoker = hand;
        int selfState = this.state;
        int opponentState = hand.state;
        if (selfState > opponentState) {
            return Result.WIN;
        } else if (selfState < opponentState) {
            return Result.LOSS;
        } else if (selfState == 10 || selfState == 6 || selfState == 1) {
            return breakTie(this.list, hand.list);
        } else if (selfState == 5 || selfState == 9) {      // straight or straight flush
            return break_straight_flush(this.list, hand.list);
        } else if (selfState == 7) {                     // full house
            return break_full_house();
        } else if (selfState == 8) {                     // four of kind
            return break_four_of_kind(map, opponentPoker.map);
        } else if (selfState == 4) {                     // three_of_kind
            return break_three_of_kind(map, opponentPoker.map);
        } else if (selfState == 3) {                     // double pair
            return break_double_pair(map, opponentPoker.map);
        } else {                                        // single pair
            return break_single_pair(map, opponentPoker.map);
        }
    }

    @Override
    public int compareTo(PokerHandSort hand) {
        if (this.compareWith(hand) == Result.WIN) {
            return -1;
        } else if (this.compareWith(hand) == Result.LOSS) {
            return 1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {      // for testing purpose
        return card;
    }
}