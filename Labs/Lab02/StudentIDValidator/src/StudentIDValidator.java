import java.util.*;

public class StudentIDValidator {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true) {
            String input = sc.nextLine();
            if(input.equals("exit"))
                break;

            validateStudentID(input);
        }
    }
    static void validateStudentID(String inputID){
        if(!isProperLength(inputID)){
            System.out.println("The input length should be 10.");
            return;
        } else if (!hasProperDivision(inputID)){
            System.out.println("Fifth character should be '-'.");
            return;
        } else if(!hasProperDigits(inputID)) {
            System.out.println("Contains an invalid digit.");
            return;
        } else{
            System.out.println(inputID + " is a valid student ID.");
            return;
        }
    }

    static boolean isProperLength(String inputID){
        return inputID.length() == 10;
    }

    static boolean hasProperDivision(String inputID){
        return inputID.charAt(4) == '-';
    }

    static boolean hasProperDigits(String inputID){
        boolean val = true;
        for(int i = 0 ; i < inputID.length(); i++){
            if(i == 4) continue;
            else{
                if(inputID.charAt(i) < '0' || inputID.charAt(i) > '9'){
                    val = false;
                }
            }
        }
        return val;
    }
}
