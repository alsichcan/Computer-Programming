//
// Created by Park on 2020-12-18.
//

#ifndef PROBLEM2_DATETIME_H
#define PROBLEM2_DATETIME_H

#include <stdio.h>
#include <string>
#include <time.h>
#include <vector>

class DateTime{
public:
    DateTime(std::string);
    friend std::ostream& operator<<(std::ostream&, const DateTime&);
    static std::string now();
    std::string getDateTime();

private:
    std::string formatted;
};


#endif //PROBLEM2_DATETIME_H
