package evolution.of.truth.agent;

import evolution.of.truth.match.Match;
import kiroong.Individual;

public class CopyKitten extends Agent{
    public CopyKitten() {
        super("CopyKitten");
    }

    @Override
    public Individual clone() {
        return new CopyKitten();
    }

    @Override
    public int choice(int previousOpponentChoice, int ppreviousOpponentChoice) {
        if (previousOpponentChoice == Match.UNDEFINED) {
            return Match.COOPERATE;
        } else if (previousOpponentChoice == Match.CHEAT && ppreviousOpponentChoice == Match.CHEAT){
            return Match.CHEAT;
        } else return Match.COOPERATE;
    }
}
