import java.util.*;

public class Solution {
    public int[] decrypt(int[] code, int k) {
        int n = code.length;
        int[] result = new int[n];

        if (k == 0) {
            Arrays.fill(result, 0);
            return result;
        }

        for (int i = 0; i < n; i++) {
            int sum = 0;
            int count = Math.abs(k);
            int index = i;

            for (int j = 1; j <= count; j++) {
                if (k > 0) {
                    index = (i + j) % n;  // forward
                } else {
                    index = (i - j + n) % n;  // backward with wrap-around
                }
                sum += code[index];
            }

            result[i] = sum;
        }

        return result;
    }

    // Example usage
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] code = {5, 7, 1, 4};
        int k = 3;
        System.out.println(Arrays.toString(sol.decrypt(code, k)));  // Output: [12, 10, 16, 13]
    }
}
