#include <iostream>
#include <string>
#include <utility>
#include <set>
#include <vector>
#include <tuple>
#include <cmath>
#include "Player.h"

/* =======START OF PRIME-RELATED HELPERS======= */
/*
 * The code snippet below AS A WHOLE does the primality
 * test and integer factorization. Feel free to move the
 * code to somewhere more appropriate to get your codes
 * more structured.
 *
 * You don't have to understand the implementation of it.
 * But if you're curious, refer to the sieve of Eratosthenes
 *
 * If you want to just use it, use the following 2 functions.
 *
 * 1) bool is_prime(int num):
 *     * `num` should satisfy 1 <= num <= 999999
 *     - returns true if `num` is a prime number
 *     - returns false otherwise (1 is not a prime number)
 *
 * 2) std::multiset<int> factorize(int num):
 *     * `num` should satisfy 1 <= num <= 999999
 *     - returns the result of factorization of `num`
 *         ex ) num = 24 --> result = { 2, 2, 2, 3 }
 *     - if `num` is 1, it returns { 1 }
 */

const int PRIME_TEST_LIMIT = 999999;
int sieve_of_eratosthenes[PRIME_TEST_LIMIT + 1];
bool sieve_calculated = false;

void make_sieve() {
    sieve_of_eratosthenes[0] = -1;
    sieve_of_eratosthenes[1] = -1;
    for(int i=2; i<=PRIME_TEST_LIMIT; i++) {
        sieve_of_eratosthenes[i] = i;
    }
    for(int i=2; i*i<=PRIME_TEST_LIMIT; i++) {
        if(sieve_of_eratosthenes[i] == i) {
            for(int j=i*i; j<=PRIME_TEST_LIMIT; j+=i) {
                sieve_of_eratosthenes[j] = i;
            }
        }
    }
    sieve_calculated = true;
}

bool is_prime(int num) {
    if (!sieve_calculated) {
        make_sieve();
    }
    return sieve_of_eratosthenes[num] == num;
}

std::multiset<int> factorize(int num) {
    if (!sieve_calculated) {
        make_sieve();
    }
    std::multiset<int> result;
    while(num > 1) {
        result.insert(sieve_of_eratosthenes[num]);
        num /= sieve_of_eratosthenes[num];
    }
    if(result.empty()) {
        result.insert(1);
    }
    return result;
}

/* =======END OF PRIME-RELATED HELPERS======= */

/* =======START OF std::string LITERALS======= */
/* Use this code snippet if you want */

const std::string MAXIMIZE_GAIN = "Maximize-Gain";
const std::string MINIMIZE_LOSS = "Minimize-Loss";
const std::string MINIMIZE_REGRET = "Minimize-Regret";

/* =======END OF std::string LITERALS======= */


/* =======START OF TODOs======= */

std::pair<int, int> number_fight(int a, int b) {
    // Factorize two Integers, A and B
    std::multiset<int> FA = factorize(a);
    std::multiset<int> FB = factorize(b);

    // Make the list of distinct prime numbers that are in both FA and FB
    std::vector<int> FG;
    std::multiset<int>::iterator iter;
    for(iter = FA.begin(); iter != FA.end(); iter++){
        auto search = FB.find(*iter);
        if(search != FB.end()){
            FG.push_back(*iter);
            for(int num = 0; num < FA.count(*iter)-1; num++){
                iter++;
            }
        }
    }

    // Calculate the product of numbers in FG
    int G = 1;
    for(int i = 0; i < FG.size(); i++){
        G *= FG[i];
    }

    // Divide A and B by G, respectively
    int A = a/G;
    int B = b/G;

    return std::pair<int, int>(A, B);
}

std::pair<int, int> number_vs_number(int a, int b) {
    const int FIGHT = 0;
    const int FLIGHT = 1;

    //-----------------------------------------------------------------------------
    // Initialize table of the game
    std::pair<int, int>** gameTable = new std::pair<int, int>*[2];
    for(int i = 0; i < 2; i++)
        gameTable[i] = new std::pair<int, int>[2];
    //-----------------------------------------------------------------------------


    //-----------------------------------------------------------------------------
    // When A and B both chooses to fight
    gameTable[FIGHT][FIGHT] = number_fight(a,b);

    // When A and B both chooses to flight
    gameTable[FLIGHT][FLIGHT] = std::pair<int, int>(a, b);

    // When either A or B chooses to fight
    // Compute the damage of A and B
    std::pair<int, int> damagedAB = number_fight(a, b);
    int damageA = b - damagedAB.second;
    int damageB = a - damagedAB.first;
    int afterA;
    int afterB;

    // If A chooses to fight and B chooses to flight
    std::multiset<int> FB = factorize(b);
    if(FB.count(7)){
        afterA = a - floor(damageA/2);
        afterB = b - floor(damageA/2);
        if(afterA < 1) afterA = 1;
        if(afterB < 1) afterB = 1;
    } else{
        afterA = a;
        afterB = b - damageA;
    }
    gameTable[FIGHT][FLIGHT] = std::pair<int, int>(afterA, afterB);

    // If B chooses to fight and A chooses to flight
    std::multiset<int> FA = factorize(a);
    if(FA.count(7)){
        afterA = a - floor(damageB/2);
        afterB = b - floor(damageB/2);
        if(afterA < 1) afterA = 1;
        if(afterB < 1) afterB = 1;
    } else{
        afterA = a - damageB;
        afterB = b;
    }
    gameTable[FLIGHT][FIGHT] = std::pair<int, int>(afterA, afterB);
    //-----------------------------------------------------------------------------


    //-----------------------------------------------------------------------------
    // Decision making process of A
    int decA_final;
    int decA_Bfight;
    int decA_Bflight;
    // Assuming B will fight
    if(gameTable[FIGHT][FIGHT].first >= gameTable[FLIGHT][FIGHT].first){
        decA_Bfight = FIGHT;
    } else{
        decA_Bfight = FLIGHT;
    }
    // Assuming B will flight
    if(gameTable[FIGHT][FLIGHT].first >= gameTable[FLIGHT][FLIGHT].first){
        decA_Bflight = FIGHT;
    } else{
        decA_Bflight = FLIGHT;
    }
    // Choosing Final Decision of A
    if(decA_Bfight == decA_Bflight){
        decA_final = decA_Bfight;
    } else{
        if(b > a){
            decA_final = FIGHT;
        } else{
            decA_final = FLIGHT;
        }
    }

    // Decision making process of B
    int decB_final;
    int decB_Afight;
    int decB_Aflight;
    // Assuming A will fight
    if(gameTable[FIGHT][FIGHT].second >= gameTable[FIGHT][FLIGHT].second){
        decB_Afight = FIGHT;
    } else{
        decB_Afight = FLIGHT;
    }
    // Assuming A will flight
    if(gameTable[FLIGHT][FIGHT].second >= gameTable[FLIGHT][FLIGHT].second){
        decB_Aflight = FIGHT;
    } else{
        decB_Aflight = FLIGHT;
    }
    // Choosing Final Decision of B
    if(decB_Afight == decB_Aflight){
        decB_final = decB_Afight;
    } else{
        if(a > b){
            decB_final = FIGHT;
        } else{
            decB_final = FLIGHT;
        }
    }
    //-----------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    int A = gameTable[decA_final][decB_final].first;
    int B = gameTable[decA_final][decB_final].second;
    //-----------------------------------------------------------------------------

    //-----------------------------------------------------------------------------
    // Deleting gameTable
    for(int i = 0; i < 2; i++){
        if(gameTable[i])
            delete[] gameTable[i];
    }
    if(gameTable)
        delete[] gameTable;
    //-----------------------------------------------------------------------------

    return std::pair<int, int>(A, B);
}


void updateBattleTable(std::pair<int, int>** &battleTable, Player &A, Player &B){
    for(int optionA = 0; optionA < A.getHands().size(); optionA++){
        for(int optionB = 0; optionB < B.getHands().size(); optionB++){
            battleTable[optionA][optionB] = number_vs_number(A.getNumber(optionA), B.getNumber(optionB));
        }
    }
}

void updateResultTable(std::pair<int, int>** &battleTable, Player &A, Player &B){
    A.clearResultTable();
    B.clearResultTable();
    for(int optionA = 0; optionA < A.getHands().size(); optionA++){
        for(int optionB = 0; optionB < B.getHands().size(); optionB++){
            A.resultTable[optionA].push_back(battleTable[optionA][optionB].first - A.getNumber(optionA));
            B.resultTable[optionB].push_back(battleTable[optionA][optionB].second - B.getNumber(optionB));
        }
    }
}

std::pair<std::multiset<int>, std::multiset<int>> player_battle(
    std::string type_a, std::multiset<int> a, std::string type_b, std::multiset<int> b
) {
    // Initialize player and game
    Player A = Player(type_a, a);
    Player B = Player(type_b, b);

    std::pair<int, int>** battleTable;
    battleTable = new std::pair<int, int>*[A.getHands().size()];
    for(int i = 0; i < A.getHands().size(); i++)
        battleTable[i] = new std::pair<int, int>[B.getHands().size()];

    // Update Battle table of the game and Result table of each player
    updateBattleTable(battleTable, A, B);
    updateResultTable(battleTable, A, B);

    // Determine choice and Battle with the choices
    int choiceA = A.chooseNumber();
    int choiceB = B.chooseNumber();
    std::pair<int, int> battleResult = number_vs_number(choiceA, choiceB);

    // Update the hands of Player A and B by the result of the battle
    A.updateHand(choiceA, battleResult.first);
    B.updateHand(choiceB, battleResult.second);

    // Delete battleTable
    for(int i = 0; i < A.getHands().size(); i++) {
        if(battleTable[i])
            delete[] battleTable[i];
    }
    if(battleTable)
        delete[] battleTable;


    return std::pair<std::multiset<int>, std::multiset<int>>(A.getHands(), B.getHands());
}

std::pair<std::multiset<int>, std::multiset<int>> player_vs_player(
    std::string type_a, std::multiset<int> a, std::string type_b, std::multiset<int> b
) {
    // Initialize player and game
    Player A = Player(type_a, a);
    Player B = Player(type_b, b);

    std::pair<int, int>** battleTable;
    battleTable = new std::pair<int, int>*[A.getHands().size()];
    for(int i = 0; i < A.getHands().size(); i++)
        battleTable[i] = new std::pair<int, int>[B.getHands().size()];

    while(true) {
        // Update Battle table of the game and Result table of each player
        updateBattleTable(battleTable, A, B);
        updateResultTable(battleTable, A, B);

        // Determine choice and Battle with the choices
        int choiceA = A.chooseNumber();
        int choiceB = B.chooseNumber();
        std::pair<int, int> battleResult = number_vs_number(choiceA, choiceB);

        if(choiceA == battleResult.first && choiceB == battleResult.second){
            break;
        } else{
            // Update the hands of Player A and B by the result of the battle
            A.updateHand(choiceA, battleResult.first);
            B.updateHand(choiceB, battleResult.second);
        }
    }

    // Delete battleTable
    for(int i = 0; i < A.getHands().size(); i++) {
        if(battleTable[i])
            delete[] battleTable[i];
    }
    if(battleTable)
        delete[] battleTable;


    return std::pair<std::multiset<int>, std::multiset<int>>(A.getHands(), B.getHands());
}


int winnerOfGame(std::pair<std::multiset<int>, std::multiset<int>> handPair, std::pair<int, int> indexPair){
    int resultA = 0;
    std::multiset<int>::iterator iterA;
    for(iterA = handPair.first.begin(); iterA != handPair.first.end(); iterA++){
        resultA += *iterA;
    }

    int resultB = 0;
    std::multiset<int>::iterator iterB;
    for(iterB = handPair.second.begin(); iterB != handPair.second.end(); iterB++){
        resultB += *iterB;
    }

    if(resultA > resultB){
        return 0;
    } else if(resultA < resultB){
        return 1;
    } else{
        return indexPair.first < indexPair.second? 0 : 1;
    }
}


int tournament(std::vector<std::pair<std::string, std::multiset<int>>> players) {

    std::vector<Player*> tournamentPlayers;

    for(int index = 0; index < players.size(); index++){
        Player* player = new Player(players[index].first, players[index].second, index);
        tournamentPlayers.push_back(player);
    }

    while(tournamentPlayers.size() != 1){
        std::vector<Player*>::iterator iter;
        for(iter = tournamentPlayers.begin(); iter != tournamentPlayers.end(); iter++){
            Player* A = *iter;
            ++iter;
            if(iter == tournamentPlayers.end())
                break;
            Player* B = *iter;

            std::pair<std::multiset<int>, std::multiset<int>> handPair = player_vs_player((*A).getPlayerType(), (*A).getHands(), (*B).getPlayerType(), (*B).getHands());
            std::pair<int, int> indexPair((*A).getId(), (*B).getId());
            int winner = winnerOfGame(handPair, indexPair);

            if(winner == 0){ // Player A has won + Eliminate Player B
                delete *iter;
                tournamentPlayers.erase(iter);
                if(iter == tournamentPlayers.end()) break;
            } else{ // Player B has won + Eliminate Player A
                delete *(--iter);
                tournamentPlayers.erase(iter);
            }
        }
    }
    int winner = (*tournamentPlayers.at(0)).getId();

    delete tournamentPlayers[0];

    return winner;
}

int steady_winner(std::vector<std::pair<std::string, std::multiset<int>>> players) {
    std::vector<Player*> tournamentPlayers;

    for(int index = 0; index < players.size(); index++){
        Player* player = new Player(players[index].first, players[index].second, index);
        tournamentPlayers.push_back(player);
    }

    std::multiset<int> winnerIndex;

    for(int tournamentIdx = 0; tournamentIdx < tournamentPlayers.size(); tournamentIdx++) {
        std::vector<Player*> tournamentRound;
        for(int i = 0; i < tournamentPlayers.size(); i++){
            tournamentRound.push_back(tournamentPlayers[i]);
        }


        while (tournamentRound.size() != 1) {
            std::vector<Player *>::iterator iter;
            for (iter = tournamentRound.begin(); iter != tournamentRound.end(); iter++) {
                Player *A = *iter;
                ++iter;
                if (iter == tournamentRound.end())
                    break;
                Player *B = *iter;

                std::pair<std::multiset<int>, std::multiset<int>> handPair = player_vs_player((*A).getPlayerType(),
                                                                                              (*A).getHands(),
                                                                                              (*B).getPlayerType(),
                                                                                              (*B).getHands());
                std::pair<int, int> indexPair((*A).getId(), (*B).getId());
                int winner = winnerOfGame(handPair, indexPair);

                if (winner == 0) { // Player A has won + Eliminate Player B
                    tournamentRound.erase(iter);
                    if (iter == tournamentRound.end()) break;
                } else { // Player B has won + Eliminate Player A
                    tournamentRound.erase(--iter);
                }
            }
        }
        winnerIndex.insert((*tournamentRound.at(0)).getId());

        // Rotate Players
        std::vector<Player*>::iterator tournamentIter;
        Player* rotatePlayer = tournamentPlayers.at(0);
        tournamentPlayers.push_back(rotatePlayer);
        tournamentIter = tournamentPlayers.begin();
        tournamentPlayers.erase(tournamentIter);
    }

    int maxWinNumber = -1;
    int maxWinnerIndex = -1;
    for(int index = 0; index < 16; index++){
        int curWinNumber = winnerIndex.count(index);
        if(curWinNumber > maxWinNumber){
            maxWinNumber = curWinNumber;
            maxWinnerIndex = index;
        } else if(curWinNumber == maxWinNumber){
            if(index < maxWinnerIndex)
                maxWinnerIndex = index;
        } else{
            continue;
        }
    }

    for(int index = 0; index < players.size(); index++){
        delete tournamentPlayers[index];
    }

    return maxWinnerIndex;
}

/* =======END OF TODOs======= */

/* =======START OF THE MAIN CODE======= */
/* Please do not modify the code below */

typedef std::pair<std::string, std::multiset<int>> player;

player scan_player() {
    std::multiset<int> numbers;
    std::string player_type; int size;
    std::cin >> player_type >> size;
    for(int i=0;i<size;i++) {
        int t; std::cin >> t; numbers.insert(t);
    }
    return make_pair(player_type, numbers);
}

void print_multiset(const std::multiset<int>& m) {
    for(int number : m) {
        std::cout << number << " ";
    }
    std::cout << std::endl;
}

int main() {
    int question_number;
    std::cin >> question_number;
    if (question_number == 1) {
        int a, b;
        std::cin >> a >> b;
        std::tie(a, b) = number_fight(a, b);
        std::cout << a << " " << b << std::endl;
    } else if (question_number == 2) {
        int a, b;
        std::cin >> a >> b;
        std::tie(a, b) = number_vs_number(a, b);
        std::cout << a << " " << b << std::endl;
    } else if (question_number == 3 || question_number == 4) {
        auto a = scan_player();
        auto b = scan_player();
        std::multiset<int> a_, b_;
        if (question_number == 3) {
            tie(a_, b_) = player_battle(
                    a.first, a.second, b.first, b.second
            );
        } else {
            tie(a_, b_) = player_vs_player(
                    a.first, a.second, b.first, b.second
            );
        }
        print_multiset(a_);
        print_multiset(b_);
    } else if (question_number == 5 || question_number == 6) {
        int num_players;
        std::cin >> num_players;
        std::vector<player> players;
        for (int i = 0; i < num_players; i++) {
            players.push_back(scan_player());
        }
        int winner_id;
        if (question_number == 5) {
            winner_id = tournament(players);
        } else {
            winner_id = steady_winner(players);
        }
        std::cout << winner_id << std::endl;
    }
}
/* =======END OF MAIN CODE======= */