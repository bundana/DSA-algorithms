package algorithms.greedy;

import java.util.*;

public class PrimsMST {

    class Edge {
        int src, dest, weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    class Graph {
        int V;
        LinkedList<Edge>[] adjacencylist;

        Graph(int V) {
            this.V = V;
            adjacencylist = new LinkedList[V];
            for (int i = 0; i < V; i++) {
                adjacencylist[i] = new LinkedList<>();
            }
        }

        void addEdge(int src, int dest, int weight) {
            Edge edge = new Edge(src, dest, weight);
            adjacencylist[src].addFirst(edge);
            edge = new Edge(dest, src, weight);
            adjacencylist[dest].addFirst(edge);
        }

        void primMST() {
            boolean[] mst = new boolean[V];
            Edge[] e = new Edge[V];
            int[] key = new int[V];
            for (int i = 0; i < V; i++) {
                key[i] = Integer.MAX_VALUE;
                e[i] = new Edge(-1, -1, 0);
            }
            key[0] = 0;
            PriorityQueue<Edge> pq = new PriorityQueue<>(V, Comparator.comparingInt(o -> o.weight));
            pq.offer(new Edge(0, 0, 0));
            while (!pq.isEmpty()) {
                int u = pq.poll().src;
                mst[u] = true;
                for (Edge edge : adjacencylist[u]) {
                    if (!mst[edge.dest] && edge.weight < key[edge.dest]) {
                        key[edge.dest] = edge.weight;
                        e[edge.dest] = edge;
                        pq.offer(new Edge(edge.dest, 0, key[edge.dest]));
                    }
                }
            }
            for (int i = 1; i < V; i++) {
                System.out.println("Edge: " + e[i].src + " - " + e[i].dest + " weight: " + e[i].weight);
            }
        }
    }
}
