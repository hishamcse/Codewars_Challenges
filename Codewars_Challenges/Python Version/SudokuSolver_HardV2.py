"""
Created on Wed Mar  3 03:17:30 2021

@author: Syed Jarullah Hisham

* Challenge: https://www.codewars.com/kata/5588bd9f28dbb06f43000085

* *****
"""

"""
    check validity but not efficient
"""


import copy


def sudoku_solver(board):
    sudoku = Sudoku(board)
    if not sudoku.valid:
        raise ValueError

    sudoku.solve()

    if len(sudoku.solutions) != 1:
        raise ValueError

    return sudoku.solutions[0]


class Sudoku:

    def __init__(self, board):
        self.board = []
        self.solutions = []

        self.valid = True

        for i in range(9):
            r = [0]*9
            self.board.append(r)

        if not self.validate_dim(board):
            self.valid = False
            return

        for r in range(len(board)):
            for c in range(len(board[r])):
                if board[r][c]:
                    if not self.valid_set(r, c, board[r][c]):
                        self.valid = False
                        return

    def validate_dim(self, board):
        if len(board) != 9:
            return False
        for i in range(len(board)):
            if len(board[i]) != 9:
                return False
        return True

    def valid_set(self, r, c, x):
        if not self.check(r, c, x):
            return False

        self.board[r][c] = x
        return True

    def check(self, r, c, x):
        if not isinstance(x, int):
            return False
        if x < 1 or x > 9:
            return False

        for i in range(9):
            if self.board[r][i] == x:
                return False
            if self.board[i][c] == x:
                return False

        box_row = r - r % 3
        box_col = c - c % 3

        for i in range(box_row, box_row+3):
            for j in range(box_col, box_col+3):
                if self.board[i][j] == x:
                    return False

        return True

    def solve(self):
        self.solve_exist(0)
        return self.solutions

    def solve_exist(self, k):
        if(k == 81):
            self.solutions.append(copy.deepcopy(self.board))
            return

        r = int(k/9)
        c = k % 9

        if self.board[r][c] != 0:
            return self.solve_exist(k+1)

        for x in range(1, 10):
            if self.check(r, c, x):
                self.board[r][c] = x

                self.solve_exist(k+1)

                if len(self.solutions) >= 2:
                    return

                self.board[r][c] = 0

        return False


problem = [[0, 0, 6, 1, 0, 0, 0, 0, 8],
           [0, 8, 0, 0, 9, 0, 0, 3, 0],
           [2, 0, 0, 0, 0, 5, 4, 0, 0],
           [4, 0, 0, 0, 0, 1, 8, 0, 0],
           [0, 3, 0, 0, 7, 0, 0, 4, 0],
           [0, 0, 7, 9, 0, 0, 0, 0, 3],
           [0, 0, 8, 4, 0, 0, 0, 0, 6],
           [0, 2, 0, 0, 5, 0, 0, 8, 0],
           [1, 0, 0, 0, 0, 2, 5, 0, 0]]

print("ans = > ", sudoku_solver(problem))
