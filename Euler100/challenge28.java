// Challenge 28

/* Starting with the number 1 and moving to the right in a clockwise direction a
 * 5 by 5 spiral is formed as follows:
 *
 *
 *  21 22 23 24 25
 *  20 07 08 09 10
 *  19 06 01 02 11
 *  18 05 04 03 12
 *  17 16 15 14 13
 *
 * It can be verified that the sum of the numbers on the diagonals is 101.
 * What is the sum of the numbers on the diagonals in a 1001 by 1001 spiral formed
 * in the same way?
 */

public class challenge28 {
  // Function to find the sum of the spiral layer that is nxn
  static int sumOfLayer(int n) {
    return 4 * n * n - 6 * (n-1);
  }

  // Function to find the total sum of the spiral up until the nxn spiral
  static long sumOfSpiral(int n) {
    if(n == 1) return 1;

    long sum = 1;

    for(int i = 3; i <= n; i += 2) sum += sumOfLayer(i);

    return sum;
  }

  public static void main(String args[]) {
      // The diagonals go up to the square of n for the nxn spiral
      // Each spiral will add n^2 + (n^2 - (n - 1)) + (n^2 - 2(n-1)) + (n^2 - 3(n-1))
      // n^2 + n^2 - (n - 1) + n^2 - 2(n-1) + n^2 - 3(n-1)
      // 4n^2 - 6(n-1)
      System.out.println("Sum of 5x5 spiral layer should be " + sumOfLayer(5));
      System.out.println("Sum of 3x3 spiral layer should be " + sumOfLayer(3));
      System.out.println("Sum of 5x5 spiral is " + sumOfSpiral(5));
      System.out.println("Sum of 1001x1001 spiral should be " + sumOfSpiral(1001));
  }
}
