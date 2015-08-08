num = 13155187
d = 13417
divs = []
while len(divs) < 5001:
    q, r = divmod(num, d)
    divs.append(q)
    num = 10 * r
sol = "".join(str(i) for i in divs[-6:])
print(sol)
