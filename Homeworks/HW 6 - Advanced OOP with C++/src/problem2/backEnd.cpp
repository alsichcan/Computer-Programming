//
// Created by Park on 2020-12-18.
//

#include "backEnd.h"


BackEnd::BackEnd() {
    initNumOfPosts();
}

int BackEnd::numOfPosts = 0;

std::vector<std::string> SplitPath(std::string path, char sep){
    std::vector<std::string> out;
    std::stringstream stream(path);
    std::string temp;

    while(std::getline(stream, temp, sep))
        out.push_back(temp);

    return out;
}

void BackEnd::initNumOfPosts(){
    int maxNum = -1;
    for(auto& file: std::filesystem::recursive_directory_iterator(SERVER_STORAGE_DIR)){
        std::string filePath = file.path().string();
        std::replace(filePath.begin(), filePath.end(), '\\', '/');
        std::vector<std::string> pathArgs = SplitPath(filePath, '/');
        if(pathArgs.size() == 4){
            std::vector<std::string> fileArgs = SplitPath(pathArgs[pathArgs.size()-1], '.');
            if(std::stoi(fileArgs[0]) > maxNum)
                maxNum = std::stoi(fileArgs[0]);
        }
    }
    BackEnd::numOfPosts = maxNum;
}


bool BackEnd::auth(std::string id, std::string passwd){
    std::string path = std::string(SERVER_STORAGE_DIR).append(id).append("/password.txt");
    std::string userPasswd;

    std::ifstream ifs(path);
    std::getline(ifs, userPasswd);
    ifs.close();
    if(userPasswd == passwd) return true;
    else return false;
}


void BackEnd::post(std::string id, Post& post){
    post.setId(++numOfPosts);
    std::string path = std::string(SERVER_STORAGE_DIR).append(id).append("/post/").append(std::to_string(post.getId())).append(".txt");
    std::ofstream newPost(path);

    newPost << post.getDate() << std::endl;
    newPost << post.getTitle() << std::endl;
    newPost << std::endl;
    newPost << post.getContent();
    newPost.close();
}

std::priority_queue<Post, std::vector<Post>, cmp> BackEnd::recommend(std::string id){
    std::priority_queue<Post, std::vector<Post>, cmp> postList;
    std::priority_queue<Post, std::vector<Post>, cmp> recommendList;

    std::string userPath = std::string(SERVER_STORAGE_DIR).append(id).append("/friend.txt");
    std::ifstream ifs(userPath);

    std::string friendUser;
    std::vector<std::string> friendList;

    while(ifs.peek() != EOF){
        std::getline(ifs, friendUser);
        friendList.push_back(friendUser);
    }

    ifs.close();

    for(int i = 0; i < friendList.size(); i++){
        std::string friendPath = std::string(SERVER_STORAGE_DIR).append(friendList[i]).append("/post/");

        for(auto& file: std::filesystem::recursive_directory_iterator(friendPath)){
            int id;
            std::string createdDateTime;
            std::string title;
            std::string content = "";

            std::string filePath = file.path().string();
            std::replace(filePath.begin(), filePath.end(), '\\', '/');
            std::vector<std::string> pathArgs = SplitPath(filePath, '/');

            // Read ID
            if(pathArgs.size() == 4) {
                std::vector<std::string> fileArgs = SplitPath(pathArgs[pathArgs.size() - 1], '.');
                id = std::stoi(fileArgs[0]);
            }

            std::ifstream ifs(filePath);
            std::string line;

            // Read Created DateTime
            std::getline(ifs, createdDateTime);

            // Read Title
            std::getline(ifs, title);

            // Read Blank Line
            std::getline(ifs, line);

            // Read Content
            std::getline(ifs, line);
            content += line;
            while(ifs.peek() != EOF){
                std::getline(ifs, line);
                content += ("\n" + line);
            }

            ifs.close();

            Post post(id, createdDateTime, title, content);
            postList.push(post);
        }
    }

    for(int i = 0; i < 10; i++){
        if(!postList.empty()){
            recommendList.push(postList.top());
            postList.pop();
        }
    }

    return recommendList;
}

int countKeyword(std::unordered_set<std::string> keywords, std::string line){
    int count = 0;

    std::vector<std::string> line_vector;
    std::stringstream ss(line);
    std::string temp;
    while(std::getline(ss, temp, ' ')){
        if(temp.empty()) continue;
        line_vector.push_back(temp);
    }

    for(const auto& keyword : keywords){
        count += std::count(line_vector.begin(), line_vector.end(), keyword);
    }

    return count;
}


std::priority_queue<Post, std::vector<Post>, cmp> BackEnd::search(std::unordered_set<std::string> keywords){
    std::priority_queue<Post, std::vector<Post>, cmp> postList;
    std::priority_queue<Post, std::vector<Post>, cmp> searchList;

    for(auto& file: std::filesystem::recursive_directory_iterator(SERVER_STORAGE_DIR)){
        // Check if we are dealing with post.txt
        std::string filePath = file.path().string();
        std::replace(filePath.begin(), filePath.end(), '\\', '/');
        std::vector<std::string> pathArgs = SplitPath(filePath, '/');

        if(pathArgs.size() != 4) continue;

        int id;
        std::string createdDateTime;
        std::string title;
        std::string content = "";
        int keywordOccurrence = 0;

        if(pathArgs.size() == 4) {
            std::vector<std::string> fileArgs = SplitPath(pathArgs[pathArgs.size() - 1], '.');
            id = std::stoi(fileArgs[0]);
        }

        std::ifstream ifs(filePath);
        std::string line;

        // Read Created DateTime
        std::getline(ifs, createdDateTime);

        // Read Title
        std::getline(ifs, title);
        keywordOccurrence += countKeyword(keywords, title);

        // Read Blank Line
        std::getline(ifs, line);

        // Read Content
        std::getline(ifs, line);
        keywordOccurrence += countKeyword(keywords, line);
        content += line;

        while(ifs.peek() != EOF){
            std::getline(ifs, line);
            keywordOccurrence += countKeyword(keywords, line);
            content += ("\n" + line);
        }

        ifs.close();

        if(keywordOccurrence == 0) continue;

        Post post(id, createdDateTime, title, content);
        post.setKeywordOccurrence(keywordOccurrence);
        postList.push(post);
    }

    for(int i = 0; i < 10; i++){
        if(!postList.empty()){
            searchList.push(postList.top());
            postList.pop();
        }
    }

    return searchList;
}
