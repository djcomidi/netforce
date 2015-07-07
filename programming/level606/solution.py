#!/usr/bin/env python

from itertools import permutations

def queenPossibilities(n):
    COLS = ROWS = list(range(n))
    t = 0
    for p in permutations(COLS):
        coords = list(zip(ROWS,p))
        hits = 0
        for i, cA in enumerate(coords):
            for cB in coords[i+1:]:
                if abs(cA[0]-cB[0]) == abs(cA[1]-cB[1]):
                    hits += 1
        if hits == 0:
            t += 1
    return t

sumt = 0
for n in range(4,11):
    t = queenPossibilities(n)
    sumt += t
    info = "{0}\t{1}\t{2}".format(n,t,sumt)
    print(info)

