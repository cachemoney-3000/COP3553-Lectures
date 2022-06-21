package PartTwo.MasterTheorem;

import java.util.Arrays;

public class FibMemoization {
    int[] memoize;
    // Initialize the memoization array
    public FibMemoization (int n) {
        memoize = new int[n];
        Arrays.fill(memoize, -1);
    }

    public static void main(String[] args) {
        FibMemoization fib = new FibMemoization(6);
        System.out.println("5th Fibonacci is ---> " + fib.CalculateFibonacci(5));

        fib = new FibMemoization(7);
        System.out.println("6th Fibonacci is ---> " + fib.CalculateFibonacci(6));

        fib = new FibMemoization(8);
        System.out.println("7th Fibonacci is ---> " + fib.CalculateFibonacci(7));

        System.out.println(fibonacci(5));
        System.out.println(fibTab(5));
        System.out.println(calcFib(5));
    }

    public int CalculateFibonacci(int n) {
        // fib(0) = 0 and fib(1) = 1 -> BASE CASE
        if (n < 2)
            return n;
        // If we already find the fib of that specific number, return what we already have from the array
        if (memoize[n] != -1)
            return memoize[n];
        // Recurse to find the fib of that number
        memoize[n] = CalculateFibonacci(n - 2) + CalculateFibonacci(n - 1);
        return memoize[n];
    }

    // Recursion only (up-bottom) O(2^n)
    public static int fibonacci (int x) {
        if (x < 2) return x;
        return fibonacci(x - 1) + fibonacci(x - 2);
    }

    // Recursion and memoization O(n + 1)
    public static int calcFib (int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return fibRec(mem, n);
    }

    public static int fibRec (int[] memoize, int n) {
        if (n < 2) return n;
        if (memoize[n] != -1) return memoize[n];

        memoize[n] = fibRec(memoize, n -2) + fibRec(memoize, n - 1);
        return memoize[n];
    }


    // Using tabulation (Bottom-up) O(n)
    public static int fibTab(int n) {
        int[] tab = new int[n + 1];
        tab[0] = 0;
        tab[1] = 1;

        for (int i = 2; i <= n; i++) {
            tab[i] = tab[i - 1] + tab[i - 2];
        }
        return tab[n];
    }

}
