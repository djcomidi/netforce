#!/usr/bin/env python3

# uitleg in het nederlands:
# https://nl.wikipedia.org/wiki/Europese_artikelnummering
# explanation in english:
# https://en.wikipedia.org/wiki/International_Article_Number_%28EAN%29#Binary_encoding_of_data_digits_into_EAN-13_barcode

STRUCTURE = [
    "LLLLLLRRRRRR",
    "LLGLGGRRRRRR",
    "LLGGLGRRRRRR",
    "LLGGGLRRRRRR",
    "LGLLGGRRRRRR",
    "LGGLLGRRRRRR",
    "LGGGLLRRRRRR",
    "LGLGLGRRRRRR",
    "LGLGGLRRRRRR",
    "LGGLGLRRRRRR",
]

CODING = {
    "0001101": ('L',0), "0100111": ('G',0), "1110010": ('R',0),
    "0011001": ('L',1), "0110011": ('G',1), "1100110": ('R',1),
    "0010011": ('L',2), "0011011": ('G',2), "1101100": ('R',2),
    "0111101": ('L',3), "0100001": ('G',3), "1000010": ('R',3),
    "0100011": ('L',4), "0011101": ('G',4), "1011100": ('R',4),
    "0110001": ('L',5), "0111001": ('G',5), "1001110": ('R',5),
    "0101111": ('L',6), "0000101": ('G',6), "1010000": ('R',6),
    "0111011": ('L',7), "0010001": ('G',7), "1000100": ('R',7),
    "0110111": ('L',8), "0001001": ('G',8), "1001000": ('R',8),
    "0001011": ('L',9), "0010111": ('G',9), "1110100": ('R',9)
}

# I used mtPaint to get the binary codes (1=black, 0=white)
#                 1         2         3         4         5         6         7         8         9
#       01234567890123456789012345678901234567890123456789012345678901234567890123456789012345678901234
#       xxx111111122222223333333444444455555556666666xxxxx111111122222223333333444444455555556666666xxx
bars = "10101110110110011000110100100110110011001101101010110011011011001001110111010010100001110010101"
numbers = []
codes = []
for i in [3,10,17,24,31,38,50,57,64,71,78,85]:
    code, num = CODING[bars[i:i+7]]
    numbers.append(num)
    codes.append(code)
code = ''.join(codes)
numbers = [STRUCTURE.index(code)] + numbers
eancode = ''.join(str(num) for num in numbers)
msg = "EAN code = {0}".format(eancode)
print(msg)
