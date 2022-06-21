package PartTwo.BFS_DFS;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BFSDFS {
    private int v; // Number of vertices
    private LinkedList<Integer> adj[]; // Stores the edges, adjacency lists

    BFSDFS(int vertex){
        v = vertex;
        // Maintains the list for each vertex
        adj = new LinkedList[vertex];
        for (int i = 0; i < vertex; i++)
            adj[i] = new LinkedList<>();
    }

    void addEdge(int v, int w){
        // Link the two vertex (this only works for undirected graph)
        adj[v].add(w);
        adj[w].add(v);
    }

    // Add to queue
    // Dequeue
    // Print

    // Go to the adjacent node
        // if that node is not yet visited, mark it as visited and add to queue
    // Repeat steps
    void BFS(int s){
        // Tracks if a vertex is visited
        boolean[] visited = new boolean[v];
        // Queue that will tracks who to visit next
        LinkedList<Integer> queue = new LinkedList<>();

        // Mark the first/starting vertex as visited
        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0){
            // Dequeue the vertex in front of the queue then print it
            s = queue.poll();
            System.out.print(s + " ");

            // Get all the adjacent vertices of the dequeued vertex s
            Iterator<Integer> i = adj[s].listIterator();
            while (i.hasNext()){
                int n = i.next();
                // If the adjacent vertex is not visited, mark it as visited and enqueue it
                if (!visited[n]){
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    void DFS(){
        // Tracks if a vertex is visited
        boolean[] visited = new boolean[v];

        // Traverse each vertex
        for (int i = 0; i < v; i++){
            // If the vertex is not visited yet, call the helper function
            if(!visited[i])
                DFSUtil(i, visited);
        }
    }

    void DFSUtil(int x, boolean[] visited){
        visited[x] = true;
        System.out.println(x);

        Iterator<Integer> it = adj[x].listIterator();
        while (it.hasNext()) {
            int v = it.next();
            if (!visited[v])
                DFSUtil(x, visited);
        }
    }

    public static void main(String[] args) {
        BFSDFS graph = new BFSDFS(7);

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        System.out.println("BFS starting from vertex 0)");

        graph.BFS(0); //starting from zero. If you have a disconnected graph, call BFS for each unvisited vertices using a loop

        System.out.println("\nDFS starting from vertex 0)");

        graph.DFS();

    }
}
