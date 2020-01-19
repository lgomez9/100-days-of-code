import java.math.*;
// Challenge 20

// n! means n x (n-1) x ... x 3 x 2 x 1
// For example, 10! = 10 x 9 x ... x 3 x 2 x 1 = 3628800,
// and the sum of the digits in the number 10! is 3 + 6 + 2 + 8 + 8 + 0 + 0 = 27.
// Find the sum of the digits in the number 100!

public class challenge20 {
  // Function that finds the sum of the digits of the given string
  static long sumOfDigits(String s) {
    // Variable to return the sum of the digits of the given BigInteger
    long sum = 0;

    for(int i = 0; i < s.length(); i++) {
      int curr = Character.getNumericValue(s.charAt(i));
      sum += (long) curr;
    }

    return sum;
  }

  // Function that finds the sum of the digits up to n!
  static long upToN(int n) {
    // Create a big integer to hold n!
    BigInteger bi = BigInteger.valueOf(n);

    // Find n!
    for(int i = n-1; i > 0; i--) bi = bi.multiply(BigInteger.valueOf(i));

    // Find sum of digits of n!
    long sum = sumOfDigits(bi.toString());

    // Find the sum of the digits
    return sum;
  }

  // Find the sum of the digits in the number 100!
  public static void main(String args[]) {
    System.out.println("Sum of digits is 10: " + upToN(10));
    System.out.println("Sum of digits is 100: " + upToN(100));
  }
}
