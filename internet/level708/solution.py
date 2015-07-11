#!/usr/bin/env python3

def calc_m(abc):
    a, b, c = abc
    mA = (not a) & b & c
    mB = a & (not b) & c
    mC = a & b & (not c)
    mD = a & b & c
    return (mA | mB | mC | mD)

bits = "110111000001110010010011101100011000001101111110000001011101110011101100011000001101011011111000011010100110111000001010100111111111000101110001010"
truths = [ c == "1" for c in bits ]
print(bits)

results = []
for i in range(0,len(truths),3):
    m = calc_m(truths[i:i+3])
    results.append("1" if m else "0")
result = "".join(results)
print(result)

chars = []
for i in range(0,len(result),7):
    t = int(result[i:i+7],2)
    chars.append(chr(t))
msg = "".join(chars)
print(msg)
