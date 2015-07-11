#!/usr/bin/env python3

from PIL import Image
from urllib import request
from io import BytesIO

url = "https://net-force.nl/challenge/level804/pass.gif"
imgbytes = BytesIO(request.urlopen(url).read())
img = Image.open(imgbytes)
img = img.convert("RGB")
pix = img.load()
W, H = img.size

message = "".join( chr(pix[i,0][2]) for i in range(0,W,31) )
print(message)

