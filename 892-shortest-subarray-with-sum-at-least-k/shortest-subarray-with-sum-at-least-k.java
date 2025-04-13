import java.util.*;

public class Solution {
    public int shortestSubarray(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n + 1];
        
        // Build prefix sum array
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums[i];
        }

        Deque<Integer> deque = new ArrayDeque<>();
        int minLen = n + 1;

        for (int i = 0; i <= n; i++) {
            // Try to shorten the window from the front
            while (!deque.isEmpty() && prefix[i] - prefix[deque.peekFirst()] >= k) {
                minLen = Math.min(minLen, i - deque.pollFirst());
            }

            // Maintain increasing order in the deque
            while (!deque.isEmpty() && prefix[i] <= prefix[deque.peekLast()]) {
                deque.pollLast();
            }

            deque.offerLast(i);
        }

        return minLen <= n ? minLen : -1;
    }

    // Example main method
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {2, -1, 2};
        int k = 3;
        System.out.println(sol.shortestSubarray(nums, k));  // Output: 3
    }
}
