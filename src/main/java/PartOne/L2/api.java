package PartOne.L2;

import java.util.*;

public class api {
    public static void main(String[] args) {
        //tree();
        //hash();
        //copy();
        //pq();

        lia();;

        //int[][] n = new int[3][5];
        //System.out.println(Arrays.deepToString(n));
    }

    public static void arrays(){
        System.out.println("ARRAYS PRACTICE");
        // The array called "arr" can hold 10 items, initialized to 0
        int[] arr = new int[10];
        // Fill the array with the value of 10
        Arrays.fill(arr, 10);
        System.out.println(Arrays.toString(arr));

        // The array called "arr2" can hold 5 items, initialized to 0
        int[] arr2 = new int[5];
        // Fill the arr2 from the index 2 up to index 4 (exclusive) with the value 50
        Arrays.fill(arr2, 2, 4, 50);
        System.out.println(Arrays.toString(arr2));

        // Copy the values from the "arr" to "arr2" and set the new length to 10
        arr2 = Arrays.copyOf(arr, 20);
        System.out.println(Arrays.toString(arr2));

        // Will copy the values from the range(index) 4 to 8 (exclusive) then set the new length to how many items it copied
        arr = Arrays.copyOfRange(arr2, 4, 8);
        System.out.println(Arrays.toString(arr));

        // Sorting takes O(nlogn) runtime, n = number of items in the array=
        Integer[] e = {150, 50, 10, 39, 78, 2, 9};

        // By default, the arrays will be sorted in increasing order
        // For primitive types like int, the sorting order can't be change (always increasing) so we use "Integer"
        System.out.println("Array sorting: (increasing)");
        Arrays.sort(e);
        System.out.println(Arrays.toString(e));
        // With the use of collections reverse order, it will sort decreasing
        System.out.println("Array sorting: (decreasing, w/reverseOrder)");
        Arrays.sort(e, Collections.reverseOrder());
        System.out.println(Arrays.toString(e));
    }

    public static void tree(){
        TreeSet<Integer> ts = new TreeSet<>();

        for (int i = 0; i < 5; i++)
        {
            ts.add(i*2);
        }

        // If an item already exist in the tree, it will just ignore it when adding
        ts.add(2);
        ts.add(4);
        ts.add(8);
        System.out.println(ts);
    }

    public static void hash(){
        // In hash set, it will just ignore a duplicate item when adding
        HashSet<String> ts1 = new HashSet();
        HashSet<String> ts2 = new HashSet();
        ts1.add("One");
        ts1.add("One");
        ts1.add("Two");
        ts1.add("Three");
        ts1.add("Four");

        ts2.add("One");
        ts2.add("Two");
        ts2.add("Five");

        // This will return false since ts1 doesn't have "Five"
        System.out.println(ts1.containsAll(ts2));
    }

    public static void copy(){
        int[] list1 = {1, 2, 3, 4};
        int[] list2 = Arrays.copyOf(list1, 6);
        list2[4] = 3;
        System.out.println(Arrays.toString(list2));
    }

    public static void pq(){
        // Min heap by nature
        PriorityQueue<Integer> numbers = new PriorityQueue<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(6);
        numbers.offer(1);
        numbers.add(1);
        numbers.add(6);

        int len = numbers.size();
        for (int i = 0; i < len ; i++)
        {
            System.out.print(numbers.poll() + " ");
        }
    }

    public static void lia(){
        ArrayList<Integer> p = new ArrayList<>();
        p.add(50);
        p.add(20);
        p.add(100);
        p.add(1);
        p.add(99);
        p.add((23));

        System.out.println(p.toString());

    }
}
