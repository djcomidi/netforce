#!/usr/bin/env python3

import urllib.request

URL = "https://net-force.nl/challenge/level604/aap.txt"
html = urllib.request.urlopen(URL).read().decode().strip()
TBL = html.split("\n")
LIMX, LIMY = len(TBL[0]), len(TBL)
TARGET = ["123","321"]

t = 0
for x in range(LIMX):
    for y in range(LIMY):
        if x + 2 < LIMX:
            subE = TBL[y][x] + TBL[y][x+1] + TBL[y][x+2]
            if subE in TARGET: t += 1
        if x + 2 < LIMX and y + 2 < LIMY:
            subSE = TBL[y][x] + TBL[y+1][x+1] + TBL[y+2][x+2]
            if subSE in TARGET: t += 1
        if y + 2 < LIMY:
            subS = TBL[y][x] + TBL[y+1][x] + TBL[y+2][x]
            if subS in TARGET: t += 1
        if x > 1 and y + 2 < LIMY:
            subSW = TBL[y][x] + TBL[y+1][x-1] + TBL[y+2][x-2]
            if subSW in TARGET: t += 1
print(t)
