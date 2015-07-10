#include "String.h"

#include <string.h>
#include <iostream>
using namespace std;

String::String(const char* other) {
    chars = new char[strlen(other) + 1];
    strcpy(chars, other);
}

String::String(const String& other) {
    chars = new char[strlen(other.chars) + 1];
    strcpy(chars, other.chars);
}

String& String::operator=(const String& other) {
    delete chars;
    chars = new char[strlen(other.chars) + 1];
    strcpy(chars, other.chars);
    return *this;
}

String::~String() {
    delete chars;
}

void String::print() {
    cout << chars << endl;
}

char String::getChar(int pos) {
    return chars[pos-1];
}

void String::reverse() { //abcd = dcba............
    char c;
    int left = 0, right = strlen(chars) - 1;
    while (left < right) {
        c = chars[left];
        chars[left] = chars[right];
        chars[right] = c;
        left++, right--;
    }
}

void String::replace(char cOld, char cNew) {
    for (int i = 0 ; i < strlen(chars); i++) {
        if (chars[i] == cOld) {
            chars[i] = cNew;
        }
    }
}

int String::length() {
    return strlen(chars);
}

String operator+(String sLeft, String sRight) {
    String temp;
    delete temp.chars;
    int len = strlen(sLeft.chars) + strlen(sRight.chars) + 1;
    temp.chars = new char[len];
    strcpy(temp.chars, sLeft.chars);
    strcat(temp.chars, sRight.chars);
    return temp;
}

int& String::operator%(const String& other) {
    areEqual = 0;
    int y , x, teller ;
    int z = strlen (other.chars);
    y = strlen(chars);
    if (z == y) {
        areEqual = 1;
        for (teller = 0; teller < z; teller++) {
            if (other.chars[teller] != chars[teller]) {
                areEqual = 0;
            }
        }
    }
    return areEqual;
}
