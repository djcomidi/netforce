text = "The tiny thing, the password, sure it is that what you seek? "
text += "You need it fast? "
text += "Need is one thing, for what you need the password anyway? "
text += "The challenge? Look at this page, if the password is what you want. "
text += "Again I say, Look!"
for c in ",?.!":
    text = text.replace(c, "")
words = text.split()
message = " ".join(words[::4])
print(message)
