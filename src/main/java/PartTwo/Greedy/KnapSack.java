package PartTwo.Greedy;

import java.util.Arrays;

public class KnapSack {
    public static void main(String[] args) {
        // Just an example
        int[] weight = {10, 40, 20, 30};
        int[] profit = {60, 40, 100, 120};
        int bagCapacity = 50;

        // Calculate the max value with max profit
        double maxValue = getMaxProfit(weight, profit, bagCapacity);
        System.out.println("Maximum value we can obtain = " + maxValue);
    }

    public static double getMaxProfit(int[] weight, int[] profit, int capacity) {
        itemValue[] item = new itemValue[weight.length];

        // Initialize the array by inserting all the data
        for (int i = 0; i < weight.length; i++) {
            itemValue x = new itemValue();
            x.profit = profit[i];
            x.weight = weight[i];
            x.ratio = (double) (profit[i] / weight[i]);
            x.ID = i;

            item[i] = x;
        }

        // sorting items by ratio, putting the highest profit ratio first;
        Arrays.sort(item, (item1, item2) -> item2.ratio.compareTo(item1.ratio));

        int counter = 0;
        double total = 0D;

        while (true) {
            double cur_profit = item[counter].profit;
            double cur_weight = item[counter].weight;

            // Put the whole item into the bag, since it can fit
            if (cur_weight <= capacity) {
                capacity = (int) (capacity - cur_weight);
                total = total + cur_profit;
            }

            // If the item can't fit, get the fraction of that item to be put into the bag
            else {
                double fraction = capacity / cur_weight;
                // Get the fractional profit of that item
                total = total + (fraction * cur_profit);
                break;
            }
            // Go to the next item
            counter++;
        }

        return total;
    }
}

class itemValue {
    Double ratio;
    double weight;
    double profit;
    double ID;
}


