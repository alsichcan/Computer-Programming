//
// Created by Park on 2020-12-18.
//

#ifndef PROBLEM2_FRONTEND_H
#define PROBLEM2_FRONTEND_H

#include <iostream>
#include <sstream>
#include "backEnd.h"
#include "post.h"

class FrontEnd{
public:
    FrontEnd(std::istream&, std::ostream&, BackEnd&);
    bool auth(std::string, std::string);
    void post();
    void recommend();
    void search(std::string);

private:
    std::istream& is;
    std::ostream& os;
    BackEnd& backEnd;
    std::string user;
};

#endif //PROBLEM2_FRONTEND_H
