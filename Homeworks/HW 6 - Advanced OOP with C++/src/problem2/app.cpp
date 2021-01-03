#include <iostream>
#include <string>
#include "app.h"
#include "frontEnd.h"
#include "backEnd.h"

bool query(FrontEnd&, const std::string&);

App::App(std::istream& is, std::ostream& os): is(is), os(os) {}

void App::run() {
    BackEnd backEnd;
    FrontEnd frontEnd(is, os, backEnd);

    std::string command;
    std::string id, passwd;
    os << "------ Authentication ------" << std::endl;
    os << "id=";
    getline(is, id);

    os << "passwd=";
    getline(is, passwd);

    bool authenticated = frontEnd.auth(id, passwd);

    if (authenticated) {
        do {
            os << "-----------------------------------" << std::endl;
            os << id << "@sns.com" << std::endl;
            os << "post : Post contents" << std::endl;
            os << "recommend : recommend interesting posts" << std::endl;
            os << "search <keyword> : List post entries whose contents contain <keyword>" << std::endl;
            os << "exit : Terminate this program" << std::endl;
            os << "-----------------------------------" << std::endl;
            os << "Command=";
            std::getline(is, command); // TODO : Error Handling?
        } while (query(frontEnd, command));
    }
    else{
        os << "Failed Authentication." << std::endl;
    }
}


bool query(FrontEnd& frontEnd, const std::string& command){
    std::string instruction;
    for(auto x : command){
        if(x == ' ') break;
        else instruction += x;
    }

    if(instruction == "exit") return false;
    else if(instruction == "post")
        frontEnd.post();
    else if(instruction == "search")
        frontEnd.search(command);
    else if(instruction == "recommend")
        frontEnd.recommend();

    return true;
}


