def calc_m(abc):
    a, b, c = abc
    mA = (not a) & b & c
    mB = a & (not b) & c
    mC = a & b & (not c)
    mD = a & b & c
    return (mA | mB | mC | mD)

bits = "1101110000011100100100111011000110000011011111100"
bits += "0000101110111001110110001100000110101101111100001"
bits += "1010100110111000001010100111111111000101110001010"
truths = [c == "1" for c in bits]

results = []
for i in range(0, len(truths), 3):
    m = calc_m(truths[i:i+3])
    results.append("1" if m else "0")
result = "".join(results)

chars = []
for i in range(0, len(result), 7):
    t = int(result[i:i+7], 2)
    chars.append(chr(t))
msg = "".join(chars)
print(msg)
