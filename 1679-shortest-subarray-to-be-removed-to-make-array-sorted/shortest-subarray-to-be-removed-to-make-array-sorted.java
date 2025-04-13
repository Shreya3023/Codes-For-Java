public class Solution {
    public int findLengthOfShortestSubarray(int[] arr) {
        int n = arr.length;

        // Step 1: Find non-decreasing prefix
        int left = 0;
        while (left + 1 < n && arr[left] <= arr[left + 1]) {
            left++;
        }

        // If the whole array is non-decreasing
        if (left == n - 1) return 0;

        // Step 2: Find non-decreasing suffix
        int right = n - 1;
        while (right > 0 && arr[right - 1] <= arr[right]) {
            right--;
        }

        // Step 3: Try removing prefix or suffix entirely
        int minRemove = Math.min(n - left - 1, right);

        // Step 4: Try merging prefix and suffix
        int i = 0;
        int j = right;

        while (i <= left && j < n) {
            if (arr[i] <= arr[j]) {
                minRemove = Math.min(minRemove, j - i - 1);
                i++;
            } else {
                j++;
            }
        }

        return minRemove;
    }

    // Optional: Main method to test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {1, 2, 3, 10, 4, 2, 3, 5};
        System.out.println(sol.findLengthOfShortestSubarray(arr));  // Output: 3
    }
}
