package PartTwo.MST.Kruskal;

import java.util.ArrayList;
import java.util.Arrays;

public class Kruskal {
    Edge_K edge[];
    int V;
    int E;

    // Creates a graph with V vertices and E edges
    Kruskal (int V, int E) {
        this.V = V;
        this.E = E;

        edge = new Edge_K[E];
        for (int i = 0; i < E; i++)
            edge[i] = new Edge_K();
    }

    public static void main(String[] args) {
        int V = 4;  // Number of vertices in the graph
        int E = 5;  // Number of edges in graph

        Kruskal graph = new Kruskal(V, E);
        graph = graph.add_edge(graph);

        ArrayList<Edge_K> result = graph.kruskal_MST();
        graph.print_result(result);
    }

    public ArrayList<Edge_K> kruskal_MST(){
        // To store the result in this list
        ArrayList<Edge_K> result = new ArrayList<>();
        // Step 1: Sort the edges in increasing order based on their weight
        Arrays.sort(edge);

        DisjointSet set = new DisjointSet(V);
        // Iterate over all the edges
        for (int i = 0; i < V; i++) {
            // Step 2: pick the smallest edge first
            int x = set.find(edge[i].src);
            int y = set.find(edge[i].dest);

            // Step 3: If including this edge doesn't case a cycle
            // Add it into the results list and perform union
            if (x != y) {
                set.union(x, y);
                result.add(edge[i]);
            }
        }

        return result;
    }

    public void print_result(ArrayList<Edge_K> result) {
        System.out.println("Following are the edges in the constructed MST");
        int minimumCost = 0;

        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i).src + " -- " + result.get(i).dest + " -- " + result.get(i).weight);
            // Add the total cost for the MST
            minimumCost += result.get(i).weight;
        }
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);
    }

    public Kruskal add_edge(Kruskal graph) {
        /* Let us create following weighted graph
				10
			0--------1
			| \	     |
		   6| 5\     |15
			|	 \   |
			2--------3
				4	 */
        // add edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = 10;

        // add edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 6;

        // add edge 0-3
        graph.edge[2].src = 0;
        graph.edge[2].dest = 3;
        graph.edge[2].weight = 5;

        // add edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 15;

        // add edge 2-3
        graph.edge[4].src = 2;
        graph.edge[4].dest = 3;
        graph.edge[4].weight = 4;

        return graph;
    }
}

// For the disjoint set
class Pair {
    private int ID;
    private int height;

    Pair (int ID, int height){
        this.ID = ID;
        this.height = height;
    }

    public int getID() {
        return ID;
    }

    public int getHeight() {
        return height;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void incHeight() {
        height++;
    }

    public void decHeight() {
        height--;
    }
}

class DisjointSet {
    private Pair[] parents;
    // Create the initial state of a disjoint set of n elements
    DisjointSet (int n) {
        // All nodes start as a leaf node, with a height of 0
        parents = new Pair[n];
        for (int i = 0; i < n; i++)
            parents[i] = new Pair(i, 0);
    }

    public int find (int id) {
        // I am the root of the tree, return me
        if (id == parents[id].getID())
            return id;
        // Find the root of this tree
        int res = find(parents[id].getID());

        // Collapse find
        if (res != parents[id].getID()) {
            parents[id].setID(res); // Attach the node directly to its parent
            parents[res].decHeight();   // Decrease the height of the parent tree
        }
        return res;
    }

    public boolean union (int u, int v) {
        // Find both of their parents
        int root1 = find(u);
        int root2 = find(v);

        // No union needed, they are in the same set
        if (root1 == root2)
            return false;

        // Attach root 2 to root 1 since root 2 is smaller tree
        if (parents[root1].getHeight() > parents[root2].getHeight())
            parents[root2].setID(root1);
        // Attach root 1 to root 2 since root 1 is smaller tree
        else if (parents[root2].getHeight() > parents[root1].getHeight())
            parents[root1].setID(root2);
        // If they have the same height, attach the tree to smaller number node
        else {
            parents[root2].setID(root1);
            parents[root1].incHeight();
        }
        // Successful union
        return true;
    }

    @Override
    public String toString() {
        String ans = "";
        for (int i=0; i<parents.length; i++) {
            if (i == parents[i].getID()) //print the height if the node is root
                ans = ans + "(" + i + ", " + parents[i].getID() + ":" + parents[i].getHeight()+") ";
            else
                ans = ans + "(" + i + ", " + parents[i].getID() + ") ";
        }
        return ans;
    }
}

class Edge_K implements Comparable <Edge_K> {
    int src;
    int dest;
    int weight;

    // Comparator function used for sorting edges based on their weight
    @Override
    public int compareTo(Edge_K compareEdge) {
        return this.weight - compareEdge.weight;
    }
}
