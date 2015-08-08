def unescape(s):
    uns, i = "", 0
    while i < len(s):
        if s[i] == '%':
            uns += chr(int(s[i+1:i+3], 16))
            i += 3
        else:
            uns += s[i]
            i += 1
    return uns

with open('data_d.txt', 'r') as f:
    d = f.read().strip().encode().decode('unicode_escape')

with open('data_msga.txt', 'r') as f:
    msgA = f.read().strip()

msgB = ''.join(chr(int(msgA[i:i+2], 16)) for i in range(1, len(msgA), 3))
msgC = ''.join(chr(int(msgB[i:i+2], 16)) for i in range(1, len(msgB), 3))
print(msgC)
print("======== BEGIN OF s ============")

s = ""
i = 0
while i < len(d):
    a = ord(d[i])
    if a == 1:
        a = 9
    if a == 2:
        a = 10
    if a == 3:
        a = 13
    if a == 4:
        a = 34
    if 14 <= a <= 31:
        off = len(s)
        i += 1
        temp = ord(d[i])
        temp -= 36
        i += 1
        temp += 90 * (ord(d[i]) - 35)
        off -= temp
        off -= 1
        lp = off + a - 14 + 4
        s += s[off:lp]
    else:
        if a >= 41:
            a -= 1
        s += chr(a)
    i += 1

print(s)
print("======== END OF s ============")
print("the important part is: pd==unescape(\"%25%30%30%25%32%35%25%33%32\");")
pd = unescape("%25%30%30%25%32%35%25%33%32")
print("pd == ", pd)
print("that password opens a new window with a login form, relevant part:")
print("======== BEGIN OF passwordok ============")
t_start = s.index("write(unescape(") + 16
t_end = s.index('")', t_start)
pwdok_escaped = s[t_start:t_end]
pwdok = unescape(pwdok_escaped)
# pwdok = pwdok.replace("><", ">\n<")
print(pwdok)
print("======== END OF passwordok ============")
print("sha1(username) == 1d31d94f30d40df7951505d1034e1e923d02ec49")
print("sha1(password) == 2d7a34c9ef8efa2cfdf4b89175f7edec1cd0ddda")
print("google is your friend")
