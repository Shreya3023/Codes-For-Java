import java.util.*;

public class Solution {
    // Method to compute shortest distance after queries
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // Initialize the graph with the roads from city i to city i+1
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < n - 1; i++) {
            graph.get(i).add(i + 1); // roads from i to i+1
        }

        int[] result = new int[queries.length];

        // For each query, add the new road and compute the shortest path
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            graph.get(u).add(v); // Add the new road from u to v

            // Find the shortest path using BFS
            result[i] = bfs(n, graph);
        }

        return result;
    }

    // BFS to find the shortest path from city 0 to city n-1
    private int bfs(int n, List<List<Integer>> graph) {
        // Distance array, initialized to -1 (meaning not visited)
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[0] = 0; // Start from city 0

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0); // Start BFS from city 0

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // Explore all neighbors
            for (int next : graph.get(curr)) {
                if (dist[next] == -1) { // If not visited
                    dist[next] = dist[curr] + 1;
                    queue.offer(next);
                }
            }
        }

        return dist[n - 1]; // Return the distance to city n-1
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 5;
        int[][] queries = {
            {0, 2},
            {2, 4},
            {0, 3}
        };

        int[] result = sol.shortestDistanceAfterQueries(n, queries);
        System.out.println(Arrays.toString(result)); // Output the result array
    }
}
