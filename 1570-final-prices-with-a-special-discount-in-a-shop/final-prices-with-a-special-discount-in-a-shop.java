import java.util.Stack;

class Solution {
    public int[] finalPrices(int[] prices) {
        int n = prices.length;
        int[] answer = new int[n];
        Stack<Integer> stack = new Stack<>();

        // Traverse through the prices array
        for (int i = 0; i < n; i++) {
            // Apply discount if necessary
            while (!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                int index = stack.pop();
                answer[index] = prices[index] - prices[i];
            }
            // Push current index to the stack
            stack.push(i);
        }
        
        // For indices where no discount was applied
        while (!stack.isEmpty()) {
            int index = stack.pop();
            answer[index] = prices[index];
        }

        return answer;
    }
}
