#include <vector>
#include "client_ui.h"
#include "product.h"
#include "user.h"

ClientUI::ClientUI(ShoppingDB &db, std::ostream& os) : UI(db, os), current_user() { }

ClientUI::~ClientUI() {
    delete current_user;
}

void ClientUI::signup(std::string username, std::string password, bool premium) {
    // TODO: For problem 1-2

}

void ClientUI::login(std::string username, std::string password) {
    // TODO: For problem 1-2

}

void ClientUI::logout() {
    // TODO: For problem 1-2

}

void ClientUI::add_to_cart(std::string product_name) {
    // TODO: For problem 1-2

}

void ClientUI::list_cart_products() {
    // TODO: For problem 1-2.

}

void ClientUI::buy_all_in_cart() {
    // TODO: For problem 1-2

}

void ClientUI::buy(std::string product_name) {
    // TODO: For problem 1-2

}

void ClientUI::recommend_products() {
    // TODO: For problem 1-3.

}
