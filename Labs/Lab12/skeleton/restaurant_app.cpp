#include <iostream>
#include <iomanip>
#include <algorithm>
#include "restaurant_app.h"


void RestaurantApp::rate(string target, int rate) {
    
}


void RestaurantApp::list() {
    
}


void RestaurantApp::show(string target) {
    
}


void RestaurantApp::ave(string target) {
    
}


void RestaurantApp::med(string target) {
    
}


void RestaurantApp::min(string target) {
    
}


void RestaurantApp::max(string target) {
    
}


void RestaurantApp::del(string target, int rate) {

}


void RestaurantApp::cheat(string target, int rate) {

}


shared_ptr<vector<int>> RestaurantApp::find_restaurant(string target) {
    auto it = restaurants.find(target);
    if (it == restaurants.end()) {
        return nullptr;
    }
    return restaurants[target];
}
