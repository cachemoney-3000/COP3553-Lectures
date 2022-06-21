package PartOne.L10.SkipList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class SkipList {

    // First and last value
    final public static int NEG_INF = Integer.MIN_VALUE;
    final public static int POS_INF = Integer.MAX_VALUE;

    public static Random rndObj = new Random();

    private ArrayList<Node> levels;
    private int size;

    // Initialize an empty skip list
    public SkipList() {
        levels = new ArrayList<>();
        levels.add(buildLevel(0));
        size = 1;
    }

    // Build a level using ID (LEVEL)
    private Node buildLevel(int id) {
        Node first = new Node(NEG_INF, id);
        Node last = new Node(POS_INF, id);
        first.next = last;
        last.prev = first;

        return first;
    }

    public ArrayList<Node> search(int value) {
        // Store answers here, where we travelled to find the value we are looking for
        ArrayList <Node> result = new ArrayList<>();

        // Size = height of the skip list
        Node cur = levels.get(size - 1);

        // Start searching from the top level
        for(int i = size - 1; i >= 0; i--) {
            // Go down this level until we're right BEFORE an equal or bigger item
            while(cur.next.data < value)
                cur = cur.next;

            // This is the floor of the value on the list so add it.
            result.add(cur);

            // Go down a level
            if (i > 0)
                cur = cur.down;
        }

        Collections.reverse(result);
        return result;
    }

    public boolean insert(int value) {
        // Find all the "previous" nodes
        ArrayList<Node> beforeList = search(value);

        // The value we want to add already exist
        if (beforeList.get(0).next.data == value)
            return true;

        // Temporary node
        Node tempNode = null;
        int i = 0;

        while (i <= size) {
            // Generate a random number to know if we are going to increase the level or no
            int val = i == 0 ? 1 : rndObj.nextInt(2);

            // If it gave us zero, then stop increasing the level
            if (val == 0)
                break;

            // We decided that we are increasing the level, so create a new node
            Node newNode = new Node(value, i);

            // Not necessary if it is a bottom level
            if (i > 0) {
                tempNode.up = newNode;
                newNode.down = tempNode;
            }

            // Special case if we are adding a new level to our list
            // We add the level and then connect it to the rest of the lists
            if (i == size) {
                Node nextLevel = buildLevel(size);
                levels.add(nextLevel);
                connectLastLevel();
                beforeList.add(nextLevel);
            }

            // Patching: joining the new node to the prev and next
            Node tempLow = beforeList.get(i);
            Node tempNext = tempLow.next;
            newNode.prev = tempLow;
            newNode.next = tempNext;
            tempLow.next = newNode;
            tempNext.prev = newNode;

            // Need to update te object's size and get out
            if (i == size) {
                size++;
                break;
            }

            // Go up next level
            i++;
            tempNode = newNode;
        }

        // We inserted an item successfully
        return true;
    }

    private void connectLastLevel() {
        // We can obtain both of these
        Node top = levels.get(levels.size() - 1);
        Node below = levels.get(levels.size() - 2);

        // Link left sides up and down
        top.down = below;
        below.up = top;

        // End of top list
        top = top.next;

        // Go to end of second to top list
        while (below.data != POS_INF)
            below = below.next;

        top.down = below;
        below.up = top;
    }

    // Returns the number of items on the top level
    private int topLevelSize() {
        Node cur = levels.get(size - 1);
        int sz = 0;
        while (cur != null) {
            cur = cur.next;
            sz++;
        }

        return sz;
    }

    public boolean delete(int value) {
        // Find for the value we are going to delete
        ArrayList<Node> beforeList = search(value);

        // Get the first node in the beforeList
        Node bottom = beforeList.get(0);

        // Check if the value exist in the skip list. If not return false
        if (bottom.next.data != value)
            return false;

        // Delete the node
        Node tempNode = bottom.next;

        while (tempNode != null) {
            // Update the pointers to signify deletion
            Node prevNode = tempNode.prev;
            Node nextNode = tempNode.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;

            // What's next to delete
            tempNode = tempNode.up;
        }

        // Special case where the node to be deleted is the only remaining node in the top level
        if(size > 1 && topLevelSize() == 2){
            levels.remove(size - 1);
            size--;
        }

        // Deletion successful
        return true;
    }

    // Print all the levels in the skip list
    public void printAllLevels(){
        System.out.println(levels.size() + " and " + size);
        for(int i = 0; i < size; i++) {
            System.out.println("Level " + i + ": ");
            printLevel(i);
        }
        System.out.println("----------------------------------");
    }

    // Print one level, must specify the height
    public void printLevel(int id){
        Node cur = levels.get(id);
        while (cur != null) {
            System.out.println(cur.data + " ");
            cur = cur.next;
        }
        System.out.println();
    }

    public static void basicInsertTest() {
        SkipList main = new SkipList();

        for (int i = 0; i < 10; i++) {
            // Generate a random item
            int item = rndObj.nextInt(1000);
            System.out.println("Generated: " + item);

            // Insert that item
            boolean flag = main.insert(item);

            // Prints if the item was inserted or not
            if(flag)
                System.out.println("Inserted " + item);
            else
                System.out.println("Rejected " + item);

            // Print the current list
            main.printAllLevels();;
        }

        // Deletion
        Scanner scanner = new Scanner(System.in);
        int item = 0;
        while (item != -1){
            System.out.println("Enter an item to delete: ");
            item = scanner.nextInt();

            boolean flag = main.delete(item);
            if (flag)
                System.out.println("Deleted " + item);
            else
                System.out.println(item + " can't be deleted");
        }
    }


    public static void main(String[] args) {

    }
}

class Node {
    public int data;
    public Node next;
    public Node prev;
    public Node up;
    public Node down;
    public int level;

    public Node (int myVal, int myLevel){
        data = myVal;
        level = myLevel;
        next = null;
        prev = null;
        up = null;
        down = null;
    }
}
