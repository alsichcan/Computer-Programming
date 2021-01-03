public class CardGameSimulator {
    private static final Player[] players = new Player[2];

    public static void simulateCardGame(String inputA, String inputB) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        players[0] = new Player(inputA, "A");
        players[1] = new Player(inputB, "B");

        Card aCard = null;
        Card bCard = null;

        aCard = players[0].dealCard();
        players[0].playCard(aCard);

        while(true){

            bCard = players[1].dealCard(aCard);
            if(bCard == null){
                printLoseMessage(players[1]);
                break;
            }
            else players[1].playCard(bCard);

            aCard = players[0].dealCard(bCard);
            if(aCard == null){
                printLoseMessage(players[0]);
                break;
            }
            else players[0].playCard(aCard);
        }
    }

    private static void printLoseMessage(Player player) {
        System.out.printf("Player %s loses the game!\n", player);
    }
}


class Player {
    private String name;
    private Card[] deck;

    Player(String stringDeck, String name){
        this.name = name;
        deck = new Card[10];
        String[] sDeck = stringDeck.split(" ");
        for(int i = 0 ; i < sDeck.length; i++){
            deck[i] = new Card(Character.getNumericValue(sDeck[i].charAt(0)), sDeck[i].charAt(1));
        }
    }

    public Card dealCard(){
        Card dealCard = null;
        int max = -1;
        int idx = -1;
        for(int i = 0 ; i < deck.length; i++){
            if(deck[i].getNumber() > max){
                max = deck[i].getNumber();
                idx = i;
            } else if(deck[i].getNumber() == max){
                if(deck[i].getShape() == 'O'){
                    idx = i;
                }
            }
        }
        dealCard = deck[idx];
        deck[idx] = null;
        return dealCard;
    }

    public Card dealCard(Card card){

        boolean cardToDeal = false;
        Card dealCard = null;

        for(int i = 0 ; i < deck.length; i++){
            if(deck[i] == null) continue;

            if(deck[i].getNumber() == card.getNumber()) {
                dealCard = deck[i];
                deck[i] = null;
                cardToDeal = true;
                break;
            }
        }


        if(!cardToDeal) {
            int max = -1;
            int idx = -1;
            for (int i = 0; i < deck.length; i++) {
                if(deck[i] == null) continue;
                if (deck[i].getShape() == card.getShape()) {
                    if (deck[i].getNumber() > max) {
                        max = deck[i].getNumber();
                        idx = i;
                    }
                }
            }
            if(idx != -1) {
                dealCard = deck[idx];
                deck[idx] = null;
                cardToDeal = true;
            }
        }

        return dealCard;
    }

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

    Card(int number, char shape){
        this.number = number;
        this.shape = shape;
    }

    public int getNumber(){
        return this.number;
    }

    public char getShape(){
        return this.shape;
    }

    @Override
    public String toString() {
        return "" + number + shape;
    }
}
