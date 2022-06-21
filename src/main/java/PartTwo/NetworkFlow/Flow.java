package PartTwo.NetworkFlow;

import java.util.LinkedList;
import java.util.Queue;

public class Flow {
    public static void main(String[] args) {
        int vertices = 6;
        // Adjacency matrix with 6 vertices, here stored the weight of each edge
        int[][] graph = {{0, 10, 8, 0, 0, 0},
                        {0, 0, 5, 5, 0, 0},
                        {0, 4, 0, 0, 10, 0},
                        {0, 0, 9, 0, 10, 3},
                        {0, 0, 0, 6, 0, 14},
                        {0, 0, 0, 0, 0, 0}};

        Graph g = new Graph(vertices, graph);
        int source = 0;
        int destination = 5;
        int max_flow = g.findMaxFlow(source, destination);

        System.out.println("Maximum flow from source: " + source + " to destination: " + destination
                        + " is " + max_flow);
    }
}

class Graph {
    int vertices;
    int[][] graph;
    public Graph (int vertices, int[][] graph) {
        this.vertices = vertices;
        this.graph = graph;
    }

    public int findMaxFlow (int source, int sink) {
        int[][] residualGraph = new int[vertices][vertices];

        // Initialize residual graph same as the original graph
        for (int i = 0; i < vertices; i++){
            System.arraycopy(graph[i], 0, residualGraph[i], 0, vertices);
        }

        // Initialize parent to store the path from source to sink
        int[] parent = new int[vertices];

        int max_flow = 0;   // Initialize max flow

        // Keep looping if we can still find a path
        while (isPathExist_BFS(residualGraph, source, sink, parent)) {
            int flow_capacity = Integer.MAX_VALUE;
            int t = sink;

            while (t != source) {
                int s = parent[t];
                flow_capacity = Math.min(flow_capacity, residualGraph[s][t]);
                t = s;
            }

            // Update the residual graph as well as the fwd edge and back edge flow capacity
            t = sink;
            while (t != source) {
                int s = parent[t];
                // Forward edge
                residualGraph[s][t] -= flow_capacity;
                // Backward edge
                residualGraph[t][s] += flow_capacity;
                t = s;
            }

            // Update the max flow capacity
            max_flow += flow_capacity;
        }

        return max_flow;
    }

    public boolean isPathExist_BFS (int[][] residualGraph, int source, int sink, int[] parent) {
        boolean pathFound = false;

        // Tracks the visited vertices
        boolean[] visited = new boolean[vertices];
        // Queue for the BFS
        Queue<Integer> queue = new LinkedList<>();

        // Start finding a path from the source
        queue.offer(source);
        parent[source] = -1;
        visited[source] = true;

        while (!queue.isEmpty()) {
            // Go to the next vertex
            int u = queue.poll();
            // Visit to its adjacent vertices
            for (int v = 0; v < vertices; v++) {
                // Check if the vertex is not yet visited and has a forward edge of > 0
                if (!visited[v] && residualGraph[u][v] > 0) {
                    queue.offer(v);
                    visited[v] = true;
                    parent[v] = u;
                }
            }
        }
        // Checks if the destination was reached
        pathFound = visited[sink];
        return pathFound;

    }
}
