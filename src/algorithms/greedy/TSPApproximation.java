package algorithms.greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TSPApproximation {

    private final int V;
    private final int[][] graph;

    public TSPApproximation(int[][] graph) {
        this.V = graph.length;
        this.graph = graph;
    }

    public List<Integer> tsp() {
        boolean[] visited = new boolean[V];
        List<Integer> path = new ArrayList<>();
        int start = 0;
        path.add(start);
        visited[start] = true;
        for (int i = 0; i < V - 1; i++) {
            int nearest = -1;
            int nearestDistance = Integer.MAX_VALUE;
            for (int j = 0; j < V; j++) {
                if (!visited[j] && graph[start][j] < nearestDistance) {
                    nearest = j;
                    nearestDistance = graph[start][j];
                }
            }
            visited[nearest] = true;
            path.add(nearest);
            start = nearest;
        }
        path.add(0); // Return to the starting point
        return path;
    }

    public int calculateCost(List<Integer> path) {
        int cost = 0;
        for (int i = 0; i < path.size() - 1; i++) {
            cost += graph[path.get(i)][path.get(i + 1)];
        }
        return cost;
    }
}
