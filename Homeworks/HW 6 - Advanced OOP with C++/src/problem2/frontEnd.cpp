//
// Created by Park on 2020-12-18.
//

#include "frontEnd.h"

FrontEnd::FrontEnd(std::istream& is, std::ostream& os, BackEnd &backEnd) : is(is), os(os), backEnd(backEnd) {}

bool FrontEnd::auth(std::string id, std::string passwd){
    if(backEnd.auth(id, passwd)){
        user = id;
        return true;
    }
    return false;
}

void FrontEnd::post(){
    std::string title;
    std::string line;
    std::string content;

    os << "-----------------------------------" << std::endl;
    os << "New Post" << std::endl;
    os << "* Title=";
    std::getline(is, title);

    os << "* Content" << std::endl;

    os << ">";
    std::getline(is, line);
    content += line;

    while(!line.empty()){
        os << ">";
        std::getline(is, line);
        if(line.empty()) break;
        content += ("\n" + line);
    }

    Post post(title, content);

    backEnd.post(user, post);
}

void FrontEnd::recommend(){
    std::priority_queue<Post, std::vector<Post>, cmp> recommendList = backEnd.recommend(user);

    for(int i = 0; i < 10; i++){
        if(!recommendList.empty()){
            os << recommendList.top() << std::endl;
            recommendList.pop();
        }
    }
}

void FrontEnd::search(std::string command){
    std::unordered_set<std::string> keywords;

    std::string keywordLine = command.substr(6, command.size());

    std::stringstream ss(keywordLine);
    std::string temp;
    while(std::getline(ss, temp, ' ')){
        if(temp.empty()) continue;
        keywords.insert(temp);
    }
    os << "-----------------------------------" << std::endl;

    std::priority_queue<Post, std::vector<Post>, cmp> searchList = backEnd.search(keywords);
    for(int i = 0; i < 10; i++){
        if(!searchList.empty()){
            Post post = searchList.top();
            os << post.getSummary() << std::endl;
            searchList.pop();
        }
    }
}
