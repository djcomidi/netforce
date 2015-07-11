#!/usr/bin/env python3

import urllib.parse
import urllib.request

def decrypt(ds):
    return ''.join(chr(ord(c)-1) for c in ds[::-1])
page = decrypt("qiq/hojttpmqPfe")
print(page)

score = 40000
sc = str(score)[::-1]
scoreVal = ''.join("ABCDEFGHIJ"[int(c)] for c in sc)
print(scoreVal)

url = "https://net-force.nl/challenge/level710/" + page
postvalues = {'score': scoreVal}
postdata = urllib.parse.urlencode(postvalues).encode('utf-8')
html = urllib.request.urlopen(url, data=postdata).read().decode()
print(html)
