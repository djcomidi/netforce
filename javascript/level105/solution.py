#!/usr/bin/env python

import subprocess

s ="%3Ccenter%3E%0D%0A%0D%0A%3Cp%3EWell%20this%20won%27t%20be%20so%20hard,%20just%20enter%20the%20right%20password!%3C/p%3E%0D%0A%0D%0A%3Cscript%20language%3D%22JScript.Encode%22%3E%23@%7E%5ElAIAAA%3D%3D@%23@%26@%21Z%20O@%23@%26dJzCMeUYCDDP3U1WN%7FMeC@%23@%26i@%23@%26d6E%09mOkGU%2CYn/DnDv%23%60@%23@%26d%5CC.%2CwC/k%7E%7BP%5BKm%21%3A+%09YcWWM%3A%20wm/dA9R%5CmsE%7Fi@%23@%267-lMP1DzwO2m/dP%7B%7EJjfVKqN%21sC0CKVrI@%23@%2677lMPl9NM%7E%27%2CBdW%5EEOrKxRa4wQwlkdAN%7BBp@%23@%26d-CMPsW1CYb+%7Ex%2CVW%5ECDkGxc4M+Wp@%23@%26d%5CmD%2CGEDPxPEBI@%23@%26d@%23@%267%5CmDPaCd/yP%7BP%5EDz2DwC/k%20/%214dOMkxLc8%21SPyMl_q*_1DXaYaC/kRdE%28/O.bxov+ev%20_ybSP2_+%23Qm.zaY2lkdRkE8dDDkULv%26Q*%20qBP0*_1DXaYaC/kRdE%28/O.vG%7E8b_1DXaO2lk/c/%3B4dOM%60%7F%7E8bialdd%7BVW%5ECDknRk%3B%28/OM%60%5EWmmYbnRbx%5B+XrWcEgB*QF*il9%5B.%27mN9D%20/%3B8kY.k%09L%60Z%7E%7EC9ND%20r%09Nn6%7DWvB_E%233F%233B%28sl%28VC%27Ei@%23@%26i0WMck%7B%21ib@%212lk/cSnxIr3_b%09@%23@%26dikWcal/d%201tCDzOvkb%2C%27%7BPwm/k+R1tCDzYcr*%23%09@%23@%26didNK%5E%3B%3A%7FxDRADrO%7F%602lkdR1tC.zY%60rb*i@%23@%26i7%29@%23@%26i8@%23@%26d%5EW1CYbWUP%7BPC%5B9D_aC/ki@%23@%267N@%23@%26O%20@*@%23@%26ob4AAA%3D%3D%5E%23%7E@%3C/script%3E%0D%0A%0D%0A%3Cform%20name%3D%22form%22%3E%0D%0APassword%3A%20%3Cinput%20type%3D%22password%22%20name%3D%22passwd%22%3E%20%3Cinput%20type%3D%22button%22%20value%3D%22OK%22%20onClick%3D%22tester%28%29%22%3E%0D%0A%3C/form%3E%0D%0A%0D%0A%3C/center%3E"

parts = s.split('%')
source = ""
for part in parts:
	if len(part) < 2: continue
	code, t = part[:2], part[2:]
	source += chr( int( code, 16 ) )
	source += t
start = source.index(".Encode") + 9
end = source.index("</script")
with open('encoded.js','wb') as f:
	f.write(source[start:end])
subprocess.call(["perl", "JScript.decode-1.0.pl", "encoded.js", "decoded.js"])
#subprocess.call(["cat", "decoded.js"])
cryptpass = "VDkPWd0lakHPl"
pass2 = cryptpass[10] + cryptpass[8] + cryptpass[7] + cryptpass[7] + cryptpass[6]
addr = "solution.php?passwd="
addr = addr[:addr.index('?')+1] + "blabla=" + pass2
print addr
subprocess.call(["rm", "-f", "encoded.js", "decoded.js"])
