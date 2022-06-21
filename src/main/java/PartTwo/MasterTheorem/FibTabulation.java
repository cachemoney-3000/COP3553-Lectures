package PartTwo.MasterTheorem;

public class FibTabulation {
    public static void main(String[] args) {
        FibTabulation fib = new FibTabulation();
        System.out.println("5th Fibonacci is ---> " + fib.fibonacci(5));
        System.out.println("6th Fibonacci is ---> " + fib.fibonacci(6));
        System.out.println("7th Fibonacci is ---> " + fib.fibonacci(7));
    }

    public int fibonacci (int n) {
        if (n == 0) return 0;
        int[] tab = new int[n + 1];
        // Base
        tab[0] = 0;
        tab[1] = 1;

        for (int i = 2; i <= n; i++)
            tab[i] = tab[i - 2] + tab[i - 1];

        return tab[n];
    }
}
