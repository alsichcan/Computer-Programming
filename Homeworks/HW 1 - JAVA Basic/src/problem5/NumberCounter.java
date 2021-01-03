public class NumberCounter {
    public static void countNumbers(String str0, String str1, String str2) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        Long val0 = Long.parseLong(str0);
        Long val1 = Long.parseLong(str1);
        Long val2 = Long.parseLong(str2);
        Long mulVal = val0 * val1 * val2;

        String strVal = mulVal.toString();

        int[] digitCount = new int[10];
        for(int i = 0; i < strVal.length(); i++){
            digitCount[strVal.charAt(i) - '0']++;
        }

        for(int digit = 0; digit < digitCount.length; digit++){
            if(digitCount[digit] == 0) continue;

            printNumberCount(digit, digitCount[digit]);
        }
    }

    private static void printNumberCount(int number, int count) {
        System.out.printf("%d: %d times\n", number, count);
    }
}
