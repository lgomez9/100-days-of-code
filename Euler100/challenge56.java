// Challenge 56: Powerful Digit Sum
import java.math.*;

/* A googol (10^100) is a massive number: one followed by one-hundred zeros;
 * 100^100 is almost unimaginably large: one followed by two-hundred zeros. Despite
 * their size, the sum of the digits in each number is only 1.
 *
 * Considering natural numbers of the form, a^b, where a, b < 100, what is the
 * maximum digital sum?
 */

public class challenge56 {
  // Find the sum of the digits of a given BigInteger
  static int digitSum(BigInteger bi) {
    int sum = 0;

    while(bi.compareTo(BigInteger.valueOf(0)) > 0) {
      sum += bi.mod(BigInteger.TEN).intValue();
      bi = bi.divide(BigInteger.TEN);
    }

    return sum;
  }

  // Find the sum of the digits of a^break;
  static int exponentDigitSum(int a, int b) {
    BigInteger bia = BigInteger.valueOf(a);
    BigInteger aToB = bia.pow(b);

    return digitSum(aToB);
  }

  // Find the maximum digital sum of a range of a^b, up to a, b < n
  static int maximumDigitalSum(int n) {
    int maxSum = 0;
    int currSum;

    for(int a = 1; a < 100; a++) {
      for(int b = 1; b < 100; b++) {
        currSum = exponentDigitSum(a, b);
        maxSum = currSum > maxSum ? currSum : maxSum;
      }
    }

    return maxSum;
  }

  public static void main(String args[]) {
    BigInteger bi = BigInteger.valueOf(12345);
    System.out.println("The digits of 12345 add up to " + digitSum(bi));
    System.out.println("The digits of 3^5 add up to " + exponentDigitSum(3, 5));

    System.out.println("The maximum sum of digits of numbers of the form a^b for a, b < 100 is " + maximumDigitalSum(100));
  }
}
