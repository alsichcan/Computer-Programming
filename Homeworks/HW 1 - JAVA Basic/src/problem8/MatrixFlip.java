public class MatrixFlip {
    public static void printFlippedMatrix(char[][] matrix) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.


        for(int i = matrix.length-1; i >= 0; i--){
            for(int j = matrix[i].length-1; j >= 0 ; j--){
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }
    }
}