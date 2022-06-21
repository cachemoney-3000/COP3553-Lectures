package PartOne.L3;

public class Lecture03_Backtracking {

    public static int N;
    public static void main(String[] args) {
        // This will store the maze problem
        int[][] maze = {
                {1,0,0,1},
                {1,1,0,0},
                {0,1,1,1},
                {1,1,0,1}
        };

        N = maze.length;
        solveMaze(maze);
    }

    /*
    solveMaze(int[][] maze)
    * this is just a wrapper function
    * this will call the solveMazeUtil to give us a solution
    * if the solveMazeUtil returns true, then print the solution
    * movement: down and forward
     */
    public static void solveMaze(int[][] maze){
        int[][] sol = new int[N][N];

        // If the maze is solvable then print the solution
        if(solveMazeUtil(maze, 0, 0, sol))
            printSolution(sol);
    }

    // This will just print the solution if the maze was solved
    public static void printSolution(int[][] sol){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                System.out.print(sol[i][j]);
            }
            System.out.println();
        }
    }


    /*
    isSafe(int[][] arr, int row, int col)
    * if it is safe it will return true (1), if not return false (0)
    * it will also check if the array is in bounds
     */
    public static boolean isSafe(int[][] maze, int row, int col){
        // if it is inside the boundary and the array are safe then return true
        return row < N && col < N && maze[row][col] == 1;
    }

    /*
    solveMazeUtil(int[][] maze, int row, int col, int[][] sol)
    * this will return a solution for the problem
    * this will keep calling isSafe function to solve the solution
     */
    public static boolean solveMazeUtil(int[][] maze, int row, int col, int[][] sol){
        // If it reaches the end, and found the exit, then return the function as true
        if(row == N - 1 && col == N - 1 && maze[row][col] == 1){
            // update the solution
            sol[row][col] = 1;
            return true;
        }

        // If it is safe
        if(isSafe(maze, row, col)){
            if(sol[row][col] == 1){
                return false;
            }

            // update the solution
            sol[row][col] = 1;
            // check if moving down is safe
            if(solveMazeUtil(maze, row + 1, col, sol))
                return true;

            // if moving down is not safe, then try moving to the right
            if(solveMazeUtil(maze, row, col + 1, sol))
                return true;

            if(solveMazeUtil(maze, row - 1, col, sol))  return true; // go left
            if(solveMazeUtil(maze, row, col - 1, sol))  return true; // go up

            // if it reaches here, then it is not safe to move on either direction
            // mark the solution as 0
            // this will help the program to backtrack
            sol[row][col] = 0;
            return false;
        }
        // If it reaches here, this means it doesn't satisfy the above conditions therefore the puzzle can't be solved
        return false;
    }
}
