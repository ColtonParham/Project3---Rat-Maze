/* Java program to solve Rat in
 a Maze problem using backtracking */
 import java.io.*;
 import java.util.*;
 public class ratterMaze {
 
  // Size of the maze
  static int N;

  /* A utility function to print
  solution matrix sol[N][N] */
  void printSolution(int sol[][])
  {
      for (int i = 0; i < N; i++) {
          for (int j = 0; j < N; j++)
              System.out.print(
                  " " + sol[i][j] + " ");
          System.out.println();
      }
  }

  /* A utility function to check
      if x, y is valid index for N*N maze */
  boolean isSafe(
      int maze[][], int x, int y)
  {
      // if (x, y outside maze) return false
      return (x >= 0 && x < N && y >= 0
              && y < N && maze[x][y] == 1);
  }

  /* This function solves the Maze problem using
  Backtracking. It mainly uses solveMazeUtil()
  to solve the problem. It returns false if no
  path is possible, otherwise return true and
  prints the path in the form of 1s. Please note
  that there may be more than one solutions, this
  function prints one of the feasible solutions.*/
  boolean solveMaze(int maze[][])
  {
      int sol[][] = new int[N][N];

      if (solveMazeUtil(maze, 0, 0, sol) == false) {
          System.out.print("Solution doesn't exist");
          return false;
      }

      printSolution(sol);
      return true;
  }

  /* A recursive utility function to solve Maze
  problem */
  boolean solveMazeUtil(int maze[][], int x, int y,
                        int sol[][])
  {
      // if (x, y is goal) return true
      if (x == N - 1 && y == N - 1
          && maze[x][y] == 1) {
          sol[x][y] = 1;
          return true;
      }

      // Check if maze[x][y] is valid
      if (isSafe(maze, x, y) == true) {
            // Check if the current block is already part of solution path.   
            if (sol[x][y] == 1)
                return false;
         
          // mark x, y as part of solution path
          sol[x][y] = 1;

          /* Move forward in x direction */
          if (solveMazeUtil(maze, x + 1, y, sol))
              return true;

          /* If moving in x direction doesn't give
          solution then Move down in y direction */
          if (solveMazeUtil(maze, x, y + 1, sol))
              return true;

          /* If none of the above movements works then
          BACKTRACK: unmark x, y as part of solution
          path */
          sol[x][y] = 0;
          return false;
      }

      return false;
  }

// Printing Path: 
static ArrayList<String> res;
    public static ArrayList<String> findPath(int[][] m, int n) {
        res = new ArrayList<>();
          // dx, dy will be used to follow `DLRU` exploring approach
          // which is lexicographically sorted order
        int[] dx = { 1,  0, 0, -1 };
        int[] dy = { 0, -1, 1,  0 };
        if (m[0][0] == 1) {
            m[0][0] = 2;
            findPathHelper(m, n, 0, 0, dx, dy, "");
        }
        return res;
    }
     
    private static void findPathHelper(int[][] m, int n, int x, int y, int[] dx, int[] dy, String path) {
        if (x == n - 1 && y == n - 1) {
            res.add(path);
            return;
        }
        String dir = "SWEN";
        for (int i = 0; i < 4; i++) {  
            int row = x + dx[i];
            int col = y + dy[i];
            if (isValid(row, col, m, n)) {
                m[row][col] = 2;                // used to track visited cells of matrix
                findPathHelper(m, n, row, col, dx, dy, path + dir.charAt(i));
                m[row][col] = 1;                // mark it unvisited yet explorable
            }
        }
    }
     
    private static boolean isValid(int row, int col, int[][] m, int n) {
        if (row >= 0 && row < n && col >= 0 && col < n && m[row][col] == 1) {
            return true;
        }
        return false;
    }



    // as long as the number of entries, and the number of rows are the same, it will work... 
  // still needs a button to prompt the run... maybe might need something that will do it in order, or a prompt for the different array options to traverse through..
  public static void main(String args[])
  {
      ratterMaze rat = new ratterMaze();
      int maze[][] = { 
      { 1, 0, 0, 0, 0, 0 },
      { 1, 1, 0, 1, 0, 1 },
      { 0, 1, 0, 1, 1, 1 },
      { 1, 1, 1, 1, 0, 1 }, 
      { 1, 1, 1, 1, 1, 1 },
      { 1, 1, 1, 1, 0, 1 } };

       System.out.println("Initial Maze for Rat to Traverse");
 
        for (int[] array: maze) {
             System.out.print(" ");
              for (int f: array) {  
                System.out.print(f + "  "); // printing each item
                   }
                System.out.print("\n"); // printing new line
              }
       System.out.println("");


              // traversal issue 
      System.out.println("Path found in Maze for Rat to Traverse");
      N = maze.length;
      rat.solveMaze(maze);

      // Printing the Path
      System.out.println("\nPath/Paths for Rat to Traverse");
      ArrayList<String> ans = findPath(maze, N);
      System.out.println(ans);
  }
}
