public class MultiplicationTable {
    public static void printMultiplicationTable(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.

        for(int i = 1; i <= n; i++){
            for(int j = 1; j <= n; j++){
                printOneMultiplication(i, j, i*j);
            }
        }
    }

    private static void printOneMultiplication(int a, int b, int c) {
        System.out.printf("%d times %d = %d\n", a, b, c);
    }
}