package PartOne.L4;

import java.util.Scanner;

public class DisjointSets {
    public static Pair[] parents;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("How many disjoints sets? ");
        int n = scanner.nextInt();

        // Create a new object in a form of array to put our disjoint set
        parents = new Pair[n];
        for(int i = 0; i < n; i++){
            // Set the ID to itself and set the height to 0
            parents[i] = new Pair(i, 0);
        }

        DisjointSets mySet = new DisjointSets();

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

    boolean union(int item1, int item2) {
        // Find the root of the 2 items
        int root1 = find(item1);
        int root2 = find(item2);

        // This means that the two items are already in the same set, no need to perform union
        if(root1 == root2){
            return false;
        }

        // Attach the smaller tree into the bigger tree
        if(parents[root1].getHeight() > parents[root2].getHeight()){
            parents[root2].setID(root1);
        }
        else if(parents[root2].getHeight() > parents[root1].getHeight()){
            parents[root1].setID(root1);
        }
        // If they both have the same height, then attach the larger index tree into the smaller index tree
        else{
            parents[root2].setID(root1);
            // Then increase their height
            parents[root1].increaseHeight();
        }
        return true;
    }

    private int find(int ID){
        int itemID = 0;

        // Keep looping until we find the root/parent of an item
        while(itemID != parents[ID].getID()){
            itemID = parents[ID].getID();
        }

        return itemID;
    }

    public String toString(){
        String ans = "";
        for(int i = 0; i < parents.length; i++){
            ans = ans + "(" + i + ", " + parents[i].getID() + ") ";
        }
        return ans;
    }
}

// An object that will store our disjoint sets and keeps track of the changes
class Pair {
    private int ID;
    private int height;

    Pair(int myID, int myHeight){
        ID = myID;
        height = myHeight;
    }

    public int getHeight() {
        return height;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void increaseHeight() {
        height++;
    }
}
