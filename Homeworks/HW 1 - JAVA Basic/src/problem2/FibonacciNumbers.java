public class FibonacciNumbers {
    public static void printFibonacciNumbers(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int var_0 = 0;
        int var_1 = 1;

        for(int i = 0; i < n ; i++){
            if(i == 0){
                System.out.print(var_0);
            } else if(i == 1){
                System.out.print(var_1);

            } else{
                int var_new = var_0 + var_1;
                System.out.print(var_new);
                var_0 = var_1;
                var_1 = var_new;
            }
            System.out.print(" ");
        }
    }
}
