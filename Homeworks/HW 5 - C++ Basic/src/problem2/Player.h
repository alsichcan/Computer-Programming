#ifndef PROBLEM2_PLAYER_H
#define PROBLEM2_PLAYER_H

#include <iostream>
#include <string>
#include <set>
#include <vector>
#include <tuple>
#include <limits>


class Player{
private:
    int id;
    std::string playerType;
    std::multiset<int> hands;

    int result;
    void computeResult();
    int Maximize_Gain();
    int Minimize_Loss();
    int Minimize_Regret();


public:
    Player();
    Player(std::string, std::multiset<int>);
    Player(std::string, std::multiset<int>, int);
    ~Player();

    std::vector<int>* resultTable;

    int getId() const;
    std::string getPlayerType() const;
    int getResult();
    std::multiset<int> getHands() const;
    int getNumber(int) const;
    void clearResultTable();
    void updateHand(int, int);
    int chooseNumber();
};


#endif //PROBLEM2_PLAYER_H
