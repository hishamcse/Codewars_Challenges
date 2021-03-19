package com.Hisham.KuTwo.Sudoku;

/*
Challenge: https://www.codewars.com/kata/5296bc77afba8baa690002d7
***
Solution: See Python
 */

import java.util.Arrays;

public class Sudoku {

    private final int[] a;
    private boolean solved;
    private int[][] solvedPuzzle;

    public Sudoku(int[][] grid) {
        this.a = new int[81];

        int k = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                a[k++] = grid[i][j];
            }
        }

        for (int i = 0; i < a.length; i++) {
            if (a[i] == 0) {
                enumerate(i);
            }
        }
    }

    public int[][] solve() {
        return solvedPuzzle;
    }

    private void process() {
        solvedPuzzle = new int[9][9];
        int j = -1;
        for (int i = 0; i < a.length; i++) {
            if (i <= 0 || i % 9 == 0) {
                j++;
            }
            solvedPuzzle[j][i % 9] = a[i];
        }
    }

    private void enumerate(int k) {

        if (solved) {
            return;
        }

        // found a solution
        if (k == 81) {
            solved = true;
            process();
            return;
        }

        // cell k initially filled in; recur on next cell
        if (a[k] != 0) {
            enumerate(k + 1);
            return;
        }

        for (int r = 1; r <= 9; r++) {      // try 9 possible digits for cell k
            a[k] = r;
            if (!canBacktrack(k)) {         // unless it violates a Sudoku constraint
                enumerate(k + 1);
            }
        }
        a[k] = 0;         // clean up
    }

    private boolean canBacktrack(int k) {
        // Row
        int row = k / 9;
        for (int i = row * 9; i < row * 9 + 9; i++) {
            if (i != k) {
                if (a[i] == a[k]) {
                    return true;
                }
            }
        }
        // Column
        int column = k % 9;
        for (int i = column; i < 81; i += 9) {
            if (i != k) {
                if (a[i] == a[k]) {
                    return true;
                }
            }
        }

        // first find the starting point of box with 'k' in it.
        int box_row = boxRow(k);
        int box_column = boxColumn(k);
        for (int i = box_row; i < box_row + 3; i++) {
            for (int j = box_column; j < box_column + 3; j++) {
                int l = i * 9 + j;
                if (l != k) {
                    if (a[l] == a[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int boxRow(int k) {
        int val = k / 9;
        int val2 = val / 3;
        return val2 * 3;
    }

    private int boxColumn(int k) {
        int val = k % 9;
        int val2 = val / 3;
        return val2 * 3;
    }

    public static void main(String[] args) {

        int[][] puzzle = {{0, 0, 6, 1, 0, 0, 0, 0, 8},
                {0, 8, 0, 0, 9, 0, 0, 3, 0},
                {2, 0, 0, 0, 0, 5, 4, 0, 0},
                {4, 0, 0, 0, 0, 1, 8, 0, 0},
                {0, 3, 0, 0, 7, 0, 0, 4, 0},
                {0, 0, 7, 9, 0, 0, 0, 0, 3},
                {0, 0, 8, 4, 0, 0, 0, 0, 6},
                {0, 2, 0, 0, 5, 0, 0, 8, 0},
                {1, 0, 0, 0, 0, 2, 5, 0, 0}};
        Sudoku sudokuSolver = new Sudoku(puzzle);
        System.out.println(Arrays.deepToString(sudokuSolver.solve()));
    }
}
