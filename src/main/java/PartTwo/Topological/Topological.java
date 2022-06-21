package PartTwo.Topological;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Topological {
    private int V;  // Stores the number of vertices
    private ArrayList<ArrayList<Integer>> adj;  // Adjacency list

    Topological (int v) {
        V = v;
        adj = new ArrayList<>(v);
        // Initialize the adjacency list
        for (int i = 0; i < v; i++)
            adj.add(new ArrayList<>());
    }

    public static void main(String[] args) {
        Topological t = new Topological(6);
        t.addEdge(5, 2);
        t.addEdge(5, 0);
        t.addEdge(4, 0);
        t.addEdge(4, 1);
        t.addEdge(2, 3);
        t.addEdge(3, 1);

        System.out.println("Following is a Topological sort of the given graph");
        t.topologicalSort();
    }

    // Add edge into the graph
    private void addEdge (int v, int w){
        adj.get(v).add(w);
    }

    private void topologicalSort(){
        // Mark all the vertices as not visited (set to false)
        boolean[] visited = new boolean[V];
        // Store the sorting here
        int[] ordering = new int[V];
        // Index for ordering array (start inserting the order from the last index)
        int t_listIndex = V - 1;

        // For each unvisited vertex, call the dfs function
        for (int i = 0; i < V; i++) {
            if (!visited[i])
                // t_listIndex will track the next available index in ordering array
                t_listIndex  = topologicalSortUtil(i, visited, t_listIndex, ordering);
        }

        // Print the final ordering
        for (int j = 0; j < V; j++)
            System.out.print(ordering[j] + " ");
    }

    private int topologicalSortUtil(int i, boolean[] visited, int t_listIndex, int[] ordering) {
        visited[i] = true;  // Mark the current node as visited
        Integer E;  // Stores the adjacent node connected to the current one

        // Recur for all the vertices adjacent to the current vertex
        Iterator<Integer> iterator = adj.get(i).iterator();
        while (iterator.hasNext()){
            E = iterator.next();
            if (!visited[E])
                t_listIndex = topologicalSortUtil(E, visited, t_listIndex, ordering);
        }

        // Store it to the ordering array
        ordering[t_listIndex] = i;

        return t_listIndex - 1;
    }
}
