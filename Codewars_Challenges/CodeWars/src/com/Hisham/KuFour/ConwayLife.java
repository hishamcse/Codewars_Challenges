package com.Hisham.KuFour;

/*
 * Challenge: https://www.codewars.com/kata/52423db9add6f6fc39000354/java
 * *****
 * Resource: https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life
 * *****
 * LifeDebug Class taken from internet
 */

import java.util.Arrays;

public class ConwayLife {

    public static int[][] getGeneration(int[][] cells, int generations) {
        if (generations == 0) {
            return resizedArr(cells);
        }

        int[][] temp = new int[cells.length + 2][cells[0].length + 2];
        for (int i = 0; i < cells.length; i++) {
            System.arraycopy(cells[i], 0, temp[i + 1], 1, cells[i].length);
        }

        int[][] newCells = new int[cells.length + 2][cells[0].length + 2];
        boolean iterAbove, iterDown, iterLeft, iterRight;
        int startX, endX, startY, endY;

        for (int i = 0; i < temp.length; i++) {
            iterAbove = i - 1 >= 0;
            iterDown = i + 1 < temp.length;

            for (int j = 0; j < temp[i].length; j++) {
                iterLeft = j - 1 >= 0;
                iterRight = j + 1 < temp[i].length;

                int currentCell = temp[i][j];
                int liveCount = 0;

                // detecting ranges for traverse of rows & cols
                startX = iterAbove ? (i - 1) : i;
                endX = iterDown ? (i + 1) : i;
                startY = iterLeft ? (j - 1) : j;
                endY = iterRight ? (j + 1) : j;

                // counting total live neighbour
                for (int x = startX; x <= endX; x++) {
                    for (int y = startY; y <= endY; y++) {
                        liveCount += temp[x][y];
                    }
                }
                liveCount -= currentCell;    // as neighbour of current cell. so, should be excluded itself

                // game logic (see question or wikipedia)
                if (currentCell == 1 && (liveCount < 2 || liveCount > 3)) {
                    newCells[i][j] = 0;
                } else if (currentCell == 0 && liveCount == 3) {
                    newCells[i][j] = 1;
                } else {
                    newCells[i][j] = currentCell;
                }
            }
        }
        return getGeneration(resizedArr(newCells), generations - 1);
    }

    private static int[][] resizedArr(int[][] cells) {   // cutting down to get the answer
        int lowestRow = cells.length;
        int highestRow = 0;
        int lowestCol = cells[0].length;
        int highestCol = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                if (cells[i][j] == 1) {
                    lowestCol = Math.min(j, lowestCol);
                    highestCol = Math.max(j, highestCol);
                    lowestRow = Math.min(i, lowestRow);
                    highestRow = Math.max(i, highestRow);
                }
            }
        }
        int[][] res = new int[highestRow - lowestRow + 1][highestCol - lowestCol + 1];
        for (int i = lowestRow; i <= highestRow; i++) {
            for (int j = lowestCol; j <= highestCol; j++) {
                res[i - lowestRow][j - lowestCol] = cells[i][j];
            }
        }
        return res;
    }

    static class LifeDebug {
        static int LIVE = 1;
        static int DIE = 0;

        public static void print(int[][] cells) {
            for (int[] cell : cells) {
                for (int i = 0; i < cells[0].length; i++) {
                    System.out.print(cell[i]);
                }
                System.out.println();
            }
        }

        public static String htmlize(int[][] cells) {
            StringBuilder sb = new StringBuilder();
            for (int[] cell : cells) {
                for (int i = 0; i < cells[0].length; i++) {
                    int i1 = cell[i];
                    if (i1 == LIVE) {
                        sb.append("▓▓ ");
                    } else if (i1 == DIE) {
                        sb.append("░░ ");
                    }
                }
                sb.append("\n");
            }
            return sb.toString();
        }

        public static boolean equals(int[][] res, int[][] cells) {
            for (int j = 0; j < cells.length; j++) {
                for (int i = 0; i < cells[0].length; i++) {
                    int i1 = cells[j][i];
                    int i2 = res[j][i];
                    if (i1 != i2) {
                        return false;
                    }
                }
            }
            return true;
        }
    }

    public static void main(String[] args) {
        int[][][] gliders = {
                {{1, 0, 0, 0, 1, 0},
                        {0, 1, 1, 1, 1, 1},
                        {1, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 1},
                        {0, 1, 1, 0, 0, 0},
                        {1, 1, 0, 0, 0, 1}},
        };
        System.out.println("Past Grid: " + '\n' + LifeDebug.htmlize(gliders[0]));
        System.out.println(Arrays.deepToString(getGeneration(gliders[0], 1)));
        System.out.println("Answer Grid: " + '\n' + LifeDebug.htmlize(getGeneration(gliders[0], 10)));
    }
}
