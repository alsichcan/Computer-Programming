#ifndef PROBLEM1_SHOPPING_DB_H
#define PROBLEM1_SHOPPING_DB_H

#include <string>
#include <vector>
#include "user.h"
#include "product.h"

class ShoppingDB {
public:
    ShoppingDB();
    ~ShoppingDB();

    void product_add(Product*);
    void product_edit(std::string, int);
    std::vector<std::pair<std::string, int>> product_list();
    Product* product_search(std::string);

    void user_signup(User*);
    User* user_login(std::string, std::string);
    std::vector<Product*>* user_getRecommend(std::string username);

private:
    std::vector<User*> users;
    std::vector<Product*> products;
};

#endif //PROBLEM1_SHOPPING_DB_H
