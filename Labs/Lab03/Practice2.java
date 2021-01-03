import java.util.Scanner;

public class Practice2 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true){
            int num = sc.nextInt();
            if(num == 0) break;


            boolean[] isPrime = new boolean[num];
            isPrime[0] = false;
            isPrime[1] = false;
            for(int i = 2; i < isPrime.length; i++){
                isPrime[i] = true;
            }

             for(int i = 2; i < num; i++){
                if(isPrime[i] == false) continue;

                for(int j = i+i; j < num ; j += i ){
                    isPrime[j] = false;
                }
            }

             for(int i = 0; i < isPrime.length; i++){
                 if(isPrime[i] == true){
                     System.out.print(i);
                     System.out.print(" ");
                 }
             }
             System.out.println("");
        }
    }
}
