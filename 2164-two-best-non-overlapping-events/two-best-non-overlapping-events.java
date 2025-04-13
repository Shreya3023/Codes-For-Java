import java.util.*;

public class Solution {
    public int maxTwoEvents(int[][] events) {
        Arrays.sort(events, Comparator.comparingInt(a -> a[1])); // Sort by endTime

        int n = events.length;
        int[] dp = new int[n]; // max value up to i-th event
        int[] endTimes = new int[n]; // for binary search
        int maxValue = 0;

        for (int i = 0; i < n; i++) {
            int value = events[i][2];
            int idx = binarySearch(events, events[i][0]); // index of last non-overlapping event
            int bestBefore = idx >= 0 ? dp[idx] : 0;
            maxValue = Math.max(maxValue, value + bestBefore);

            dp[i] = Math.max(i > 0 ? dp[i - 1] : 0, value);
            endTimes[i] = events[i][1];
        }

        return maxValue;
    }

    // Find the last event whose endTime < current startTime
    private int binarySearch(int[][] events, int startTime) {
        int left = 0, right = events.length - 1, res = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (events[mid][1] < startTime) {
                res = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] events = {
            {1, 3, 4},
            {2, 4, 3},
            {3, 10, 5},
            {11, 13, 10}
        };
        System.out.println(sol.maxTwoEvents(events)); // Output: 15
    }
}
