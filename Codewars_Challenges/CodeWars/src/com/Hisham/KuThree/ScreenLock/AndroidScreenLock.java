package com.Hisham.KuThree.ScreenLock;

/*
  Not a kata of codewars. It was a optional work of this challenge.
  Find total patterns possible in android with minimum length 4
  Slight Modification of the original kata solution
 */

public class AndroidScreenLock {

    private static final int TOTAL_POINTS = 9;
    private static final int[] POINTS = {0, 1, 2, 3, 4, 5, 6, 7, 8};     // A, B, C, D, E, F, G, H, I

    int total;

    private int calculate_By_DFS() {
        boolean[] marked = new boolean[TOTAL_POINTS];
        total = 0;
        for (int v : POINTS) {
            dfs(marked, v, 0);
        }
        return total;
    }

    private boolean valid_straight_line(int v, int w, boolean[] marked) {
        int row1 = v / 3;
        int row2 = w / 3;
        int col1 = v % 3;
        int col2 = w % 3;
        double avgRow = (double) (row1 + row2) / 2;
        double avgCol = (double) (col1 + col2) / 2;
        if (avgRow == (int) avgRow && avgCol == (int) avgCol) {    // checking if this cell really exits or not
            return marked[(int) (3 * avgRow + avgCol)];
        }
        return true;
    }

    private void dfs(boolean[] marked, int v, int count) {
        count++;
        if (count >= 4) {     // modification
            total++;
        }
        marked[v] = true;
        for (int w : POINTS) {
            if (v != w && !marked[w] && valid_straight_line(v, w, marked)) {
                dfs(marked, w, count);
            }
        }
        marked[v] = false;
    }

    public int calculateCombinations() {
        return calculate_By_DFS();
    }
}