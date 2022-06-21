package PartOne.L1.practice;

public class slmp {
    public static void main(String[] args) {
        int[] list1 = {10, 20, 30, 35, 40, 45, 50, 55, 60, 65};
        int[] list2 = {12, 15, 20, 25, 40, 50, 52, 60, 62, 70};

        linearSearch(list1, list2);
        binarySearch(list1, list2);
        twoTrackers(list1, list2);
    }

    // Time complexity of O(n * m)
    public static void linearSearch(int[] l1, int[] l2){
        // Loop through the first list
        for(int i = 0; i < l1.length; i++){
            // Then loop through the second list for each item in the first list
            for(int j = 0; j < l2.length; j++){
                // If an item from the first list matched an item from the second list
                if(l1[i] == l2[j]){
                    // Print the result
                    System.out.print(l1[i] + " ");
                    break;
                }
            }
        }
        System.out.println();
    }

    // Time complexity of O(logn) for 1 item, O(nlogn) if we repeat it n times for the first list
    public static void binarySearch(int[] l1, int[] l2){
        // Loop through the first list
        for(int i = 0; i < l1.length; i++){
            // Get each item from the list and store it to "val"
            int val = l1[i];
            // If we found a match, print it
            if(binSearchUtil(l2, val)){
                System.out.print(val + " ");
            }
        }
        System.out.println();
    }

    private static boolean binSearchUtil(int[] l2, int item) {
        // Get the leftmost index
        int left = 0;
        // Get the rightmost index
        int right = l2.length -1;
        int mid;

        // Loop until the left and right tracker doesn't cross
        while(left <= right){
            // Get the mid-index
            mid = (left + right) / 2;

            // If we found the item, return its value
            if(l2[mid] == item){
                return true;
            }

            // If the item we are looking for is less than the item found on the middle index
            else if(item < l2[mid]){
                // Update the right index tracker
                right = mid - 1;
            }

            // If the item we are looking for is greater than the item found on the middle index
            else{
                // Update the left index
                left = mid + 1;
            }
        }
        return false;
    }

    public static void twoTrackers(int[] l1, int[] l2){
        // Get the size of the first list
        int i = l1.length;
        // Get the size of the second list
        int j = l2.length;
        int iTracker = 0;
        int jTracker = 0;

        // Loop until the two trackers doesn't go out of bounds
        while(iTracker < i && jTracker < j){
            // Update the iTracker, since the value of the first list is less than the value on the second list
            if(l1[iTracker] < l2[jTracker]){
                iTracker++;
            }

            // Update the jTracker, since the value of the second list is less than the value on the first list
            else if(l1[iTracker] > l2[jTracker]){
                jTracker++;
            }

            // We found a match
            else{
                // Print the match
                System.out.print(l1[iTracker] + " ");
                // Update the 2 trackers
                iTracker++;
                jTracker++;
            }
        }

        System.out.println();
    }
}
