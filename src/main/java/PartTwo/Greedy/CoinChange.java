package PartTwo.Greedy;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class CoinChange {
    // List all the possible denomination (converted into cents x10^-2)
    public static int[] denomination = {1, 5, 10, 25, 50, 100, 200, 500, 1000, 2000, 5000, 10000};
    public static int n = denomination.length;

    public static void main(String[] args) {
        // Example, find the least bills for $6.39
        double money = 6.35;
        int money_int = (int) (money * 100);

        System.out.println("Minimal number of change for $" + money + ": ");
        findMin(money_int);
    }

    private static void findMin(int money) {
        ArrayList<Integer> result = new ArrayList<>();
        // Loop through the denomination array
        for (int i = n - 1; i >= 0; i--){
            // Keep finding the optimal values
            while (money >= denomination[i]){
                money = money - denomination[i];
                // Add it to the result list
                result.add(denomination[i]);
            }

            // Breaks out immediately if money becomes zero
            if (money == 0)
                break;
        }

        // Printing the results
        for (int i = 0; i < result.size(); i++){
            int x = result.get(i);

            if (x >= 100)
                System.out.print("$" + x / 100 + ", ");
            else
                System.out.print("¢" + x + ", ");
        }
    }
}
