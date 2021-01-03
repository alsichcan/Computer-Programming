import java.util.*;

public class ArrayPrinter {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        sc.nextLine();
        String[] strings = new String[num];

        for(int i = 0 ; i < num ; i++){
            strings[i] = sc.nextLine();
        }

        for(int i = 0; i < num; i++){
            System.out.print(strings[i]);
            System.out.print(" ");
        }
        System.out.println("");
        System.out.println("--------------");

        for(int i = num-1; i >= 0 ; i--){
            System.out.print(strings[i]);
            System.out.print(" ");
        }
    }
}
