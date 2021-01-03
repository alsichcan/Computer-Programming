#include "user.h"

User::User(std::string name, std::string password): name(name), password(password) {

}

std::string User::getName(){
    return name;
}


bool User::isPremium() {
    return false;
}

bool User::checkPassword(std::string password){
    if(this->password == password) return true;
    else return false;
}

int User::buy(Product* product){
    purchaseHistory.push_back(product);
    purchaseList.insert(product);
    return price(product->price);
}

int User::buyCart(){
    int totalPrice = 0;
    for(int i = 0; i < cart.size(); i++){
        purchaseHistory.push_back(cart[i]);
        purchaseList.insert(cart[i]);
        totalPrice += cart[i]->price;
    }

    std::vector<Product*> emptyCart;
    cart = emptyCart;

    return price(totalPrice);
}


int User::price(int price){
    return price;
}

void User::addCart(Product* product){
    cart.push_back(product);
}

std::vector<std::pair<std::string, int>> User::listCart(){
    std::vector<std::pair<std::string, int>> cartList;

    for(int i = 0; i < cart.size(); i++){
        std::pair<std::string, int> product;
        product.first = cart[i]->name;
        product.second = cart[i]->price;
        cartList.push_back(product);
    }
    return cartList;
}

std::vector<std::pair<std::string, int>> User::listRecommend(std::vector<Product*>* similarList) {
    std::vector<std::pair<std::string, int>> recommendList;

    for(int i = purchaseHistory.size()-1; i >= 0; i--){
        if(recommendList.size() == 3) break;

        if(recommendList.size() > 1){
            if(purchaseHistory[i]->name == recommendList[1].first)
                continue;
        }

        if(recommendList.size() > 0){
            if(purchaseHistory[i]->name == recommendList[0].first)
                continue;
        }

        std::pair<std::string, int> product;
        product.first = purchaseHistory[i]->name;
        product.second = purchaseHistory[i]->price;
        recommendList.push_back(product);
    }
    return recommendList;
}

std::set<Product*, ProductComp> User::getPurchaseList(){
    return purchaseList;
}

Product* User::getLatestPurchase(){
    if(purchaseHistory.size() == 0)
        return nullptr;
    else
        return purchaseHistory.at(purchaseHistory.size()-1);
}


NormalUser::NormalUser(std::string name, std::string password): User(name, password) {}

PremiumUser::PremiumUser(std::string name, std::string password): User(name, password) {}

std::string PremiumUser::getName(){
    return name;
}


bool PremiumUser::isPremium() {
    return true;
}

int PremiumUser::price(int price){
    return std::round(price * 0.9);
}

int PremiumUser::buy(Product* product){
    purchaseHistory.push_back(product);
    purchaseList.insert(product);
    return price(product->price);
}

int PremiumUser::buyCart(){
    int totalPrice = 0;
    for(int i = 0; i < cart.size(); i++){
        purchaseHistory.push_back(cart[i]);
        purchaseList.insert(cart[i]);
        totalPrice += cart[i]->price;
    }

    std::vector<Product*> emptyCart;
    cart = emptyCart;

    return price(totalPrice);
}

std::vector<std::pair<std::string, int>> PremiumUser::listRecommend(std::vector<Product*>* similarList) {
    std::vector<std::pair<std::string, int>> recommendList;

    for(int i = purchaseList.size(); i >= 0; i--){
        for(int j = 0; j < similarList[i].size(); j++){
            if(recommendList.size() == 3) break;

            if(similarList[i][j] == nullptr)
                continue;

            if(recommendList.size() > 1){
                if(similarList[i][j]->name == recommendList[1].first)
                    continue;
            }

            if(recommendList.size() > 0){
                if(similarList[i][j]->name == recommendList[0].first)
                    continue;
            }
            std::pair<std::string, int> product;
            product.first = similarList[i][j]->name;
            product.second = similarList[i][j]->price;
            recommendList.push_back(product);
        }
    }
    return recommendList;
}

std::set<Product*, ProductComp> PremiumUser::getPurchaseList() {
    return purchaseList;
}

Product* PremiumUser::getLatestPurchase() {
    if(purchaseHistory.size() == 0)
        return nullptr;
    else
        return purchaseHistory.at(purchaseHistory.size()-1);
}