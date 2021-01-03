//
// Created by Park on 2020-12-18.
//

#ifndef PROBLEM2_POST_H
#define PROBLEM2_POST_H

#include <iostream>
#include <string>
#include "dateTime.h"

class Post{
public:
    Post(std::string, std::string);
    Post(int, std::string, std::string, std::string);
    std::string getSummary();
    friend std::ostream& operator<<(std::ostream&, const Post&);

    int getId();
    void setId(int);
    std::string getDate();
    const DateTime getCurrentDataTime();
    void setDateTime(DateTime);
    std::string getTitle();
    std::string getContent();
    void setKeywordOccurrence(int);
    int getKeywordOccurrence();


private:
    int id;
    const static int ID_NOT_INITIATED = -1;
    DateTime dateTime;
    std::string title;
    std::string content;
    int keywordOccurrence = 0;
};

#endif //PROBLEM2_POST_H
