#!/usr/bin/env python3

given = "B8 9B 8C E0 89 9F 9D 98 8C 89 91 91 8E 9C E0 97 8D E0 BD 91 AB 92 8C"
codes = [ int(t,16) for t in given.split(" ") ]
compls = [ (0xFF ^ t)+1 for t in codes ]
chars = [ chr(t) for t in compls ]
msg = "".join(chars)
print(msg)
