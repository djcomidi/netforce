#include <iostream>
using namespace std;

#include "String.h"

void multiReplace(String& s, char aOld, char aNew, char bOld, char bNew);
void showChars(int maxIndex);
String sB, sZ;

int main() {
    String sPassword,sLogin;
    String sA, sC, sD, sE, sX, sY;
    sB = "THIS IS REALY FUNNY SING OR DIE FURBY KILLER";
    cout << "Challenge made by...";
    showChars(3);
    sA = "Het wachtwoord voor deze challenge is : ";
    sA.print();
    sX = "DMCOFFX";
    sY = "SGYFRRM";
    sX.reverse();
    multiReplace(sX, 'X' ,'A' ,'F' ,'B');
    multiReplace(sY, 'R' ,'E' ,'Y' ,'L');
    sZ = sX + sY;
    cout << "............................." << endl;
    if (sZ%sA) {
        sPassword = "Smart!";
        showChars(35);
    }
    sLogin.reverse();
    showChars(sZ.length());
    return 0;
}

void multiReplace(String& s, char aOld, char aNew, char bOld, char bNew) {
    s.replace(aOld, aNew);
    s.replace(bOld, bNew);
}

void showChars(int maxIndex) {
    if (maxIndex <= 5) {
        cout << sB.getChar(4);
        cout << sB.getChar(26);
        cout << sB.getChar(15);
        cout << sB.getChar(1);
        cout << sB.getChar(4);
        cout << sB.getChar(17);
        cout << sB.getChar(11);
        cout << sB.getChar(39);
        cout << sB.getChar(10);
    } else {
        for (int i = 0; i <= maxIndex; i += 2) {
            cout << sZ.getChar(i);
        }
    }
    cout << endl;
}
