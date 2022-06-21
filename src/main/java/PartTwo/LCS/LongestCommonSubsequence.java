package PartTwo.LCS;

import java.util.Arrays;

public class LongestCommonSubsequence {
    public static void main(String[] args) {
        String A = "GXGGXTXAYB\0";
        String B = "ABCD\0";
        char[] A_char = A.toCharArray();
        char[] B_char = B.toCharArray();

        System.out.println(LCS(A_char, B_char, 0, 0));
        System.out.println(countLCS(A_char, B_char));
    }

    // LCS with recursion only
    public static int LCS (char[] A, char[] B, int i, int j) {
        if (A[i] == '\0' || B[j] == '\0')
            return 0;

        if (A[i] == B[j])
            return 1 + LCS(A, B, i + 1, j + 1);

        // Returns the longest subsequence
        return Math.max(LCS(A, B, i + 1, j), LCS(A, B, i, j + 1));
    }

    // LCS with memoization
    public static int countLCS(char[] A, char[] B) {
        int[][] memoize = new int[A.length][B.length];

        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B.length; j++)
                memoize[i][j] = -1;

        // Returns the longest subsequence
        return LCSRec(memoize, A, B, 0, 0) - 1;
    }

    // LCS with memoization
    public static int LCSRec (int[][] memo, char[] A, char[] B, int i, int j) {
        if (i == A.length || j == B.length)
            return 0;

        if (A[i] == B[j]) {
            if (memo[i][j] != -1)
                return memo[i][j];

            memo[i][j] = 1 + LCSRec(memo, A, B, i + 1, j + 1);

            return memo[i][j];
        }

        return Math.max(LCSRec(memo, A, B, i + 1, j), LCSRec(memo, A, B, i, j + 1));
    }
}
