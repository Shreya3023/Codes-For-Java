import java.util.*;

public class Solution {
    public int maxCount(int[] banned, int n, int maxSum) {
        Set<Integer> bannedSet = new HashSet<>();
        for (int b : banned) {
            bannedSet.add(b);
        }

        int count = 0;
        int sum = 0;

        for (int i = 1; i <= n; i++) {
            if (bannedSet.contains(i)) continue;
            if (sum + i > maxSum) break;

            sum += i;
            count++;
        }

        return count;
    }

    // Sample test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] banned = {1, 6, 5};
        int n = 5;
        int maxSum = 6;
        System.out.println(sol.maxCount(banned, n, maxSum)); // Output: 2
    }
}
