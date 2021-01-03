//
// Created by Park on 2020-12-18.
//

#include "dateTime.h"

DateTime::DateTime(std::string dtString) {
    formatted = dtString;
}

std::string DateTime::now(){
    time_t now = time(0);
    struct tm tstruct;
    char buf[80];
    tstruct = *localtime(&now);
    strftime(buf, sizeof(buf), "%Y/%m/%d %X", &tstruct);

    return buf;
}

std::ostream& operator<<(std::ostream& os, const DateTime& dt){
    return os << dt.formatted;
}

std::string DateTime::getDateTime() {
    return formatted;
}