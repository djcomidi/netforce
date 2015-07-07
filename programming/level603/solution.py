#!/usr/bin/env python3

# Ref: http://oeis.org/A000389
# "For a team with n basketball players (n>=5),
# this sequence is the number of possible starting lineups of 5 players,
# without regard to the positions (center, forward, guard) of the players."

t = 0
for n in range(1,501):
    x = n + 4
    t += ( (x*(x-1)*(x-2)*(x-3)*(x-4)) // 120 )
print("500 cups = {0} possibilities".format(t))
