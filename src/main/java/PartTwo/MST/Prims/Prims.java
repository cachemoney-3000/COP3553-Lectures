package PartTwo.MST.Prims;

public class Prims {
    // Number of vertices in the graph
    private static final int V = 5;

    public static void main(String[] args) {
        Prims tree = new Prims();
        // Adjacency matrix
        int[][] graph = {
                { 0, 2, 0, 6, 0 },
                { 2, 0, 3, 8, 5 },
                { 0, 3, 0, 0, 7 },
                { 6, 8, 0, 0, 9 },
                { 0, 5, 7, 9, 0 } };

        tree.MST(graph);
    }

    public void MST (int[][] graph) {
        int[] parent = new int[V];  // Store the constructed MST
        int[] key = new int[V]; // Store the weight values for each node
        Boolean[] visited = new Boolean[V]; // Tracks if the node or vertex was already visited

        // Initialize all keys as INFINITE and unvisited
        for (int i = 0; i < V; i++) {
            key[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }
        // Start at this vertex
        key[0] = 0; // Mark the starting vertex as zero
        parent[0] = -1; // Picked as first vertex, root

        // Loop through all the vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the adjacent vertex that has the minimum weight/key
            int u = minKey(key, visited);
            // Add the chosen vertex to the visited list
            visited[u] = true;

            // Update the key value and the parent index of each adjacent vertex of the node we picked
            for (int vertex = 0; vertex < V; vertex++) {
                // Only update the vertex that is not in the MST list yet
                // Update if the vertex is non zeros only (adjacent from u)
                // Update the key only if that adjacent vertex has a smaller weight than the key[v]
                if (graph[u][vertex] != 0 && !visited[vertex] && graph[u][vertex] < key[vertex]) {
                    parent[vertex] = u;
                    key[vertex] = graph[u][vertex];
                }
            }
        }

        printMST(parent, graph);
    }

    // Finds the adjacent vertex with the minimum key value
    public int minKey(int[] key, Boolean[] visited) {
        // Initialize the min value
        int min = Integer.MAX_VALUE;
        int min_index = - 1;

        for (int i = 0; i < V; i++) {
            // The vertex must not in the visited list yet and must be less than infinity
            if (!visited[i] && key[i] < min) {
                min = key[i];
                min_index = i;
            }
        }
        // Return the index of the vertex that has the minimum key value
        return min_index;
    }

    // Prints the final result
    void printMST (int[] parent, int[][] graph) {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + "\t " + graph[i][parent[i]]);
    }
}
