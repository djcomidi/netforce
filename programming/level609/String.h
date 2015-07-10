#ifndef __STRING_H__
#define __STRING_H__

class String {
private:
    char *chars;
    int areEqual;
public:
    String(const char* other = "");
    String(const String& other);
    String & operator=(const String& other);
    ~String();

    void print();
    char getChar(int pos);
    void reverse();
    void replace(char cOld, char cNew);
    int length();
    friend String operator+(String sLeft, String sRight);
    int& operator%(const String& other);
};

#endif // __STRING_H__
