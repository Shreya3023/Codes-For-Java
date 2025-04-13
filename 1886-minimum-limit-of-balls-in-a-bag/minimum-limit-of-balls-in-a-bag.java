public class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
        int left = 1, right = (int) 1e9;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canDivide(nums, mid, maxOperations)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    private boolean canDivide(int[] nums, int maxBalls, int maxOps) {
        int opsNeeded = 0;

        for (int balls : nums) {
            // Number of operations to split this bag to maxBalls size or less
            opsNeeded += (balls - 1) / maxBalls;
        }

        return opsNeeded <= maxOps;
    }

    // Sample test
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] nums = {9};
        int maxOperations = 2;
        System.out.println(sol.minimumSize(nums, maxOperations)); // Output: 3
    }
}
