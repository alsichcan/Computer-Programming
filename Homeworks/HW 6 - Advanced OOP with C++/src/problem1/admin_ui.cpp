#include "admin_ui.h"

AdminUI::AdminUI(ShoppingDB &db, std::ostream& os): UI(db, os) { }

void AdminUI::add_product(std::string name, int price) {
    if(price > 0){
        Product* pd = new Product(name, price);
        db.product_add(pd);
        os << "ADMIN_UI: " << name << " is added to the database." << std::endl;
    } else{
        os << "ADMIN_UI: Invalid price." << std::endl;
    }
}

void AdminUI::edit_product(std::string name, int price) {
    if(db.product_search(name) == nullptr){
        os << "ADMIN_UI: Invalid product name." << std::endl;
        return;
    }

    if(price > 0){
        db.product_edit(name, price);
        os << "ADMIN_UI: " << name << " is modified from the database." << std::endl;
    } else{
        os << "ADMIN_UI: Invalid price." << std::endl;
    }
}

void AdminUI::list_products() {
    std::vector<std::pair<std::string, int>> productList = db.product_list();
    os << "ADMIN_UI: Products: [";
    for(int i = 0; i < productList.size(); i++){
        os << "(" << productList[i].first << ", " << productList[i].second << ")";

        if(i != productList.size()-1)
            os << ", ";
    }
    os << "]" << std::endl;
}
