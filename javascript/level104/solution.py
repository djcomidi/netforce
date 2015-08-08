with open('escaped_data.txt', 'r') as f:
    s = f.read().strip()

parts = s.split('%')
sol = ""
for part in parts:
    if len(part) < 2:
        continue
    code, t = part[:2], part[2:]
    sol += chr(int(code, 16))
    sol += t
print(sol)
