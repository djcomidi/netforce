For the applet as shown on the challenge page:
$ ant run.applet

For a desktop version of that applet:
$ ant run.desktop

For a program that makes a bruteforce attempt to crack the hashes:
$ ant run.solver
(runtime is over 4 hours...)

Another (probably better/faster) way to crack the hashes is to use John The Ripper:
1) get the data:
$ ant run.solver.john
OR
$ java -jar dist/LoginChallenge-solver.jar --john
2) put the output in a plaintext file (eg. hashes.txt)
3) install john if you haven't already
4) execute john
$ john --format=raw-md5 hashes.txt
5) be patient :) (on my machine it takes around 4 hours)

