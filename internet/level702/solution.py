#!/usr/bin/env python3

from itertools import product
from zipfile import ZipFile

LCASES = list("abcdefghijklmnopqrstuvwxyz")

def gen_passwds():
    for chars in product(LCASES, repeat=6):
        yield ''.join(chars).encode()    

passwords = gen_passwds()
solution = None
with ZipFile('file.zip') as zf:
    while not solution:
        passwd = next(passwords)
        try:
            fd = zf.open('file.txt',pwd=passwd)
            txt = fd.read()
            if len(txt) > 0:
                solution = [passwd, txt]
        except:
            pass
    
print(solution[1].encode())


