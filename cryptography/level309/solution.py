#!/usr/bin/env python3

# the solution was posted on the forum:
# https://net-force.nl/forum/list_messages/965/From_the_front_to_the_back_solution/0/#p9185
# below is an implementation of that solution

msg = "qpb asc qcqgaoq bad 1 jao a maeag aa"
msg = "".join( msg.split(' ') )
chars = []
for i in range(0,len(msg),2):
    tA, tB = [ord(c) for c in msg[i:i+2]]
    cT = chr((tA & 0b11110000) + (tB & 0b1111))
    chars.append(cT)
sol = "".join(chars)
print(sol)
