#ifndef PROBLEM1_USER_H
#define PROBLEM1_USER_H

#include <string>
#include <vector>
#include "product.h"

class User {
public:
    User(std::string name, std::string password);
    const std::string name;
private:
    std::string password;
};

class NormalUser : public User {

};

class PremiumUser : public User {

};

#endif //PROBLEM1_USER_H
