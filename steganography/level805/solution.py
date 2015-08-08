from urllib import request
import re

url = "https://net-force.nl/challenge/level805/monster.jpg"
with request.urlopen(url) as img:
    bytes = img.read()
longest = b''
for m in re.findall(b'[01]+', bytes):
    if len(m) > len(longest):
        longest = m
data = longest.decode()
print(data)
msg = "".join(chr(int(data[i:i+8], 2)) for i in range(0, len(data), 8))
print(msg)
