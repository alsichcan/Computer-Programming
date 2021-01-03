package Platform;

import Platform.Games.Dice;
import Platform.Games.RockPaperScissors;

import java.util.Scanner;

public class Platform {
    private int roundNum = 1;
    private boolean roundSet = false;

    public void setRounds(int num){
        if(!roundSet){
            roundSet = true;
            roundNum = num;
        }
        
    }

    public float run(){
        Scanner sc = new Scanner(System.in);

        int game = sc.nextInt();

        if(game == 0){
            Dice dice = new Dice();
            int sum = 0;
            for(int i = 0; i < roundNum; i++){
                int status = dice.playGame();
                if(status == 1)
                    sum += 1;
            }
            return sum / (float) roundNum;
        } else if(game == 1){
            RockPaperScissors rsp = new RockPaperScissors();
            int sum = 0;
            for(int i = 0; i < roundNum; i++){
                int status = rsp.playGame();
                if(status == 1)
                    sum += 1;
            }
            return sum / (float) roundNum;
        } else{
            return 0.0f;
        }
    }
}
