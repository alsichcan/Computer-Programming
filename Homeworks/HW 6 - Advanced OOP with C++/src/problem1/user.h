#ifndef PROBLEM1_USER_H
#define PROBLEM1_USER_H

#include <string>
#include <vector>
#include <cmath>
#include <set>
#include "product.h"

struct ProductComp{
    bool operator()(const Product* p1, const Product* p2) const{
        return p1->name < p2->name;
    }
};

class User {
public:
    User(std::string name, std::string password);
    const std::string name;
    std::vector<Product*> cart;
    std::vector<Product*> purchaseHistory;
    std::set<Product*, ProductComp> purchaseList;

    virtual std::string getName();
    virtual bool isPremium();
    bool checkPassword(std::string password);
    virtual int buy(Product*);
    virtual int buyCart();
    virtual int price(int);
    void addCart(Product*);
    std::vector<std::pair<std::string, int>> listCart();
    virtual std::vector<std::pair<std::string, int>> listRecommend(std::vector<Product*>*);
    virtual std::set<Product*, ProductComp> getPurchaseList();
    virtual Product* getLatestPurchase();

private:
    std::string password;
};

class NormalUser : public User {
public:
    NormalUser(std::string name, std::string password);
};

class PremiumUser : public User {
public:
    PremiumUser(std::string name, std::string password);
    virtual std::string getName();
    virtual bool isPremium();
    virtual int buy(Product*);
    virtual int buyCart();
    virtual int price(int);

    virtual std::vector<std::pair<std::string, int>> listRecommend(std::vector<Product*>*);
    virtual std::set<Product*, ProductComp> getPurchaseList();
    virtual Product* getLatestPurchase();
};

#endif //PROBLEM1_USER_H
