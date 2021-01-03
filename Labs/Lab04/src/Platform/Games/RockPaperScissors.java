package Platform.Games;

import java.util.Scanner;

public class RockPaperScissors {
    public int playGame(){
        Scanner sc = new Scanner(System.in);

        String userChoice = sc.nextLine();

        if (!(userChoice.equals("rock") || userChoice.equals("paper") || userChoice.equals("scissors"))){
            return -1;
        }

        String[] choice = {"rock", "paper", "scissors"};
        String opChoice = choice[(int) (Math.random()*3)];

        System.out.println(userChoice + " " + opChoice);

        if(userChoice.equals("rock")){
            if(opChoice.equals("rock")) return 0;
            else if(opChoice.equals("scissors")) return 1;
            else return -1;
        } else if(userChoice.equals("scissors")){
            if(opChoice.equals("rock")) return -1;
            else if(opChoice.equals("scissors")) return 0;
            else return 1;
        } else if(userChoice.equals("paper")){
            if(opChoice.equals("rock")) return 1;
            else if(opChoice.equals("scissors")) return -1;
            else return 0;
        } else{
            return -1;
        }
    }
}
