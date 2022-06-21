package PartOne.L3;

import java.util.Scanner;

public class Lecture03_Queens {
    private int[] perm;
    private boolean[] used;
    private int numSols;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter the size of the board (must be >= 4): ");
        int boardSize = scanner.nextInt();

        while (boardSize < 4){
            System.out.print("Entry must be >= 4. Try again: ");
            boardSize = scanner.nextInt();
        }

        Lecture03_Queens nQueens = new Lecture03_Queens(boardSize);
        nQueens.solveIt();
        nQueens.printNumSols();
    }

    public Lecture03_Queens(int n){
        perm = new int[n];
        used = new boolean[n];
        numSols = 0;

        for(int i = 0; i < n; i++){
            perm[i] = -1;
            used[i] = false;
        }
    }

    private void solveIt() {
        solveItRec(0);
    }

    // Pre-condition: perm stores a permutation of queens from index 0 to location-1
    //                that is valid for a set of location number of non-conflicting
    //                queens. location represents the column we are placing the next
    //                queen, and usedList keeps track of the rows in which queens
    //                have already been placed.
    private void solveItRec(int location) {
        int i;

        // We've found a solution to the problem, so print it!\
        // Perm length is equals to the number of queens
        if(location == perm.length){
            printSol();
            numSols++;
        }

        // Loop through possible locations for the next queen to place.
        for(i = 0; i < perm.length; i++){
            // Only try this row if it hasn't already been used.
            if(used[i] == false){
                // We can actually place this particular queen without conflict!
                if(!conflict(location, i)){
                    // Place the new queen!
                    perm[location] = i;
                    // We've used this row now, so mark that.
                    used[i] = true;
                    // Recursively solve this board.
                    solveItRec(location + 1);

                    // Unselect this square, so that we can continue trying to
                    // fill it with the next possible choice.
                    used[i] = false;
                }
            }
        }
    }

    // Only checks the diagonals
    private boolean conflict(int location, int row){
        // See if the grid spot (location, row) shares a diagonal with any of
        // the previously placed queens.
        for(int i = 0; i < location; i++){
            // Diagonals have equal distance in the x and y axes.
            if(Math.abs(location - i) == Math.abs(perm[i] - row)){
                return true;
            }
        }
        // No conflict, so we could place a queen there.
        return false;
    }

    private void printSol() {
        System.out.println("Here is a solution:");
        for(int i = 0; i < perm.length; i++){
            for(int j = 0; j < perm.length; j++){
                if(perm[j] == i){
                    System.out.print("Q ");
                }
                else{
                    System.out.print("_ ");
                }
            }
            System.out.println();
        }
    }

    private void printNumSols() {
        System.out.println("There were " + numSols + " solutions.");
    }


}
