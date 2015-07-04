#!/usr/bin/env python3


ENC   = "vychxqxyrrrqhpcxxdqirwxqnujnvlvxdgackjrwxyksuglqtw"
PLAIN = "thesecretpasswordforthechallengepageisthekeyweused"
ABC="abcdefghijklmnopqrstuvwxyz"

print(ENC)
print(PLAIN)
# a basic vigenere cipher
t = "".join( ABC[(ord(p)-ord(e)+26)%26] for e, p in zip(PLAIN,ENC) )
print(t)
