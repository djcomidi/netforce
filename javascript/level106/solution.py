def unescape(s):
    res = ""
    for part in s.split('%'):
        if len(part) < 2:
            res += part
        else:
            code, t = part[:2], part[2:]
            res += chr(int(code, 16)) + t
    return res


def koh(s):
    un, L = "", len(s)
    oh = int(round(L/2.0))
    for i in range(oh):
        a, b = s[i], s[i+oh]
        un += a + b
    M = un[:L]
    M = M.replace("`", "'")
    M = M.replace("@@", "\\")
    M = M.replace("qg", unescape("%0D%0A"))
    return M

gy72 = 6928
with open('data_fw.txt', 'r') as f:
    fw = f.read().strip()
with open('data_s.txt', 'r') as f:
    s = f.read().strip()
print("SECOND SCRIPT:")
uns = unescape(s)
print(uns)

decoded = koh(fw)
print("\nDECODED FIRST SCRIPT:")
print(decoded)
print("\nPASSWORD LINE:")
lines = decoded.replace("><", ">\n<").split('\n')
for line in lines:
    if line.find("password") != -1:
        print(line)
