package evolution.of.truth.environment;

import evolution.of.truth.agent.*;
import evolution.of.truth.match.MistakeMatch;
import kiroong.Individual;
import kiroong.Population;

public class Tournament {
    Population agentPopulation;

    public Tournament() {
        agentPopulation = new Population();
        for (int i = 0; i < 15; i++) {
            agentPopulation.addIndividual(new CopyKitten());
        }
        for (int i = 0; i < 5; i++) {
            agentPopulation.addIndividual(new Devil());
        }
        for (int i = 0; i < 5; i++) {
            agentPopulation.addIndividual(new Copycat());
        }
    }

    public void evolvePopulation() {
        agentPopulation.toNextGeneration(5);
    }

    public void resetAgents() {
        Individual[] agents = agentPopulation.getIndividuals();
        for(Individual _agent: agents) {
            Agent agent = (Agent)_agent;
            agent.setScore(0);
        }
    }

    private MistakeMatch[] createAllMatches() {
        int n = agentPopulation.size();
        Individual[] agents = agentPopulation.getIndividuals();
        MistakeMatch[] matches = new MistakeMatch[n * (n - 1) / 2];
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                matches[index++] = new MistakeMatch((Agent)agents[i], (Agent)agents[j]);
            }
        }
        return matches;
    }

    public void playAllGames(int numRounds) {
        MistakeMatch[] matches = createAllMatches();
        for (int round = 0; round < numRounds; round++) {
            for (MistakeMatch match : matches) {
                match.playGame();
            }
        }
    }

    public void describe() {
        Individual[] agents = agentPopulation.getIndividuals();
        for(Individual _agent: agents) {
            Agent agent = (Agent)_agent;
            System.out.print(agent.toString() + " / ");
        }
        System.out.println();
    }
}
