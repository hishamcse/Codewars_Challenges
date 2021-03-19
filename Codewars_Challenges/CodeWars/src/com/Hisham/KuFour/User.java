package com.Hisham.KuFour;

/*
 * Challenge: https://www.codewars.com/kata/51fda2d95d6efda45e00004e
 */

public class User {

    public int rank;
    public int progress;
    private int currentArrPos;

    private static final int[] allRanks = new int[]{-8, -7, -6, -5, -4, -3, -2, -1, 1, 2, 3, 4, 5, 6, 7, 8};

    public User() {
        this.rank = -8;
        this.progress = 0;
        currentArrPos = 0;
    }

    public void incProgress(int rank) {
        if (rank < -8 || rank == 0 || rank > 8) {
            throw new IllegalArgumentException();
        }
        if (this.rank == 8) {
            return;
        }
        int rankPos = -1;
        for (int i = 0; i < allRanks.length; i++) {
            if (allRanks[i] == rank) {
                rankPos = i;
                break;
            }
        }
        if (this.rank == rank) {
            this.progress += 3;
        } else if (currentArrPos != 0 && rank == allRanks[currentArrPos - 1]) {
            this.progress += 1;
        } else if (16 - currentArrPos > 0 && this.rank < rank) {
            this.progress += (10 * (rankPos - currentArrPos) * (rankPos - currentArrPos));
        }
        if (this.progress >= 100) {
            while (this.progress >= 100) {
                currentArrPos += 1;
                this.rank = allRanks[currentArrPos];
                if (this.rank == 8) {
                    this.progress = 0;
                    return;
                }
                this.progress -= 100;
            }
        }
    }

    public static void main(String[] args) {
        User user = new User();
        System.out.println(user.rank);
        System.out.println(user.progress);
        user.incProgress(-7);
        System.out.println(user.progress);
        user.incProgress(-5);
        System.out.println(user.progress);
        System.out.println(user.rank);
        user.incProgress(1);
        System.out.println(user.progress);
        System.out.println(user.rank);
    }
}