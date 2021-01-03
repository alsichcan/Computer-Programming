public class IncreasingString {
    public static void printLongestIncreasingSubstringLength(String inputString) {
        // DO NOT change the skeleton code.
        // You can add codes anywhere you want.

        int maxLength = 1;
        int tempLength = 1;
        char lastChar = 'a';
        char newChar = 'a'; // default
        
        for(int idx = 0; idx < inputString.length(); idx++){
            if(idx == 0){
                lastChar = inputString.charAt(idx);
                continue;
            }

            newChar = inputString.charAt(idx);
            if(lastChar < newChar) tempLength++;
            else tempLength = 1;

            if(tempLength > maxLength) maxLength = tempLength;
            lastChar = newChar;
        }

        System.out.println(maxLength);
    }
}
