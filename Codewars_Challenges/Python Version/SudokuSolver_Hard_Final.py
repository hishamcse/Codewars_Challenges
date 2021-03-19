"""
Created on Wed Mar  4 12:46:30 2021

@author: Syed Jarullah Hisham

* Challenge: https://www.codewars.com/kata/5588bd9f28dbb06f43000085

* *****
"""

"""
    most effecient and fast algorithm.
    must see the resource link for details
"""




import copy
import math

def sudoku_solver(puzzle):
    sudoku = Sudoku()

    sudoku.setBoard(puzzle)

    if not sudoku.valid:
        raise ValueError

    sudoku.solve()

    if len(sudoku.result_boards) != 1:
        raise ValueError

    return sudoku.result_boards[0]


class Sudoku:

    digits = [1, 2, 3, 4, 5, 6, 7, 8, 9]
    units = []    # all items of a cell corresponding its row,col,box
    peers = []    # neighbours of a cell

    for i in range(81):
        units.append([])

        #  Row
        units[i].append([])
        r = int(i/9)
        for c in range(9):
            units[i][0].append(r*9+c)

        #  Column
        units[i].append([])
        c = int(i % 9)
        for r in range(9):
            units[i][1].append(r*9+c)

        #  Box
        units[i].append([])
        box_row = int(int(i/9)/3)
        box_col = int(int(i % 9)/3)
        for r in range(box_row*3, box_row*3 + 3):
            for c in range(box_col*3, box_col*3 + 3):
                units[i][2].append(r*9 + c)

        #  Neighbours
        peers.append([])
        for unit in units[i]:
            for cell in unit:
                if cell not in peers[i]:
                    peers[i].append(cell)

        peers[i].remove(i)


    def __init__(self):
        self.mask = []
        self.valid = True
        self.solutions = []
        self.result_boards = []


    def setBoard(self, board):
        self.mask = []
        self.valid = True
        self.solutions = []
        self.result_boards = []

        if not self.validate_dim(board):
            self.valid = False

        self.mask = [0x1ff]*81

        for r in range(len(board)):
            for c in range(len(board[r])):
                if board[r][c]:
                   if not self.valid_set(r*9+c, board[r][c]):
                      self.valid = False
                      return


    def validate_dim(self, board):
        if len(board) != 9:
            return False
        for i in range(len(board)):
            if len(board[i]) != 9:
                return False
        return True


    def mask_to_board(self, mask):
        board = []
        for r in range(9):
            board.append([0]*9)
        for r in range(9):
            for c in range(9):
                if self.is_single_bit(mask[r*9+c]):
                    for d in self.digits:
                        if mask[r*9+c] & (1 << (d-1)):
                            board[r][c] = d

        return board


    def clone(self):
        sudoku = Sudoku()
        sudoku.mask = copy.copy(self.mask)
        sudoku.valid = self.valid
        return sudoku


    def solve(self):
        self.final_solver()

        for result in self.solutions:
            self.result_boards.append(self.mask_to_board(result))


    def final_solver(self):
        cell = self.find_min_possibilities_vacant()

        if cell is None:
            self.add_solution(self.mask)
            return

        for d in self.digits:

            if not (self.mask[cell] & (1 << (d-1))):
                continue

            sudoku = self.clone()

            if not sudoku.valid_set(cell, d):
                continue

            sudoku.final_solver()

            if len(sudoku.solutions) > 0:
                for solution in sudoku.solutions:
                    self.add_solution(solution)

            if len(self.solutions) >= 2:
                return


    def is_single_bit(self, m):
        return (m & (m-1)) == 0


    def count_bits(self, m):
        count = 0
        while m:
            m &= (m-1)
            count += 1

        return count


    def add_solution(self, mask):
        for result in self.solutions:
            if result == mask:
                return
        self.solutions.append(copy.deepcopy(mask))


    def find_min_possibilities_vacant(self):
        vacant_cnt = 0
        best_vacant_possibilities = 10
        best_vacant_i = 0

        for i in range(81):
            if best_vacant_possibilities == 2:
                break
            if not self.is_single_bit(self.mask[i]):
                vacant_cnt += 1
                choices = self.count_bits(self.mask[i])

                if choices < best_vacant_possibilities:
                    best_vacant_possibilities = choices
                    best_vacant_i = i

        if (vacant_cnt == 0):
            return None
        return best_vacant_i


    def valid_set(self, cell, d):
        other_values = [d2 for d2 in self.digits if d2 !=
                        d and self.mask[cell] & (1 << (d2-1))]
        for d2 in other_values:
            if not self.clear(cell, d2):
                return False
        return True


    def clear(self, cell, d):
        if not (self.mask[cell] & (1 << (d-1))):
            return True

        self.mask[cell] &= ~(1 << (d-1))

        # Rule 1
        if self.mask[cell] == 0:    # Rule 3
            return False
        elif self.is_single_bit(self.mask[cell]):
            value = int(math.log2(self.mask[cell]))+1
            for cell2 in self.peers[cell]:
                if not self.clear(cell2, value):
                    return False
        # Rule 2
        for u in self.units[cell]:
            available_places = [
                cell2 for cell2 in u if self.mask[cell2] & (1 << (d-1))]
            if len(available_places) == 0:    # Rule 4
                return False
            elif len(available_places) == 1:
                if not self.valid_set(available_places[0], d):
                    return False
        return True


puzzle = [[0, 0, 6, 1, 0, 0, 0, 0, 8],
          [0, 8, 0, 0, 9, 0, 0, 3, 0],
          [2, 0, 0, 0, 0, 5, 4, 0, 0],
          [4, 0, 0, 0, 0, 1, 8, 0, 0],
          [0, 3, 0, 0, 7, 0, 0, 4, 0],
          [0, 0, 7, 9, 0, 0, 0, 0, 3],
          [0, 0, 8, 4, 0, 0, 0, 0, 6],
          [0, 2, 0, 0, 5, 0, 0, 8, 0],
          [1, 0, 0, 0, 0, 2, 5, 0, 0]]

print(sudoku_solver(puzzle))
