#!/usr/bin/env python3

encmsg = "ogrk hg tl'n srbszizrzig aiphgu mg ycahzylluf vllu fg ahcoogneg pceinc is ngzdluag"

t = encmsg
pairs = ["ol","eg","ru","tz","fd","ac","yw"]
for pair in pairs:
    trans = str.maketrans(pair,pair[::-1])
    t = t.translate(trans)
print(t)
