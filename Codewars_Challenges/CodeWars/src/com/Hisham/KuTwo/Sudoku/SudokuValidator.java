package com.Hisham.KuTwo.Sudoku;

/*
 * Challenge: https://www.codewars.com/kata/529bf0e9bdf7657179000008
 * *****
 */

import java.util.HashSet;
import java.util.Set;

public class SudokuValidator {

    public static boolean valid_row(int row, int[][] grid) {
        int[] temp = grid[row];
        Set<Integer> set = new HashSet<>();
        for (int value : temp) {
            if (value <= 0 || value > 9) {
                return false;
            }

            //Checking for repeated values.
            else {
                if (!set.add(value)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean valid_col(int col, int[][] grid) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i][col] <= 0 || grid[i][col] > 9) {
                return false;
            }

            // Checking for repeated values.
            else if (grid[i][col] != 0) {
                if (!set.add(grid[i][col])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean valid_subsquares(int[][] grid) {
        for (int row = 0; row < 9; row = row + 3) {
            for (int col = 0; col < 9; col = col + 3) {
                Set<Integer> set = new HashSet<>();
                for (int r = row; r < row + 3; r++) {
                    for (int c = col; c < col + 3; c++) {
                        if (grid[r][c] <= 0 || grid[r][c] > 9) {
                            return false;
                        }
                        // Checking for repeated values.
                        else if (grid[r][c] != 0) {
                            if (!set.add(grid[r][c])) {
                                return false;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }

    public static boolean valid_board(int[][] grid) {
        for (int i = 0; i < 9; i++) {
            boolean res1 = valid_row(i, grid);
            boolean res2 = valid_col(i, grid);
            if (!res1 || !res2) {
                return false;
            }
        }

        return valid_subsquares(grid);
    }

    private static boolean check_sum(int[][] sudoku) {
        int sum = 45;
        for (int i = 0; i < 9; i++) {
            int s = 0;
            for (int j = 0; j < 9; j++) {
                s += sudoku[i][j];
            }
            if (s != sum) return false;
        }
        return true;
    }

    public static boolean check(int[][] sudoku) {
        if (!valid_board(sudoku)) {
            return false;
        }
        return check_sum(sudoku);
    }

    public static void main(String[] args) {
        int[][] sudoku = {
                {5, 3, 4, 6, 7, 8, 9, 1, 2},
                {6, 7, 2, 1, 9, 5, 3, 4, 8},
                {1, 9, 8, 3, 4, 2, 5, 6, 7},
                {8, 5, 9, 7, 6, 1, 4, 2, 3},
                {4, 2, 6, 8, 5, 3, 7, 9, 1},
                {7, 1, 3, 9, 2, 4, 8, 5, 6},
                {9, 6, 1, 5, 3, 7, 2, 8, 4},
                {2, 8, 7, 4, 1, 9, 6, 3, 5},
                {3, 4, 5, 2, 8, 6, 1, 7, 9}
        };
        System.out.println(check(sudoku));
    }
}
