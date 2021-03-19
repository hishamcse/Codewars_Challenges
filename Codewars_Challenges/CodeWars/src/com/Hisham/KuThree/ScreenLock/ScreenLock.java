package com.Hisham.KuThree.ScreenLock;

/*
 * Challenge: https://www.codewars.com/kata/585894545a8a07255e0002f1
 * ******
 * Resource:
 *   https://www.quora.com/Android-operating-system-How-many-combinations-does-Android-9-point-unlock-have
 *   https://www.careercup.com/question?id=5663422257561600
 */

public class ScreenLock {

    private static final int TOTAL_POINTS = 9;
    private static final int[] POINTS = {0, 1, 2, 3, 4, 5, 6, 7, 8};     // A, B, C, D, E, F, G, H, I

    int total;

    private int calculate_By_DFS(int node, int patternLength) {
        boolean[] marked = new boolean[TOTAL_POINTS];
        total = 0;
        dfs(marked, node, patternLength, 0);
        return total;
    }

    private boolean valid_straight_line(int v, int w, boolean[] marked) {
        int row1 = v / 3;
        int row2 = w / 3;
        int col1 = v % 3;
        int col2 = w % 3;
        double avgRow = (double) (row1 + row2) / 2;
        double avgCol = (double) (col1 + col2) / 2;
        if (avgRow == (int) avgRow && avgCol == (int) avgCol) {   // checking if this cell really exits or not
            return marked[(int) (3 * avgRow + avgCol)];
        }
        return true;
    }

    private void dfs(boolean[] marked, int v, int patternLength, int count) {
        count++;
        if (count % patternLength == 0) {
            total++;
            return;
        }
        marked[v] = true;
        for (int w : POINTS) {
            if (v != w && !marked[w] && valid_straight_line(v, w, marked)) {
                dfs(marked, w, patternLength, count);
            }
        }
        marked[v] = false;
    }

    public int calculateCombinations(char startPosition, int patternLength) {
        if (patternLength > 9 || patternLength == 0) {
            return 0;
        }
        if (patternLength == 1) {
            return 1;
        }
        int node = startPosition - 65;       // converting to integer ranged between 0-9
        return calculate_By_DFS(node, patternLength);
    }
}