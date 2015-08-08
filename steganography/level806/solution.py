from urllib import request
import subprocess
from zipfile import ZipFile
import os

FLAG = "flag.zip"
URL = "https://net-force.nl/challenge/level806/flag.ico"
bytes = request.urlopen(URL).read()
# extracting the extra data (a zip file)
pk = bytes.index(b'PK')
with open(FLAG, 'wb') as flag:
    flag.write(bytes[pk:])
# in the forum it states "keep it simple" :-)
output = subprocess.check_output(["fcrackzip", "-u", "-l", "1", FLAG]).strip()
password = output.split()[-1]
message = "Password = {0}".format(password.decode())
print(message)
with ZipFile(FLAG) as zf:
    txt = zf.read('file.txt', pwd=password).decode()
print(txt)
os.unlink(FLAG)
