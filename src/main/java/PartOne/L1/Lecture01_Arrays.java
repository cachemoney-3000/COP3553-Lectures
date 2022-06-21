package PartOne.L1;

// Linear searching for an item
public class Lecture01_Arrays {
    private static int countLinearSearch = 0;
    private static int countBinarySearch = 0;
    private static int countTwoTrackers = 0;

    public static void main(String[] args) {
        int[] list1 = {10, 20, 30, 35, 40, 45, 50, 55, 60, 65};
        int[] list2 = {12, 15, 20, 25, 40, 50, 52, 60, 62, 70};

        System.out.println("LINEAR SEARCH: ");
        MatchLinearSearch(list1, list2);
        System.out.println("\nSteps taken using linear search: " + countLinearSearch + "\n");

        System.out.println("BINARY SEARCH: ");
        MatchBinarySearch(list1, list2);
        System.out.println("\nSteps taken using binary search: " + countBinarySearch + "\n");

        System.out.println("TWO TRACKERS: ");
        twoTrackers(list1, list2);
        System.out.println("\nSteps taken using two trackers search: " + countTwoTrackers + "\n");
    }

    private static void MatchLinearSearch(int[] list1, int[] list2){
        for (int k : list1) {
            for (int i : list2) {
                countLinearSearch++;
                if (k == i) {
                    System.out.print(k + " ");
                    break;
                }
            }
        }
    }

    private static void MatchBinarySearch(int[] list1, int[] list2){
        for (int j : list1) {
            // If the item was found, it will return what index it was found
            if (binSearch(list2, j) != -1) {
                System.out.print(j + " ");
            }
        }
    }

    private static int binSearch(int[] list, int item) {
        int l = 0; // leftmost index
        int h = list.length - 1; // rightmost index
        int mid; // stores the middle index

        while(l <= h){
            countBinarySearch++;
            mid = (l + h) / 2; // calculates the middle index
            // Starts searching in the middle index
            // If the item we are looking for is in the middle index, return that index
            if(list[mid] == item){
                return mid;
            }

            // If the item we are looking for is greater than the value of the middle index
            // Update the value of the leftmost index to search on the right side half of the array
            else if(list[mid] < item){
                l = mid + 1;
            }

            // If the item we are looking for is smaller than the value of the middle index
            // Then we update the value of the rightmost index to look on the left side of the array
            else{
                h = mid - 1;
            }
        }

        // If we reach here, then the item was not found
        return -1;
    }

    private static void twoTrackers(int[] list1, int[] list2){
        int i = list1.length;
        int j = list2.length;
        int tracker01 = 0;
        int tracker02 = 0;

        while(tracker01 < i  && tracker02 < j){
            countTwoTrackers++;

            // If the item in the first array is smaller than the value of the second array
            // Update the tracker01
            if(list1[tracker01] < list2[tracker02]){
                tracker01++;
            }

            // If the item in the second array is smaller than the value of the first array
            // Update the tracker02
            else if(list2[tracker02] < list1[tracker01]){
                tracker02++;
            }

            // If we found matching numbers, print it, then update the tracker01 and tracker02
            else{
                System.out.print(list1[tracker01] + " ");
                tracker01++;
                tracker02++;
            }
        }
    }
}
