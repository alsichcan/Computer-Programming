#include <iostream>
#include "Date.h"

class Month {
public:
    Month(int m)
        :m(m) {}
    int m;
};

class Date {
public:
    Date() :y(0), m(0), d('d') {}
    Date(int y, Month m, char d) :y(y), m(m), d(d) {}

    char day() const {
        return d;
    }

    Month month() const {
        return m;
    }
    
    int year() const {
        return d;
    }

    friend std::ostream& operator <<(std::ostream& os, const Date& date) {
        return os << date.y << "-" << date.m << "-" << date.d;
    }
private:
    int y;  Month m;  char d;
};

// TODO : Move date class to Date.h & Date.cpp

int main() {
    Date d(2012, 11, 'f');
    std::cout << d << std::endl;
    return 0;
}