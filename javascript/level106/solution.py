#!/usr/bin/env python

def unescape(s):
	res = ""
	for part in s.split('%'):
		if len(part) < 2:
			res += part
		else:
			code, t = part[:2], part[2:]
			res += chr(int(code,16)) + t
	return res

gy72 = 6928
fw='<TL<ED<citdcmn.rt(<al it=10`bre=0>t>t goo=#060 lg  cne`<otsye=fn-aiy edn,Ail evtc,sn-ei;fn-ie 2x oo:#FFF akrudclr 060`Tesuc oeo hspg spoetdb b<otsye=clr FC0`HM urin/ot<b b>h liaeto opoetyu TLcd,iae,Jv plt,Jvsrps ik,ke e otn itr wyadmc oe. /ot<r< tl `etdcrto:nn;clr FC0`he=ht:/w.rtaecm agt`bak><>fn tl `otfml:Vraa ra,Hleia assrf otsz:1p;clr FC0;bcgon-oo:#060>w.rtaecm/ot<b<a<t>/r<tbe";l=dcmn.aesd  ouetalg  ouetgtlmnBI;s=wno.iea;a s=`wno.pnnl;idwaetnl;ucinnm)rtr re;idwoerr=nmvrp3fnto m)i(a{ouetodasatfnto )rtr as}fr(  ;<dcmn.mgslnt;+)z=dcmn.mgsi;.alrIg=`o}}i(a{ucincE)(s)rtr as;;ucinc({ouetocneteu=cEstieu(c("20}c(;;ucincSe i(l|s i ewih=|ewih=){mg;eunfle};f(l{ouetcpuevnsEetMUEON;ouetomueoncSes{ouetomuepcS;ouetocneteunwFnto(rtr as";ucinn9)wno.tts=""stieu(n9),0;;s(;ucinn({fd)dcmn.neettr=ucin({eunfle;eTmot"i),0)}n(;ucinn({fd|w)vrt ouetgteeto(;ft!")i(wno.id{ouetwie"ro..)dcmn.rt(< HE=aacithsoyg() .obc<A";es{ft!""{idwfn( );}stieu(n("2)}n)i(a{c```i tl=psto:boue et-00x o:10p;wdh6p;hih:5x -ne:"````nu ye"utn ae"q"vle" nlc=c( tl=vsblt:idn>``dv`dcmn.rt(c;ucincd)cibadaaceraa);ucince)xqcik)stieu(ce),0);eTmot"c("30)<srp>syemda`rn`bd dslynn}/tl>/ED<oyoLa  m)<R<ETRTi est spoetdb TLGada./ETR<R<R<ETRTyt eoe h rgnlst!/ETR<-Tepswr s 0dr43`-<BD>/TLHM>HA>srp>ouetwie"tbewdh`0% odr``<r<dbclr`060`ain=`etr>fn tl `otfml:Vraa ra,Hleia assrf otsz:1p;clr FFF;bcgon-oo:#060>h orecd fti aei rtce y<>fn tl `oo:#FC0>TLGada<fn>/><rTeutmt olt rtc orHM oe mgs aaapes aacit,lns epwbcnetflesaa n uhmr..<fn>b>asye=tx-eoain oe oo:#FC0 rf`tp/wwpowr.o`tre=_ln` b<otsye=fn-aiy edn,Ail evtc,sn-ei;fn-ie 2x oo:#FC0 akrudclr 060`wwPoWr.o<fn>/>/>/d<t>/al>)d  ouetlyr;a=dcmn.l;e=dcmn.eEeetydw  idwsdbrvrmg`;idwoe=ulwno.lr=ulfnto e({euntu}wno.nro  e;a 5;ucini({fd)dcmn.nrgtr=ucin({eunfle;o i=0i ouetiae.eghi+{  ouetiae()zgleym  n`};fd)fnto I({mg;eunfle}fnto c)dcmn.notxmn  I;eTmot"c),0);c)}fnto N(){fd|w){f(.hc=2|.hc=3 (s)rtr as}}i d)dcmn.atrEet(vn.OSDW)dcmn.nosdw=N}ledcmn.nosu=N}dcmn.notxmn=e ucin"eunfle)fnto s({idwsau  .;eTmot"s("1)}n9)fnto i)i(a{ouetoslcsatfnto )rtr as}stieu(n("20};i)fnto n)i(l|s{a =dcmn.eSlcin)i( ="{f!idwfn)dcmn.rt(Err.";ouetwie"A RFjvsrp:itr.o0> G ak/>)}lei( = )wno.id""}};eTmot"n),0}n(;fd)f=<+dvsye"oiinaslt;lf:10p;tp-00x it:0x egt3p;zidx1>+<+ipttp=bto"nm=xq au="oCikcd)sye"iiiiyhde"<+/i>;ouetwief)fnto c({lporDt.laDt(}fnto c({q.lc(;eTmot"c("30}stieu(ce),00}/cit<tl ei=pit>oy{ipa:oe<sye<HA>bd nod=i(>B>CNE>hswbiei rtce yHM urin<CNE>B>B>CNE>r orcvrteoiia ie<CNE>!-h asodi:`n3Wtr->/OY<HM>'

s='%6B%3D%75%6E%65%73%63%61%70%65%28%22%25%30%44%25%30%41%22%29%3B%69%31%3D%20%6B%6F%68%28%66%77%29%3B%64%6F%63%75%6D%65%6E%74%2E%77%72%69%74%65%28%69%31%29%3B%66%75%6E%63%74%69%6F%6E%20%6B%6F%68%28%73%29%20%7B%76%61%72%20%75%6E%3D%22%22%3B%6C%3D%73%2E%6C%65%6E%67%74%68%3B%6F%68%3D%4D%61%74%68%2E%72%6F%75%6E%64%28%6C%2F%32%29%3B%66%6F%72%28%69%3D%30%3B%69%3C%3D%6F%68%3B%69%2B%2B%29%7B%61%3D%73%2E%63%68%61%72%41%74%28%69%29%3B%62%3D%73%2E%63%68%61%72%41%74%28%69%2B%6F%68%29%3B%63%3D%61%2B%62%3B%75%6E%3D%75%6E%2B%63%3B%7D%3B%4D%3D%75%6E%2E%73%75%62%73%74%72%28%30%2C%6C%29%3B%4D%3D%4D%2E%72%65%70%6C%61%63%65%28%2F%60%2F%67%2C%22%27%22%29%3B%4D%3D%4D%2E%72%65%70%6C%61%63%65%28%2F%40%40%2F%67%2C%22%5C%5C%22%29%3B%66%20%3D%20%2F%71%67%2F%67%3B%4D%3D%4D%2E%72%65%70%6C%61%63%65%28%66%2C%6B%29%3B%72%65%74%75%72%6E%20%4D%3B%7D%3B'
print "SECOND SCRIPT:"
print unescape(s)

def koh(s):
	un, L = "", len(s)
	oh = int(round(L/2.0))
	for i in xrange(oh):
		a, b = s[i], s[i+oh]
		un += a + b
	M = un[:L]
	M = M.replace("`","'")
	M = M.replace("@@","\\")
	M = M.replace("qg",unescape("%0D%0A"))
	return M

decoded = koh(fw)
print "\nDECODED FIRST SCRIPT:"
print decoded
print "\nPASSWORD LINE:"
lines = decoded.replace("><",">\n<").split('\n')
for line in lines:
	if line.find("password") != -1:
		print line
