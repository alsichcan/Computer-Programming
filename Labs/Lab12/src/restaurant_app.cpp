#include <algorithm>
#include <iomanip>
#include <iostream>
#include "restaurant_app.h"

void RestaurantApp::rate(string target, int rate) {
    shared_ptr<vector<int>> ratings = find_restaurant(target);
    if (!ratings) {
        vector<int> rating;
        rating.push_back(rate);
        restaurants.insert(std::pair<string, shared_ptr<vector<int>>>(target,
                                                                      std::make_shared<vector<int>>(rating)));
        return;
    }

    ratings->push_back(rate);
    std::sort(ratings->begin(), ratings->end());
}


void RestaurantApp::list() {
    //for (std::pair<string, shared_ptr<vector<int>>> item : restaurants)
    for (auto item : restaurants) {
        std::cout << item.first << " ";
    }
    std::cout << std::endl;
}


void RestaurantApp::show(string target) {
    auto ratings = find_restaurant(target);
    if (!ratings) {
        std::cout << target << " does not exist" << std::endl;
        return;
    }

    for (int rate : *ratings) {
        std::cout << rate << " ";
    }
    std::cout << std::endl;
}


void RestaurantApp::ave(string target) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;
    if (ratings->size() == 0) {
        std::cout << "0" << std::endl;
        return;
    }

    double average = 0;
    for (int rate : *ratings) {
        average += rate;
    }
    average /= double(ratings->size());
    std::cout << std::fixed << std::setprecision(2) << average << std::endl;
}


void RestaurantApp::med(string target) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;
    if (ratings->size() == 0) {
        std::cout << "0" << std::endl;
        return;
    }

    double median = 0;
    int len = ratings->size();
    if (len%2 == 0) {
        median = ((*ratings)[len/2] + (*ratings)[len/2-1])/2;
    }
    else {
        median = (*ratings)[len/2];
    }
    std::cout << std::fixed << std::setprecision(2) << median << std::endl;
}


void RestaurantApp::min(string target) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;
    if (ratings->size() == 0) {
        std::cout << "0" << std::endl;
        return;
    }

    int min = *std::min_element(ratings->begin(), ratings->end());
    //int min = (*ratings)[0];
    std::cout << min << std::endl;
}


void RestaurantApp::max(string target) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;
    if (ratings->size() == 0) {
        std::cout << "0" << std::endl;
        return;
    }

    int max = *std::max_element(ratings->begin(), ratings->end());
    std::cout << max << std::endl;
}


void RestaurantApp::del(string target, int rate) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;

    auto it = std::remove(ratings->begin(), ratings->end(), rate);
    ratings->erase(it, ratings->end());
}


void RestaurantApp::cheat(string target, int rate) {
    auto ratings = find_restaurant(target);
    if (!ratings) return;

    auto it = std::lower_bound(ratings->begin(), ratings->end(), rate);
    ratings->erase(ratings->begin(), it);
}


shared_ptr<vector<int>> RestaurantApp::find_restaurant(string target) {
    auto it = restaurants.find(target);
    if (it == restaurants.end()) {
        return nullptr;
    }
    return restaurants[target];
}