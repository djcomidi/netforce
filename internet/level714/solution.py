#!/usr/bin/env python3

# !!! the broken creditcard numbers change every refresh
# input here the 3 given broken creditcard numbers:
DATA = [
    "4??9-?45?-??7?-?7??",
    "4??5-?18?-??1?-?6??",
    "55?3-?88?-??6?-?7??"
]

def is_valid_cardnr(cardnr):
    odds, evens = cardnr[::2], cardnr[1::2]
    odds = [(2*n)%9 for n in odds]
    return (sum(odds)+sum(evens)) % 10 == 0

for data in DATA:
    cardnr = [ int(c) for c in data.replace("?","0") if c != "-"]
    while not is_valid_cardnr(cardnr):
        cardnr[-1] += 1
    type = "MasterCard" if cardnr[0] == 5 else "Visa"
    cardnr = "".join(str(n) for n in cardnr)
    print("{0} = {1}".format(cardnr,type))

