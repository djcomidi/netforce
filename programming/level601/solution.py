x, y, pans = 1, 1, 1
while x < 525:
    ans = x * y + pans + 3
    print(ans, x, y, pans)
    x, y, pans = x+1, y+1, ans
ans = x * y + pans + 3
print(ans, x, y, pans)
print(ans)
