package com.Hisham.KuTwo.Sudoku;

/*
 * Challenge: https://www.codewars.com/kata/5588bd9f28dbb06f43000085
 * *****
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    private final int[][] board;
    private final List<int[][]> solution_list;

    public SudokuSolver(int[][] grid) {

        if(!validate_dim(grid)){
            throw new IllegalArgumentException();
        }

        board = new int[9][9];

        solution_list = new ArrayList<>();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    if(!validate_setValue(i, j, grid[i][j])) throw new IllegalArgumentException();
                }
            }
        }
    }

    public int[][] solve() {
         enumerate(0);
         return solution_list.get(0);
    }

    private boolean validate_dim(int[][] grid) {
        if (grid.length != 9) return false;
        for (int i = 0; i < 9; i++) {
            if (grid[i].length != 9) return false;
        }
        return true;
    }

    private boolean validate_setValue(int r, int c, int x) {
        if (!check(r, c, x)) return false;
        this.board[r][c] = x;
        return true;
    }

    private boolean check(int r, int c, int x) {
        if (x < 1 || x > 9) return false;

        for (int i = 0; i < 9; i++) {
            if (board[r][i] == x || board[i][c] == x) {
                return false;
            }
        }

        int box_row = r - r % 3;
        int box_column = c - c % 3;
        for (int i = box_row; i < box_row + 3; i++) {
            for (int j = box_column; j < box_column + 3; j++) {
                if (board[i][j] == x) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean enumerate(int k) {
        if (k == 81) {
            solution_list.add(board);
            if (solution_list.size() >= 2) throw new IllegalArgumentException();
            return true;
        }

        int r = k / 9;
        int c = k % 9;

        if (board[r][c] != 0) {
            return enumerate(k + 1);
        }

        for (int x = 1; x <= 9; x++) {      // try 9 possible digits for cell k
            if (check(r, c, x)) {         // unless it violates a Sudoku constraint
                board[r][c] = x;
                enumerate(k + 1);
                if (solution_list.size() >= 2) throw new IllegalArgumentException();
                board[r][c] = 0;
            }
        }
        return true;
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
        SudokuSolver sudokuSolver = new SudokuSolver(puzzle);
        System.out.println(Arrays.deepToString(sudokuSolver.solve()));
    }
}