package PartTwo.Dijkstra;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        // Initialize the edges for the graph
        List<Edge> edges = Arrays.asList(
                new Edge(1,2, 1),
                new Edge(2, 3, 2),
                new Edge(3, 4, 3),
                new Edge(4, 5, 4),
                new Edge(5, 6, 5),
                new Edge(7, 8, 7),
                new Edge(8, 9, 8),
                new Edge(0, 1, 1)
        );

        // Total number of nodes in the graph (0 - 4)
        int n = 10;
        // Construct a graph
        Graph graph = new Graph(edges, n);

        // Run the Dijkstra algo for every node to find the shortest path from
        // the source to all the vertices
        for (int source = 0; source < n; source++) {
            //findShortestPath(graph, source, n);
        }
        findShortestPath(graph, 4, n);
    }

    private static void findShortestPath(Graph graph, int source, int n) {
        // Create a min heap which makes the shortest distance as the first item
        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        // Set the initial distance from source to 'v' (last node) as infinity
        List<Integer> dist = new ArrayList<>(Collections.nCopies(n, Integer.MAX_VALUE));
        // Tracks if a node is visited or not
        boolean[] visited = new boolean[n];
        // Stores the predecessors for each node
        int[] prev = new int[n];

        minHeap.add(new Node(source, 0));
        dist.set(source, 0);
        prev[source] = -1;
        visited[source] = true;

        while (!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            int u = node.vertex;
            int w = node.weight;

            for (Edge edge : graph.adjacencyList.get(u)) {
                int v = edge.dest;
                int weight = edge.weight;
                int newDistance = dist.get(u) + weight;

                if (!visited[v] && newDistance < dist.get(v)) {
                    dist.set(v, newDistance);
                    prev[v] = u;

                    minHeap.add(new Node (v, newDistance));
                }
            }
            visited[u] = true;
        }

        // Printing the path
        List<Integer> route = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (i != source && dist.get(i) != Integer.MAX_VALUE) {
                getRoute(prev, i, route);
                System.out.printf("Path (%d --> %d): Minimum cost = %d, Route = %s\n",
                        source, i, dist.get(i), route);

                route.clear();
            }
        }
    }

    private static void getRoute(int[] prev, int i, List<Integer> route) {
        if (i >= 0){
            getRoute(prev, prev[i], route);
            route.add(i);
        }
    }
}

class Edge {
    int source;
    int dest;
    int weight;

    public Edge (int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

class Node {
    int vertex;
    int weight;

    public Node (int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}

class Graph {
    List<List<Edge>> adjacencyList = null;

    Graph (List<Edge> edges, int n) {
        adjacencyList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (Edge edge: edges) {
            adjacencyList.get(edge.source).add(edge);
        }
    }
}
