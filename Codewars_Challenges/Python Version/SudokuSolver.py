"""
# Chalenge: https://www.codewars.com/kata/5296bc77afba8baa690002d7
"""


a = [0 for l in range(81)]
solvedPuzzle = [[0 for i in range(9)] for j in range(9)]
solved = False


def solve(grid):
    global solved

    solved = False
    # print(grid, flush=True)

    k = 0
    for i in range(9):
        for j in range(9):
            a[k] = grid[i][j]
            k += 1

    for i in range(len(a)):
        if a[i] == 0:
            enumeration(i)

    return solvedPuzzle


def process():
    j = 0
    solvedPuzzle[0][0] = a[0]
    for i in range(1, len(a)):
        if i % 9 == 0:
            j += 1
        solvedPuzzle[j][i % 9] = a[i]


def enumeration(k):
    global solved
    if solved:
        return

    if k == 81:
        solved = True
        process()
        return

    if a[k] != 0:
        enumeration(k + 1)
        return

    for r in range(1, 10):
        a[k] = r
        if not canBacktrack(k):
            enumeration(k + 1)

    a[k] = 0


def canBacktrack(k):
    row = k//9
    m = row * 9
    n = m + 9

    for i in range(m, n):
        if i != k and a[i] == a[k]:
            return True

    column = k % 9
    for i in range(column, 81, 9):
        if i != k and a[i] == a[k]:
            return True

    # box_row = (row // 3) * 3
     # box_column = (column // 3) * 3
    box_row = row - row % 3
    box_column = column - column % 3
    for i in range(box_row, box_row + 3):
        for j in range(box_column, box_column + 3):
            lr = (i * 9 + j)
            if lr != k and a[lr] == a[k]:
                return True

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

print("ans = > ",solve(problem))