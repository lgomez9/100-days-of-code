import java.math.*;
// Challenge 16

// 2^15 = 32768 and the sum of its digits is 3 + 2 + 7 + 6 + 8 = 26.
// What is the sum of the digits of the number 2^1000?

public class challenge16 {
  // Function that finds the sum of the digits of the given string
  static int sumOfDigits(String s) {
    // Variable to return the sum of the digits of the given BigInteger
    int sum = 0;

    for(int i = 0; i < s.length(); i++) {
      int curr = Character.getNumericValue(s.charAt(i));
      sum += curr;
    }

    return sum;
  }

  // Need to use BigInteger for this problem
  public static void main(String args[]) {
    // Create a big integer to hold what will eventually be 2^1000
    BigInteger bi = BigInteger.valueOf(2);

    // Raise 2 to the 1000th power
    bi = bi.pow(1000);

    // Find the answer
    System.out.println("The sum of the digits of 2^1000 is " + sumOfDigits(bi.toString()));
  }
}
