#include "shopping_db.h"

ShoppingDB::ShoppingDB() {

}

ShoppingDB::~ShoppingDB(){
    for(int i = 0; i < users.size(); i++){
        delete users[i];
    }

    for(int j = 0; j < products.size(); j++){
        delete products[j];
    }
}

void ShoppingDB::product_add(Product* product){
    products.push_back(product);
}

void ShoppingDB::product_edit(std::string name, int price) {
    for(int i = 0; i < products.size(); i++){
        if(products[i]->name == name)
            products[i]->price = price;
    }
}

std::vector<std::pair<std::string, int>> ShoppingDB::product_list() {
    std::vector<std::pair<std::string, int>> productList;
    for(int i = 0; i < products.size(); i++){
        std::pair<std::string, int> product;
        product.first = products[i]->name;
        product.second = products[i]->price;
        productList.push_back(product);
    }
    return productList;
}

Product* ShoppingDB::product_search(std::string name){
    for(int i = 0; i < products.size(); i++){
        if(products[i]->name == name)
            return products[i];
    }
    return nullptr;
}

void ShoppingDB::user_signup(User* user){
    users.push_back(user);
}


User* ShoppingDB::user_login(std::string username, std::string password){
    for(int i = 0; i < users.size(); i++){
        if(users[i]->name == username && users[i]->checkPassword(password))
            return users[i];
    }
    return nullptr;
}

std::vector<Product*>* ShoppingDB::user_getRecommend(std::string username){
    User* requestUser;
    std::string name;
    for(int i = 0; i < users.size(); i++){
        name = users[i]->getName();
        if(users[i]->getName() == username){
            requestUser = users[i];
        }
    }

    int size = requestUser->getPurchaseList().size();
    std::vector<Product*>* recommendList = new std::vector<Product*>[size+1];
    for(int i = 0; i < size+1; i++){
        recommendList[i] = std::vector<Product*>();
    }

    for(int i = 0; i < users.size(); i++){
        if(users[i]->getName() == username) continue;

        int similarity = 0;
        std::set<Product*>::iterator iter;
        for(iter = requestUser->purchaseList.begin(); iter != requestUser->purchaseList.end(); iter++){
            if(users[i]->getPurchaseList().count(*iter))
                similarity++;
        }

        recommendList[similarity].push_back(users[i]->getLatestPurchase());
    }

    return recommendList;
}

