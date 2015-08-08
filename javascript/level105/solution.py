import subprocess
import os

with open('escaped_data.txt', 'r') as f:
    s = f.read().strip()

parts = s.split('%')
source = ""
for part in parts:
    if len(part) < 2:
        continue
    code, t = part[:2], part[2:]
    source += chr(int(code, 16))
    source += t
start = source.index(".Encode") + 9
end = source.index("</script")
with open('encoded.js', 'w') as f:
    f.write(source[start:end])
subprocess.call(["perl", "JScript.decode-1.0.pl", "encoded.js", "decoded.js"])
subprocess.call(["cat", "decoded.js"])
cryptpass = "VDkPWd0lakHPl"
pass2 = "".join(cryptpass[i] for i in [10, 8, 7, 7, 6])
addr = "solution.php?passwd="
addr = addr[:addr.index('?')+1] + "blabla=" + pass2
print(addr)
os.unlink("encoded.js")
os.unlink("decoded.js")
