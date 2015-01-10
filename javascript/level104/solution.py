#!/usr/bin/env python

s="%3Cform%3E%0D%0A%3Cp%3EUsername%3A%20%3Cbr%3E%0D%0A%20%20%3Cinput%20type%3D%22text%22%20name%3D%22text2%22%3E%0D%0A%3C/p%3E%0D%0A%3Cp%3EPassword%3A%20%3Cbr%3E%0D%0A%3Cinput%20type%3D%22password%22%20name%3D%22text1%22%3E%3Cbr%3E%0D%0A%20%20%3Cinput%20type%3D%22button%22%20value%3D%22Check%21%22%20name%3D%22Submit%22%20onclick%3Djavascript%3Avalidate%28text2.value%2C%22user%22%2Ctext1.value%2C%22member%22%29%20%3E%0D%0A%3C/p%3E%0D%0A%0D%0A%3C/form%3E%0D%0A%3Cscript%20language%20%3D%20%22javascript%22%3E%0D%0A%0D%0Afunction%20validate%28text1%2Ctext2%2Ctext3%2Ctext4%29%0D%0A%7B%0D%0A%20if%20%28text1%3D%3Dtext2%20%26%26%20text3%3D%3Dtext4%29%0D%0A%20alert%28%22Well%20done%20use%20this%20password%20on%20the%20challenge%20page%22%29%3B%0D%0A%20else%20%0D%0A%20%7B%0D%0A%20%20alert%28%22Wrong...%21%22%29%3B%0D%0A%20%7D%0D%0A%7D%0D%0A%0D%0A%3C/script%3E"

parts = s.split('%')
sol = ""
for part in parts:
	if len(part) < 2: continue
	code, t = part[:2], part[2:]
	sol += chr( int( code, 16 ) )
	sol += t
print sol
