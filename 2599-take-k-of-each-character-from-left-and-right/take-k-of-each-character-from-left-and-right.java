import java.util.*;

public class Solution {
    public int takeCharacters(String s, int k) {
        if (k == 0) return 0;

        int n = s.length();
        int[] total = new int[3]; // counts of a, b, c

        for (char c : s.toCharArray()) {
            total[c - 'a']++;
        }

        // If any character occurs less than k, it's impossible
        for (int count : total) {
            if (count < k) return -1;
        }

        int maxLen = 0;
        int left = 0;
        int[] count = new int[3];

        for (int right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']++;

            // shrink window from left if it causes not enough chars outside
            while (count[0] > total[0] - k || 
                   count[1] > total[1] - k || 
                   count[2] > total[2] - k) {
                count[s.charAt(left) - 'a']--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        // Minimum minutes = total length - maxLen of valid middle
        return n - maxLen;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        System.out.println(sol.takeCharacters("aabaaaacaabc", 2));  // Output: 8
    }
}
