package PartOne.L4;

import java.util.Scanner;

public class DisjointSetsPathCompression {
    public static Pair_PathComp[] parents;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many disjoints sets? ");
        int n = scanner.nextInt();

        // Create a new object in a form of array to put our disjoint set
        parents = new Pair_PathComp[n];
        for(int i = 0; i < n; i++){
            // Set the ID to itself and set the height to 0
            parents[i] = new Pair_PathComp(i, 0);
        }

        DisjointSetsPathCompression mySet = new DisjointSetsPathCompression();

        while(true){
            // Asks the user which set to join together
            System.out.print("Select 2 items to union: (from 0 to " + (n - 1) + "): ");
            int item1 = scanner.nextInt();
            int item2 = scanner.nextInt();

            // Union the 2 sets
            boolean success = mySet.union(item1, item2);
            if(success) {
                System.out.println("Here is your sets: " + mySet);
            }
            else {
                System.out.println("Union failed");
            }

            // Asks user if they want to exit or continue
            System.out.print("Do you want to continue (0 = No, 1 = Yes)? ");
            int cont = scanner.nextInt();
            if(cont == 0)
                break;
        }
    }

    private boolean union(int item1, int item2) {
        int root1 = find(item1);
        int root2 = find(item2);

        // If the two items have the same root, ignore
        if(root1 == root2){
            return false;
        }

        // Attach the smaller tree into the bigger tree
        if(parents[root1].getHeight() > parents[root2].getHeight())
            parents[root2].setID(root1);

        else if(parents[root2].getHeight() > parents[root1].getHeight())
            parents[root1].setID(root2);
        // If they have the same size
        else{
            // Attach the tree with bigger index into the bigger one
            parents[root2].setID(root1);
            // Increase the height of the root
            parents[root1].increaseHeight();
        }
        return true;
    }

    private int find(int ID){
        // The item is already the root
        if(ID == parents[ID].getID())
            return ID;

        // Find the item parents root
        int result = find(parents[ID].getID());

        // If the result is not the existing parent, make it the parent
        if(result != parents[ID].getID()){
            // Attach that item directly into the root
            parents[ID].setID(result);
            // Decrease the height of the root
            parents[result].decreaseHeight();
        }

        return result;
    }

    public String toString(){
        String ans = "";
        for(int i = 0; i < parents.length; i++){
            ans = ans + "(" + i + ", " + parents[i].getID() + ") ";
        }
        return ans;
    }

    boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}

class Pair_PathComp{
    private int height;
    private int ID;

    Pair_PathComp(int myID, int myHeight){
        height = myHeight;
        ID = myID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHeight() {
        return height;
    }

    public void increaseHeight() {
        height++;
    }

    public void decreaseHeight() {
        height--;
    }
}
