//
// Created by Park on 2020-12-18.
//

#ifndef PROBLEM2_BACKEND_H
#define PROBLEM2_BACKEND_H

#include <iostream>
#include <fstream>
#include <filesystem>
#include <sstream>
#include <string>
#include <algorithm>
#include <queue>
#include <vector>
#include <unordered_set>
#include "config.h"
#include "post.h"
#include "dateTime.h"

struct cmp{
    bool operator()(Post p1, Post p2){
        if(p1.getKeywordOccurrence() == p2.getKeywordOccurrence())
            return p1.getDate() < p2.getDate();

        return p1.getKeywordOccurrence() < p2.getKeywordOccurrence();
    }
};


class BackEnd{
public:
    BackEnd();
    static int numOfPosts;
    void initNumOfPosts();
    bool auth(std::string, std::string);
    void post(std::string, Post&);
    std::priority_queue<Post, std::vector<Post>, cmp> recommend(std::string);
    std::priority_queue<Post, std::vector<Post>, cmp> search(std::unordered_set<std::string>);



private:
};


#endif //PROBLEM2_BACKEND_H
