import java.util.*;
import java.math.*;

// Challenge 53: Combinatoric selections

/* There are exactly ten ways of selecting three from five, 12345:
 * 123, 124, 125, 134, 135, 145, 234, 235, 245, and 345
 *
 * In combinatorics, we use the notation, (5 3) = 10
 *
 * In general, (n r) = n!/(r!(n-r)!), where r <= n, n! = n * (n-1) * ... * 3 * 2 * 1, and 0! = 1
 *
 * It is not until n = 23, that a value exceeds one million: (23 10) = 1144066.
 *
 * How many, not necessarily distinct, values of (n r) for 1 <= n <= 100, are greater than
 * one million
 */

public class challenge53 {
  // Function to find the factorial of a given number
  static BigInteger factorial(int n) {
    if(n == 0) return BigInteger.valueOf(1);

    BigInteger result = BigInteger.valueOf(n);

    for(int i = n-1; i > 0; i--) result = result.multiply(BigInteger.valueOf(i));

    return result;
  }

  // Function to find the number of combinations of r from n
  static BigInteger combinations(int n, int r) {
    BigInteger numerator = factorial(n);
    BigInteger denominator = factorial(r).multiply(factorial(n-r));

    return numerator.divide(denominator);
  }

  // Function to find values of (n r) that are greater than one-million for 1 <= n <= 100.
  static int greaterThanOneMillion() {
    int result = 0;

    // All the n values
    for(int n = 1; n <= 100; n++) {
      // All the r values (must be less than or equal to n)
      for(int r = 1; r <= n; r++) {
        if(combinations(n, r).compareTo(BigInteger.valueOf(1000000)) == 1) result++;
      }
    }

    return result;
  }

  public static void main(String args[]) {
    System.out.println("Factorial of 8 is " + factorial(8).toString());
    System.out.println("There are " + combinations(5, 3) + " ways of selecting three from five.");
    System.out.println("There are " + combinations(23, 10) + " ways of selecting ten from 23.");

    System.out.println("There are " + greaterThanOneMillion() + " combinations greater than one million.");
  }
}
