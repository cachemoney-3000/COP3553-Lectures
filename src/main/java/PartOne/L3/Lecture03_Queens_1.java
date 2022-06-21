package PartOne.L3;

import java.util.Scanner;

public class Lecture03_Queens_1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the size of the board (must be >= 4): ");
        int boardSize = scanner.nextInt();

        while (boardSize < 4){
            System.out.print("Entry must be >= 4. Try again: ");
            boardSize = scanner.nextInt();
        }

        Lecture03_Queens_1 nQueens = new Lecture03_Queens_1();
        nQueens.solveNQueens(boardSize);

    }

    private boolean solveNQueens(int boardSize){
        int[][] board = new int[boardSize][boardSize];

        for(int i = 0; i < boardSize; i++){
            for(int j = 0; j < boardSize; j ++){
                board[i][j] = 0;
            }
        }

        if(!solveNQueensUtil(board, 0, boardSize)){
            System.out.println("Solution does not exist");
            return false;
        }

        printSolution(board, boardSize);
        return true;
    }

    private boolean solveNQueensUtil(int[][] board, int col, int N){
        if(col >= N)
            return true;

        for(int row = 0; row < N; row++){
            if(isSafe(board, row, col, N)){
                board[row][col] = 1;

                if(solveNQueensUtil(board, col + 1, N))
                    return true;

                board[row][col] = 0;
            }
        }
        return false;
    }

    private boolean isSafe(int[][] board, int row, int col, int N){
        int diagonal1 = row - col;
        int diagonal2 = row + col;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                int conflict1 = i - j; // going from upper left to bottom right
                int conflict2 = i + j; // going from upper right to bottom left

                if(i == row || j == col || conflict1 == diagonal1 || conflict2 == diagonal2){
                    if(board[i][j] == 1)
                        return false;
                }
            }
        }
        return true;
    }

    private void printSolution(int board[][], int N){
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++){
                if(board[i][j] == 0){
                    System.out.print("_ ");
                }
                else{
                    System.out.print("Q ");
                }
            }
            System.out.println();
        }

    }


}
