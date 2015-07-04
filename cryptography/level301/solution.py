#!/usr/bin/env python3

import base64
from string import ascii_lowercase as LCASES
from string import ascii_uppercase as UCASES

def shift(msg,n):
    s = ""
    for c in str(msg):
        t = c
        if c in LCASES:
            t = chr( (((ord(c) - ord('a')) + 26 + n) % 26 ) + ord('a') )
        elif c in UCASES:
            t = chr( (((ord(c) - ord('A')) + 26 + n) % 26 ) + ord('A') )
        s += t
    return s

secmsg = "UGRhIGx3b29za256IGJrbiBwZGEgeWR3aGhhamNhIGx3Y2EgZW86IHludWxwaw=="
cesmsg = base64.b64decode(secmsg)
print(cesmsg)
plainmsg = shift(cesmsg,4)
print(plainmsg)
