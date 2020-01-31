// Challenge 30
import java.lang.*;

/* Surprisingly there are only three numbers that can be written as the sum of
 * fourth powers of their digits
 *
 * 1634 = 1^4 + 6^4 + 3^4 + 4^4
 * 8208 = 8^4 + 2^4 + 0^4 + 8^4
 * 9474 = 9^4 + 4^4 + 7^4 + 4^4
 *
 * As 1 = 1^4 is not a sum it is not included
 *
 * The sum of these numbers is 1634 + 8208 + 9474 = 19316
 *
 * Find the sum of all the numbers that can be written as the sum of fifth powers
 * of their digits.
 */

public class challenge30 {
  // Function to find the sum of the pow powers of n's digits
  static long powPowersSum(int n, int pow) {
    long sum = 0;

    while(n > 0) {
      double ones = n % 10;

      sum += (long) Math.pow(ones, (double) pow);

      n /= 10;
    }

    return sum;
  }

  // Function to find the sum of all the numbers that can be written as the sum of
  // pow powers of their digits
  static long sumOfPowerDigits(int pow) {
    long sum = 0;

    // Find an upper limit for this problem
    int upper = (int) Math.pow(9.0, (double) pow) * 5;

    // Iterate through 2 to the upper limit
    for(int i = 2; i <= upper; i++) {
      if(i == powPowersSum(i, pow)) sum += i;
    }

    return sum;
  }


  public static void main(String args[]) {
    System.out.println("Sum of the numbers that can be written as the sum of the fourth power of their digits is " + sumOfPowerDigits(4));
    System.out.println("Sum of the numbers that can be written as the sum of the fifth power of their digits is " + sumOfPowerDigits(5));
  }
}
