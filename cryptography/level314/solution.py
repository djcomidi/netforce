#!/usr/bin/env python3

from itertools import product, permutations
import os
import subprocess
import sys
import urllib.request

PEGS = [ 24, 12, 3, 17, 7, 16, 20, 1 ]
ROTORS = [
    [ 4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9 ],
    [ 0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4 ],
    [ 1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14 ],
    [ 4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1 ],
    [ 21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10 ],
    [ 9, 15, 6, 21, 14, 20, 12, 5, 24, 16, 1, 4, 13, 7, 25, 17, 3, 10, 0, 18, 23, 11, 8, 2, 19, 22 ],
    [ 13, 25, 9, 7, 6, 17, 2, 23, 12, 24, 18, 22, 1, 14, 20, 5, 0, 8, 21, 11, 15, 4, 10, 16, 3, 19 ],
    [ 5, 10, 16, 7, 19, 11, 23, 14, 2, 1, 9, 18, 15, 3, 25, 17, 0, 12, 4, 22, 13, 8, 20, 24, 6, 21 ]
]
REFLECTOR = [ 24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19 ]

## retrieve morse and translate message
MESSAGE_URL = "https://net-force.nl/challenge/level314/message.wav"
wav_file, _ = urllib.request.urlretrieve(MESSAGE_URL)
# linux app morse2ascii (for debian/ubuntu: sudo apt-get install morse2ascii)
message = subprocess.check_output(["morse2ascii", wav_file], universal_newlines=True, stderr=subprocess.DEVNULL)
os.unlink(wav_file)
message = message.upper()
print("EMCODED = {0}".format(message))

## the inverse rotors (calculated beforehand)
INVRTRS = [
    [20, 22, 24, 6, 0, 3, 5, 15, 21, 25, 1, 4, 2, 10, 12, 19, 7, 23, 18, 11, 17, 8, 13, 16, 14, 9],
    [0, 9, 15, 2, 25, 22, 17, 11, 5, 1, 3, 10, 14, 19, 24, 20, 16, 6, 4, 13, 7, 23, 12, 8, 21, 18],
    [19, 0, 6, 1, 15, 2, 18, 3, 16, 4, 20, 5, 21, 13, 25, 7, 24, 8, 23, 9, 22, 11, 17, 10, 14, 12],
    [7, 25, 22, 21, 0, 17, 19, 13, 11, 6, 20, 15, 23, 16, 2, 4, 9, 12, 1, 18, 10, 3, 24, 14, 8, 5],
    [16, 2, 24, 11, 23, 22, 4, 13, 5, 19, 25, 14, 18, 12, 21, 9, 20, 3, 10, 6, 8, 0, 17, 15, 7, 1],
    [18, 10, 23, 16, 11, 7, 2, 13, 22, 0, 17, 21, 6, 12, 4, 1, 9, 15, 19, 24, 5, 3, 25, 20, 8, 14],
    [16, 12, 6, 24, 21, 15, 4, 3, 17, 2, 22, 19, 8, 0, 13, 20, 23, 5, 10, 25, 14, 18, 11, 7, 9, 1],
    [16, 9, 8, 13, 18, 0, 24, 3, 21, 10, 1, 5, 17, 20, 7, 12, 2, 15, 11, 4, 22, 25, 19, 6, 23, 14]
]

## decode message
TWENTYSIX = list(range(26))
ROTORCOMBOS = reversed(list(permutations([0,1,2,3,4,5,6,7], r=3)))
for rA, rB, rC in ROTORCOMBOS:
    pegA, pegB, pegC = PEGS[rA], PEGS[rB], PEGS[rC]
    for pA, pB, pC in product(TWENTYSIX, repeat=3):
        dc = []
        for c in message:
            code = ord(c) - ord('A')
            code = ROTORS[rA][(code+pA)%26]
            code = ROTORS[rB][(code+pB)%26]
            code = ROTORS[rC][(code+pC)%26]
            code = REFLECTOR[code]
            code = (INVRTRS[rC][code] - pC + 26) % 26
            code = (INVRTRS[rB][code] - pB + 26) % 26
            code = (INVRTRS[rA][code] - pA + 26) % 26
            dc.append(chr(code + ord('A')))
            pA = (pA + 1) % 26
            pB = (pB + 1) % 26 if pA == pegA else pB
        dmsg = ''.join(dc)
        if "PASSWORD" in dmsg:
            print("ROTOR 1 = #{0} @ {1}".format(rA+1, pA))
            print("ROTOR 2 = #{0} @ {1}".format(rB+1, pB))
            print("ROTOR 3 = #{0} @ {1}".format(rC+1, pC))
            print("DECODED = {0}".format(dmsg))
            sys.exit(0)
