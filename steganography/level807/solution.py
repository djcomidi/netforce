#!/usr/bin/env python3

data = b"2C7CBi*66iC6C2BBB3i6B36i<;][XJ\D>AQJ>Q7[\C;|Q[M]>917,.E.|G]B>S.2X3YXYXXY./YY.2Y3XY32.X.Yl//lmml.63mm2*l6.+7lml622336*26/"
bits = [b&1 for b in data]
bitstr = "".join(str(b) for b in bits)
bytes = [ bitstr[i:i+8] for i in range(0,len(bitstr),8) ]
chars = [ chr(int(b,2)) for b in bytes ]
message = "".join(chars)
print(message)
