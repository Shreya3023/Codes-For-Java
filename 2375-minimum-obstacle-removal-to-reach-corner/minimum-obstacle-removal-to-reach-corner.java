import java.util.*;

public class Solution {
    // Method to calculate minimum obstacles
    public int minimumObstacles(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        
        // Directions for moving: up, down, left, right
        int[] dirs = {-1, 0, 1, 0, -1, 0};
        
        // Min-Heap Priority Queue to store cells by the number of obstacles removed
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        
        // 2D array to track the minimum obstacles removed to reach each cell
        int[][] minObstacles = new int[m][n];
        for (int[] row : minObstacles) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        minObstacles[0][0] = 0;
        
        // Start with the initial position (0, 0), obstacles removed is 0
        pq.offer(new int[]{0, 0, 0});
        
        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int x = curr[0], y = curr[1], removed = curr[2];
            
            // If we reached the bottom-right corner, return the result
            if (x == m - 1 && y == n - 1) {
                return removed;
            }
            
            // Explore the 4 neighboring cells
            for (int i = 0; i < 4; i++) {
                int nx = x + dirs[i];
                int ny = y + dirs[i + 1];
                
                if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                    int newRemoved = removed + grid[nx][ny];
                    
                    // If we found a path with fewer obstacles removed, update the state
                    if (newRemoved < minObstacles[nx][ny]) {
                        minObstacles[nx][ny] = newRemoved;
                        pq.offer(new int[]{nx, ny, newRemoved});
                    }
                }
            }
        }
        
        // If we cannot reach the bottom-right corner, return -1
        return -1;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] grid = {
            {0, 1, 1},
            {1, 0, 1},
            {0, 0, 0}
        };
        int result = sol.minimumObstacles(grid);
        System.out.println(result); // Output: 2
    }
}
