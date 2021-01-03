#include <iostream>
#include <ctime>
#include "player.h"

void round(Player&, Player&);

int main() {
    std::srand(std::time(nullptr));

    Player a, b;
    a.add_monster(fireMon);
    a.add_monster(fireMon);
    a.add_monster(waterMon);
    b.add_monster(waterMon);
    b.add_monster(grassMon);
    b.add_monster(grassMon);

    for (int i = 1;; i++) {
        round(a, b);
        std::cout << "Round " << i << ": " << a.get_total_hp()
        << " " << b.get_total_hp() << std::endl;
        if (b.get_num_monsters() == 0) {
            std::cout << "Player a won the game!" << std::endl;
            break;
        } else if (a.get_num_monsters() == 0) {
            std::cout << "Player b won the game!" << std::endl;
            break;
        }
    }
    return 0;
}

void round(Player& a, Player& b) {
    Monster* aMon = a.select_monster();
    Monster* bMon = b.select_monster();

    aMon->attack(bMon);
    bMon->attack(aMon);

    if(aMon->get_hp() <= 0) a.delete_monster(aMon);
    if(bMon->get_hp() <= 0) b.delete_monster(bMon);
}
