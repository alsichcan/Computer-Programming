package evolution.of.truth.match;

import evolution.of.truth.agent.Agent;

public class MistakeMatch extends Match {
    private static int ruleMatrix[][][] = {
            {
                    {0, 0}, // A cheats, B cheats
                    {3, -1} // A cheats, B cooperates
            },
            {
                    {-1, 3}, // A cooperates, B cheats
                    {2, 2} // A cooperates, B cooperates
            }
    };

    public MistakeMatch(Agent agentA, Agent agentB) {
        super(agentA, agentB);
    }

    @Override
    public void playGame(){
        int choiceA = agentA.choice(previousChoiceB, ppreviousChoiceB);
        int choiceB = agentB.choice(previousChoiceA, ppreviousChoiceA);


        double probA = Math.random();
        double probB = Math.random();

        if(probA <= 0.05) {
            if (choiceA == Match.COOPERATE) choiceA = Match.CHEAT;
            else if (choiceA == Match.CHEAT) choiceA = Match.COOPERATE;
        }

        if(probB <= 0.05){
            if(choiceB == Match.COOPERATE) choiceB = Match.CHEAT;
            else if(choiceB == Match.CHEAT) choiceB = Match.COOPERATE;
        }

        agentA.setScore(agentA.getScore() + ruleMatrix[choiceA][choiceB][0]);
        agentB.setScore(agentB.getScore() + ruleMatrix[choiceA][choiceB][1]);
        ppreviousChoiceA = previousChoiceA;
        ppreviousChoiceB = previousChoiceB;
        previousChoiceA = choiceA;
        previousChoiceB = choiceB;
    }
}
