#include <iostream>

#ifndef DATE_H
#define DATE_H

class Month {
public:
    Month(int m);
    int m;
};

class Date {
public:
    Date();
    Date(int y, Month m, char d);
    char day() const;
    Month month() const;
    int year() const;
    friend std::ostream& operator <<(std::ostream& os, const Date& date);
private:
    int y;  Month m;  char d;
};

#endif // !