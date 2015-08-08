from itertools import product, permutations
import os
import subprocess
import sys
import urllib.request

PEGS = [24, 12, 3, 17, 7, 16, 20, 1]
with open('rotors.txt', 'r') as f:
    ROTORS = []
    for line in f.readlines():
        ROTORS.append([int(d) for d in line.strip().split(",")])
REFL = "24,17,20,7,16,18,11,3,15,23,13,6,14,10,12,8,4,1,5,25,2,22,21,9,0,19"
REFL = [int(x) for x in REFL.split(",")]
# the inverse rotors
INVRTRS = []
for rotor in ROTORS:
    INVRTRS.append([rotor.index(r) for r in range(26)])

# retrieve morse and translate message
MESSAGE_URL = "https://net-force.nl/challenge/level314/message.wav"
wav_file, _ = urllib.request.urlretrieve(MESSAGE_URL)
# linux app morse2ascii (for debian/ubuntu: sudo apt-get install morse2ascii)
message = subprocess.check_output(["morse2ascii", wav_file],
                                  universal_newlines=True,
                                  stderr=subprocess.DEVNULL)
os.unlink(wav_file)
message = message.upper()
print("ENCODED = {0}".format(message))

# decode message
TWENTYSIX = list(range(26))
ROTORCOMBOS = reversed(list(permutations(range(8), r=3)))
for rA, rB, rC in ROTORCOMBOS:
    pegA, pegB, pegC = PEGS[rA], PEGS[rB], PEGS[rC]
    for pA, pB, pC in product(TWENTYSIX, repeat=3):
        dc = []
        for c in message:
            code = ord(c) - ord('A')
            code = ROTORS[rA][(code+pA) % 26]
            code = ROTORS[rB][(code+pB) % 26]
            code = ROTORS[rC][(code+pC) % 26]
            code = REFL[code]
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
