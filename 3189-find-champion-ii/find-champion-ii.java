public class Solution {
    public int findChampion(int n, int[][] edges) {
        // Initialize an array to count in-degrees of each team
        int[] inDegree = new int[n];
        
        // Populate the in-degree array
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            inDegree[v]++; // team v has one more incoming edge
        }
        
        // Find the team(s) with 0 incoming edges (in-degree == 0)
        int champion = -1;
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                if (champion == -1) {
                    champion = i;  // First team with no incoming edge, set as candidate
                } else {
                    return -1;  // More than one team with no incoming edge, return -1
                }
            }
        }
        
        return champion;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Example input
        int n = 6;
        int[][] edges = {
            {0, 1},
            {0, 2},
            {2, 3},
            {3, 4},
            {4, 5}
        };
        
        int result = sol.findChampion(n, edges);
        System.out.println(result); // Output: 0, as team 0 is the champion
    }
}
