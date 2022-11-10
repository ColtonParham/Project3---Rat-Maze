import java.util.*;
 
class ratMaze {
 
    // setting to a arraylist instead
    static ArrayList<String> res;
    public static ArrayList<String> findPath(int[][] m, int n) {
        res = new ArrayList<>();
          // dx, dy will be used to follow `SWEN` exploring approach
          // giving params
        int[] dx = { 1,  0, 0, -1 };
        int[] dy = { 0, -1, 1,  0 };
        if (m[0][0] == 1) {
            m[0][0] = 2;
            findPathHelper(m, n, 0, 0, dx, dy, "");
        }
        return res;
    }
     
    // helper function to add directions applicable by chopping a string
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
     
    // validation function
    private static boolean isValid(int row, int col, int[][] m, int n) {
        if (row >= 0 && row < n && col >= 0 && col < n && m[row][col] == 1) {
            return true;
        }
        return false;
    }
 
 
    public static void main(String[] args) {
 
        // needs to draw the maze, and needs to generate it too. - ask dr.zhao whether or not it needs to be randomly generated, or hardcoded in.
        int m[][] = { { 1, 0, 0, 0, 1, 1, 1, 0 },
                      { 1, 1, 0, 1, 1, 1, 0, 0 },
                      { 1, 1, 0, 0, 0, 1, 0, 0 },
                      { 0, 1, 1, 1, 1, 1, 1, 1 }};
        int n = m.length;
        // 2D array print?
       
        // Initial Array
 
        System.out.println("Initial Maze for Rat to Traverse");
 
        for (int[] array: m) {
            System.out.print("[");
            for (int f: array) {  
              System.out.print(f + " "); // printing each item
            }
            System.out.print("]\n"); // printing new line
          }
          System.out.println("");
 
 
        // maybe ask Dr. Zhao if he wants it to print all outcomes other than the directions..
        ArrayList<String> ans = findPath(m, n);
        System.out.println(ans);
    }
}

