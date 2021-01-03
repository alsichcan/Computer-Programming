package Platform.Games;

public class Dice {
    public int playGame() {
        int myNum = (int) (Math.random() * 100);
        int opNum = (int) (Math.random() * 100);

        System.out.println(myNum + " " + opNum);

        if(myNum > opNum) return 1;
        else if(myNum < opNum) return -1;
        else return 0;

    }
}
