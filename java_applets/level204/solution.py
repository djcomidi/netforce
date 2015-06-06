#!/usr/bin/env python

import string
import random

TARGET = 448
ALFABET = string.ascii_lowercase

def find_sols(s="",val=0):
    if val > TARGET: return
    if val == 448:
        yield s
        return
    if s == "":
        for c in ALFABET:
            for sol in find_sols(s+c,val+ord(c)):
                yield sol
    else:
        i = ALFABET.index(s[-1])
        for c in ALFABET[i:]:
            for sol in find_sols(s+c,val+ord(c)):
                yield sol

print "this is one of the many possible solutions:"
start=""
print random.choice(list(find_sols()))
