public class CharacterCounter {
    public static void countCharacter(String str) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.
        int[] charCount = new int[26];

        for(int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            int index = (int) c - 97;
            charCount[index]++;
        }

        for(int idx = 0; idx < charCount.length; idx++){
            if(charCount[idx] == 0) continue;
            printCount((char) (idx+97), charCount[idx]);
        }
    }

    private static void printCount(char character, int count) {
        System.out.printf("%c: %d times\n", character, count);
    }
}