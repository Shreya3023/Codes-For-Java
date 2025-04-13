import java.util.*;

public class Solution {

    // The target state of the board
    private static final String TARGET = "123450";

    // All possible moves for the empty space (0)
    private static final int[] DIRS = {-1, 1, -3, 3}; // left, right, up, down

    public int slidingPuzzle(int[][] board) {
        // Convert the 2x3 board into a string to make it easier to handle
        String start = convertToString(board);

        // If the start state is already the target, no moves are needed
        if (start.equals(TARGET)) {
            return 0;
        }

        // Use BFS to find the minimum number of moves
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.offer(start);
        visited.add(start);
        int moves = 0;

        // Perform BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String current = queue.poll();

                // Try all possible moves
                for (int dir : DIRS) {
                    int zeroIndex = current.indexOf('0');
                    int newZeroIndex = zeroIndex + dir;

                    // Check if the new position of 0 is within bounds and is valid
                    if (newZeroIndex >= 0 && newZeroIndex < 6 &&
                        !(zeroIndex == 2 && dir == 1) && !(zeroIndex == 3 && dir == -1)) {
                        // Swap the 0 with the adjacent number
                        String nextState = swap(current, zeroIndex, newZeroIndex);
                        
                        if (nextState.equals(TARGET)) {
                            return moves + 1;
                        }

                        // If the state is not visited, add it to the queue
                        if (!visited.contains(nextState)) {
                            visited.add(nextState);
                            queue.offer(nextState);
                        }
                    }
                }
            }
            moves++;
        }

        // If we exhausted all possibilities and did not find the solution, return -1
        return -1;
    }

    // Convert the board 2x3 into a string
    private String convertToString(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(board[i][j]);
            }
        }
        return sb.toString();
    }

    // Swap positions of 0 with an adjacent number
    private String swap(String board, int i, int j) {
        char[] chars = board.toCharArray();
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
        return new String(chars);
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] board = {
            {1, 2, 3},
            {4, 0, 5}
        };
        
        int result = sol.slidingPuzzle(board);
        System.out.println(result);  // Output should be the minimum number of moves to solve the puzzle
    }
}
