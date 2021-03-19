"""
* Challenge: https://www.codewars.com/kata/55171d87236c880cea0004c6
****
"""

"""
    time limit exceeded
"""


def solve(board):
    sudoku = Sudoku(board)
    result = sudoku.solve()

    return result


class Sudoku:
    board = []

    def __init__(self, board):
        self.board = board

    def canBackTrack(self, r, c, x):
        for i in range(9):
            if self.board[r][i] == x:
                return True
            if self.board[i][c] == x:
                return True

        box_row = r - r % 3
        box_col = c - c % 3

        for i in range(box_row, box_row+3):
            for j in range(box_col, box_col+3):
                if self.board[i][j] == x:
                    return True

        return False

    def solve(self):
        if self.solve_exist(0):
            return self.board
        else:
            return None

    def solve_exist(self, k):
        if(k == 81):
            return True

        r = int(k/9)
        c = k % 9

        if self.board[r][c] != 0:
            return self.solve_exist(k+1)

        for x in range(1, 10):
            if not self.canBackTrack(r, c, x):
                self.board[r][c] = x
                if self.solve_exist(k+1):
                    return True

                self.board[r][c] = 0

        return False


problem = [[0, 0, 5, 0, 0, 0, 8, 0, 0],
           [0, 2, 0, 8, 0, 9, 0, 7, 0],
           [3, 0, 0, 0, 4, 0, 0, 0, 1],
           [0, 3, 0, 2, 0, 6, 0, 1, 0],
           [0, 0, 2, 0, 0, 0, 5, 0, 0],
           [0, 7, 0, 5, 0, 4, 0, 6, 0],
           [2, 0, 0, 0, 6, 0, 0, 0, 4],
           [0, 8, 0, 4, 0, 2, 0, 9, 0],
           [0, 0, 7, 0, 0, 0, 2, 0, 0]]

print("ans = > ", solve(problem))
