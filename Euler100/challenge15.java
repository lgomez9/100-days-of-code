// Challenge 15

/* Starting in the top left corner of a 2x2 grid, and only being able to move to
 * the right and down, there are exactly 6 routes to the bottom right corner.
 *
 * How many such routes are there through a 20x20 grid?
 */

public class challenge15 {

  // Function to find the number of paths through an NxN grid
  // Dynamic programming
  static long numOfPaths(int n) {
    // Create array to hold sub solutions
    long[][] numPaths = new long[n+1][n+1];

    // Initialize the base case
    numPaths[0][0] = 1;

    // Fill out the array with the number of ways to get to each path
    for(int i = 0; i < n+1; i++) {
      for(int j = 0; j < n+1; j++) {
        if(i > 0) numPaths[i][j] += numPaths[i-1][j];
        if(j > 0) numPaths[i][j] += numPaths[i][j-1];
      }
    }

    // Return the number of paths found
    return numPaths[n][n];
  }

  public static void main(String args[]) {
    System.out.println("A 2x2 grid has " + numOfPaths(2) + " paths.");
    System.out.println("A 3x3 grid has " + numOfPaths(3) + " paths.");
    System.out.println("A 4x4 grid has " + numOfPaths(4) + " paths.");
    System.out.println("A 20x20 grid has " + numOfPaths(20) + " paths.");
  }
}
