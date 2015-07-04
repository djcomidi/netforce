#!/usr/bin/env python

from math import ceil, log

s = "1000100101010101100100101001000111000001010010"
LENS = len(s)
MAXP = ceil(log(LENS)/log(2))

print(">>> Error checking...")
flipbit = -1
for p in range(MAXP):
    skip = 2**p
    start = skip - 1
    ones = 0
    for i in range(start,LENS,2*skip):
        ones += s[i:i+skip].count("1")
    if ones % 2 == 1:
        flipbit = max(flipbit,0) + skip

if flipbit != -1:
    msg = ">>> Wrong bit at {0}".format(flipbit)
    print(msg)
    s = s[:flipbit-1] + ('1' if s[flipbit-1] == '0' else '0') + s[flipbit:]
    print(s)
else:
    print(">>> No errors found")

print(">>> Extract data bits")
t = ""
for i, c in enumerate(s):
    if not i+1 in [1,2,4,8,16,32]:
        t += c
print(">>> Chop in bytes and convert to chars")
sol = "".join( chr(int(t[i:i+8],2)) for i in range(0,len(t),8) )
print(sol)
