#!/usr/bin/env python3

import urllib.request

URL = "https://net-force.nl/challenge/level602/prog2.php"
html = urllib.request.urlopen(URL).read()
num = int( str(html).split("'")[1], 10 )
print(html)
print(num)
answer = ( num * 3 + 2 ) - 250
NEWURL = "{0}?solution={1}".format(URL,answer)
html = urllib.request.urlopen(NEWURL).read()
print(str(html))

