public class PrimeNumbers {
    public static void printPrimeNumbers(int n) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.

        int[] primeNums = new int[100];
        int foundPNum = 0;

        int num = 2;
        while(foundPNum < n){
            if(foundPNum == 0){
                primeNums[foundPNum] = num;
                foundPNum++;
                num++;
                continue;
            }

            int checkIdx = 0;
            boolean isPNum = true;
            while(checkIdx < foundPNum){
                if(num % primeNums[checkIdx] == 0){
                    isPNum = false;
                    break;
                } else{
                    checkIdx++;
                }
            }

            if(isPNum){
                primeNums[foundPNum] = num;
                foundPNum++;
            }

            num++;
        }

        System.out.print(primeNums[0]);
        for(int i = 1; i < n; i++){
            System.out.print(" ");
            System.out.print(primeNums[i]);
        }

    }
}
