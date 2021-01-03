#include <vector>
#include "client_ui.h"
#include "product.h"
#include "user.h"

ClientUI::ClientUI(ShoppingDB &db, std::ostream& os) : UI(db, os), current_user() { }

ClientUI::~ClientUI() {}

void ClientUI::signup(std::string username, std::string password, bool premium) {
    User* user;
    if(premium) user = new PremiumUser(username, password);
    else user = new NormalUser(username, password);

    db.user_signup(user);
    os << "CLIENT_UI: " << username << " is signed up." << std::endl;
}

void ClientUI::login(std::string username, std::string password) {
    if(current_user != nullptr){
        os << "CLIENT_UI: Please logout first." << std::endl;
        return;
    }

    if((current_user = db.user_login(username, password)) != nullptr){
        os << "CLIENT_UI: " << current_user->name << " is logged in." << std::endl;
    } else{
        os << "CLIENT_UI: Invalid username or password." << std::endl;
    }
}

void ClientUI::logout() {
    if(current_user != nullptr){
        os << "CLIENT_UI: " << current_user->name << " is logged out." << std::endl;
        current_user = nullptr;
    } else{
        os << "CLIENT_UI: There is no logged-in user." << std::endl;
    }
}

void ClientUI::add_to_cart(std::string product_name) {
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }

    Product* product;
    if((product = db.product_search(product_name)) != nullptr){
        current_user->addCart(db.product_search(product_name));
        os << "CLIENT_UI: " << product->name << " is added to the cart." << std::endl;
    } else{
        os << "CLIENT_UI: Invalid product name." << std::endl;
    }
}

void ClientUI::list_cart_products() {
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }

    std::vector<std::pair<std::string, int>> productList = current_user->listCart();
    os << "CLIENT_UI: Cart: [";
    for(int i = 0; i < productList.size(); i++){
        os << "(" << productList[i].first << ", " << current_user->price(productList[i].second) << ")";

        if(i != productList.size()-1)
            os << ", ";
    }
    os << "]" << std::endl;

}

void ClientUI::buy_all_in_cart() {
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }

    os << "CLIENT_UI: Cart purchase completed. Total price: " << current_user->buyCart() << "." << std::endl;

}

void ClientUI::buy(std::string product_name) {
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }

    Product* product;
    if((product = db.product_search(product_name)) != nullptr){
        os << "CLIENT_UI: Purchase completed. Price: " << current_user->buy(product) << "." << std::endl;
    } else{
        os << "CLIENT_UI: Invalid product name." << std::endl;
    }
}

void ClientUI::recommend_products() {
    if(current_user == nullptr){
        os << "CLIENT_UI: Please login first." << std::endl;
        return;
    }


    std::vector<std::pair<std::string, int>> recommendList;
    std::vector<Product*>* similarList = nullptr;
    if(current_user->isPremium())
        similarList = db.user_getRecommend(current_user->getName());

    recommendList = current_user->listRecommend(similarList);

    os << "CLIENT_UI: Recommended products: [";
    for(int i = 0; i < recommendList.size(); i++){
        os << "(" << recommendList[i].first << ", " << current_user->price(recommendList[i].second) << ")";

        if(i != recommendList.size()-1)
            os << ", ";
    }
    os << "]" << std::endl;

    delete[] similarList;
}
