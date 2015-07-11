#!/usr/bin/env python3

lines = [
    "The tiny thing, the password, sure it is that what you seek?",
    "You need it fast? Need is one thing, for what you need the password anyway?",
    "The challenge? Look at this page, if the password is what you want.",
    "Again I say, Look!"
]
text = " ".join(lines)
for c in ",?.!":
    text = text.replace(c,"")
words = text.split()
message = " ".join(words[::4])
print(message)
