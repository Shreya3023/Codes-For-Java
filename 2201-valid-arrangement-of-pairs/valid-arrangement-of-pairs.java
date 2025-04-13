import java.util.*;

public class Solution {
    public int[][] validArrangement(int[][] pairs) {
        Map<Integer, Queue<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        Map<Integer, Integer> outdegree = new HashMap<>();

        for (int[] pair : pairs) {
            graph.putIfAbsent(pair[0], new LinkedList<>());
            graph.get(pair[0]).offer(pair[1]);

            outdegree.put(pair[0], outdegree.getOrDefault(pair[0], 0) + 1);
            indegree.put(pair[1], indegree.getOrDefault(pair[1], 0) + 1);
        }

        int start = pairs[0][0];
        for (int key : graph.keySet()) {
            if (outdegree.getOrDefault(key, 0) > indegree.getOrDefault(key, 0)) {
                start = key;
                break;
            }
        }

        LinkedList<int[]> result = new LinkedList<>();
        dfs(start, graph, result);

        return result.toArray(new int[result.size()][]);
    }

    private void dfs(int node, Map<Integer, Queue<Integer>> graph, LinkedList<int[]> result) {
        Queue<Integer> neighbors = graph.get(node);
        while (neighbors != null && !neighbors.isEmpty()) {
            int next = neighbors.poll();
            dfs(next, graph, result);
            result.addFirst(new int[]{node, next});
        }
    }
}
