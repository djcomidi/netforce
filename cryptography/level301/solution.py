import base64
from string import ascii_lowercase as LCASES
from string import ascii_uppercase as UCASES


def rotate(char, n):
    offset = ord('a') if char in LCASES else ord('A')
    return chr((((ord(char) - offset) + 26 + n) % 26) + offset)


def shift(msg, n):
    chars = []
    for c in str(msg):
        t = c
        if c in LCASES or c in UCASES:
            t = rotate(c, n)
        chars.append(t)
    return "".join(chars)

secmsg = "UGRhIGx3b29za256IGJrbiBwZGEgeWR3aGhhamNhIGx3Y2EgZW86IHludWxwaw=="
cesmsg = base64.b64decode(secmsg).decode()
print(cesmsg)
plainmsg = shift(cesmsg, 4)
print(plainmsg)
