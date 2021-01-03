public class CardGameSimulator {
    private static final Player[] players = new Player[2];

    public static void simulateCardGame(String inputA, String inputB) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
    }

    private static void printLoseMessage(Player player) {
        System.out.printf("Player %s loses the game!\n", player);
    }
}


class Player {
    private String name;
    private Card[] deck;

    public void playCard(Card card) {
        System.out.printf("Player %s: %s\n", name, card);
    }

    @Override
    public String toString() {
        return name;
    }
}


class Card {
    private int number;
    private char shape;

    @Override
    public String toString() {
        return "" + number + shape;
    }
}
