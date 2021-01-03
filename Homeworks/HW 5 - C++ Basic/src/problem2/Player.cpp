#include "Player.h"

Player::Player(std::string playerType, std::multiset<int> hands): playerType(playerType), hands(hands), id(0) {
    resultTable = new std::vector<int>[hands.size()];
}

Player::Player(std::string playerType, std::multiset<int> hands, int id): playerType(playerType), hands(hands), id(id) {
    resultTable = new std::vector<int>[hands.size()];
}

Player::~Player(){
    delete[] resultTable;
}

void Player::computeResult(){
    result = 0;
    std::multiset<int>::iterator iter;
    for(iter = hands.begin(); iter != hands.end(); iter++){
        result += *iter;
    }
}

int Player::getId() const{
    return id;
}

std::string Player::getPlayerType() const{
    return playerType;
}


int Player::getResult() {
    computeResult();
    return result;
}

std::multiset<int> Player::getHands() const{
    return hands;
}

int Player::getNumber(int index) const{
    std::multiset<int>::iterator iter = hands.begin();
    for(int i = 0; i < index; i++)
        iter++;
    return *iter;
}

void Player::clearResultTable(){
    for(int i = 0; i < hands.size(); i++){
        resultTable[i].clear();
    }
}

void Player::updateHand(int beforeBattle, int afterBattle){
    int sameNumCount = hands.erase(beforeBattle);
    for(int i = 0 ; i < sameNumCount-1; i++){
        hands.insert(beforeBattle);
    }
    hands.insert(afterBattle);
}

int Player::chooseNumber() {
    if(playerType == "Maximize-Gain")
        return Maximize_Gain();
    else if(playerType == "Minimize-Loss")
        return Minimize_Loss();
    else if(playerType == "Minimize-Regret")
        return Minimize_Regret();
    else
        return 0;
}

int Player::Maximize_Gain(){
    std::pair<int, int>* optionMaxGainTable = new std::pair<int, int>[hands.size()];

    // Calculate maximum gain of each option Player can take
    for(int option = 0; option < hands.size(); option++){
        int maxGain = std::numeric_limits<int>::min();
        std::vector<int> optionResult = resultTable[option];
        for(int i = 0; i < optionResult.size(); i++){
            if(maxGain < optionResult[i])
                maxGain = optionResult[i];
        }
        optionMaxGainTable[option] = std::pair<int, int>(getNumber(option), maxGain);
    }

    // Get an option with maximum gain
    int curMax = std::numeric_limits<int>::min();
    int curOpt = -1;
    for(int option = 0; option < hands.size(); option++){
        if(curMax < optionMaxGainTable[option].second){
            // If new maximum gain, update current Option and Max value
            curOpt = optionMaxGainTable[option].first;
            curMax = optionMaxGainTable[option].second;
        } else if(curMax == optionMaxGainTable[option].second){
            // If same maximum gain, update current Option only when new option is smaller
            if(curOpt > optionMaxGainTable[option].first)
                curOpt = optionMaxGainTable[option].first;
        } else{
            continue;
        }
    }

    // Delete optionMaxGainTable
    if(optionMaxGainTable)
        delete[] optionMaxGainTable;

    return curOpt;
}

int Player::Minimize_Loss(){
    std::pair<int, int>* optionMinLossTable = new std::pair<int, int>[hands.size()];

    // Calculate worst loss of each option Player can take
    for(int option = 0; option < hands.size(); option++){
        int worstLoss = std::numeric_limits<int>::max();
        std::vector<int> optionResult = resultTable[option];
        for(int i = 0; i < optionResult.size(); i++){
            if(worstLoss > optionResult[i])
                worstLoss = optionResult[i];
        }
        optionMinLossTable[option] = std::pair<int, int>(getNumber(option), worstLoss);
    }

    // Get an option with minimum worst loss
    int curMin = std::numeric_limits<int>::min();
    int curOpt = -1;
    for(int option = 0; option < hands.size(); option++){
        if(curMin < optionMinLossTable[option].second){
            // If new minimum loss, update current Option and min value
            curOpt = optionMinLossTable[option].first;
            curMin = optionMinLossTable[option].second;
        } else if(curMin == optionMinLossTable[option].second){
            // If same minimum loss, update current Option only when new option is smaller
            if(curOpt > optionMinLossTable[option].first)
                curOpt = optionMinLossTable[option].first;
        } else{
            continue;
        }
    }

    // Delete optionMaxGainTable
    if(optionMinLossTable)
        delete[] optionMinLossTable;

    return curOpt;
}

int Player::Minimize_Regret(){
    int worstOutcome[hands.size()]; // The worst-case outcome of its choice
    int bestOutcome[hands.size()]; // The best-case outcome of its choice
    int bestCase[hands.size()]; // One best-case outcome among all other choices
    int regret[hands.size()];

    // Calculate worst case of each option Player can take
    for(int option = 0; option < hands.size(); option++){
        int worstLoss = std::numeric_limits<int>::max();
        std::vector<int> optionResult = resultTable[option];
        for(int i = 0; i < optionResult.size(); i++){
            if(worstLoss > optionResult[i])
                worstLoss = optionResult[i];
        }
        worstOutcome[option] = worstLoss;
    }

    // Calculate best case of all other options Player can take
    for(int option = 0; option < hands.size(); option++){
        int bestGain = std::numeric_limits<int>::min();
        std::vector<int> optionResult = resultTable[option];
        for(int i = 0; i < optionResult.size(); i++){
            if(bestGain < optionResult[i])
                bestGain = optionResult[i];
        }
        bestOutcome[option] = bestGain;
    }

    for(int option = 0; option < hands.size(); option++){
        int oppCost = std::numeric_limits<int>::min(); // Biggest opportunity cost of an option
        for(int other_option = 0; other_option < hands.size(); other_option++){
            if(option == other_option) continue;

            if(oppCost < bestOutcome[other_option])
                oppCost = bestOutcome[other_option];
        }
        bestCase[option] = oppCost;
    }

    // Calculate regret of choice
    for(int option = 0; option < hands.size(); option++){
        regret[option] = bestCase[option] - worstOutcome[option];
    }

    // Choose an option with least regret
    int curRegret = std::numeric_limits<int>::max();
    int curOpt = -1;
    for(int option = 0; option < hands.size(); option++){
        if(curRegret > regret[option]){
            // If new minimum regret, update current Option and regret value
            curOpt = option;
            curRegret = regret[option];
        } else if(curRegret == regret[option]){
            // If same regret, update current Option only when new option is smaller
            if(getNumber(curOpt) > getNumber(option))
                curOpt = option;
        } else{
            continue;
        }
    }

    return getNumber(curOpt);
}

