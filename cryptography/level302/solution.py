encmsg = "ogrk hg tl'n srbszizrzig aiphgu mg ycahzylluf vllu fg ahcoogneg "
encmsg += "pceinc is ngzdluag"

t = encmsg
pairs = ["ol", "eg", "ru", "tz", "fd", "ac", "yw", "mj"]
for pair in pairs:
    trans = str.maketrans(pair, pair[::-1])
    t = t.translate(trans)
print(t)
