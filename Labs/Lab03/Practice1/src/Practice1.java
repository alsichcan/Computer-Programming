import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        while(true) {
            int num = sc.nextInt();
            if(num == 0) break;

            char[][] matrix = new char[num][num];

            for (int row = 0; row < num; row++) {
                for (int col = 0; col < num; col++) {
                    matrix[row][col] = 'O';
                }
            }

            for (int row = 0; row < num; row++) {
                for (int col = 0; col < num; col++) {
                    matrix[row][row] = 'X';
                    matrix[row][num - row - 1] = 'X';
                }
            }

            for (int row = 0; row < num; row++) {
                for (int col = 0; col < num; col++) {
                    System.out.print(matrix[row][col]);
                }
                System.out.println("");
            }
        }
    }
}
