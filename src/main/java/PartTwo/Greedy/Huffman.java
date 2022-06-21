package PartTwo.Greedy;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Huffman {
    public static void main(String[] args) {
        // Example:
        int n = 6;  // Number of nodes
        char[] charArray = { 'a', 'b', 'c', 'd', 'e', 'f' };
        int[] charFreq = { 5, 9, 12, 13, 16, 45 };

        HuffmanNode root = new HuffmanNode();
        root = huffman(charArray, charFreq, n);

        printCode (root, "");
    }

    public static HuffmanNode huffman(char[] charArray, int[] charFreq, int n) {
        // Create a min heap that will sort the frequencies of each character in increasing order
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>(n, new MyComparator());
        // Initialize the queue by inserting all the nodes
        for (int i = 0; i < n; i++) {
            HuffmanNode item = new HuffmanNode();
            item.c = charArray[i];
            item.freq = charFreq[i];

            // Each item should point to null initially
            item.left = null;
            item.right = null;

            // Add each item to the min heap
            queue.add(item);
        }

        HuffmanNode root = null;
        // Stop until there is only 1 item (root) in the queue
        while (queue.size() > 1) {
            // Get the 2 minimum frequency nodes in the queue
            HuffmanNode x = queue.poll();
            HuffmanNode y = queue.poll();

            // Combine the 2 nodes into this combine node
            HuffmanNode combine = new HuffmanNode();
            combine.c = '*';
            combine.freq = x.freq + y.freq;
            // The least freq node is set on the left, then the other is set on the right
            combine.left = x;
            combine.right = y;

            // Set the combined node as the root and add them to queue
            root = combine;
            queue.add(root);
        }
        return root;
    }

    // Printing is preOrder method Root-Left-Right
    private static void printCode(HuffmanNode root, String s) {
        // If it's a leaf, print it
        if (root.left == null && root.right == null && Character.isLetter(root.c)){
            System.out.println(root.c + ": " + s);
            return;
        }

        // Recursive call from left and right subtree
        printCode(root.left, s + "0");  // Label the left subtree as 0
        printCode(root.right, s + "1"); // Label the right subtree as 1
    }
}

// Compare nodes based on the frequency of each letter
class MyComparator implements Comparator<HuffmanNode> {
    public int compare (HuffmanNode x, HuffmanNode y) {
        return x.freq - y.freq;
    }
}

// For connecting nodes into a tree
class HuffmanNode {
    // Information of each letters
    int freq;
    char c;
    // Pointers
    HuffmanNode left;
    HuffmanNode right;
}
