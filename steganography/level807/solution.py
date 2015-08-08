with open('data.txt', 'rb') as f:
    data = f.read().strip()
bits = [b & 1 for b in data]
bitstr = "".join(str(b) for b in bits)
bytes = [bitstr[i:i+8] for i in range(0, len(bitstr), 8)]
chars = [chr(int(b, 2)) for b in bytes]
message = "".join(chars)
print(message)
