public class Solution {
    public int maxScore(String s) {
        int count_left_zeros = 0;
        int count_right_ones = 0;
        
        // Count the number of ones in the entire string (this will be for the right substring initially)
        for (char c : s.toCharArray()) {
            if (c == '1') {
                count_right_ones++;
            }
        }
        
        int maxScore = 0;
        
        // Now traverse the string and calculate the score for each split point
        for (int i = 0; i < s.length() - 1; i++) {  // Ensure both substrings are non-empty
            if (s.charAt(i) == '0') {
                count_left_zeros++;
            } else {
                count_right_ones--;
            }
            
            // Calculate the score: left zeros + right ones
            maxScore = Math.max(maxScore, count_left_zeros + count_right_ones);
        }
        
        return maxScore;
    }
}
