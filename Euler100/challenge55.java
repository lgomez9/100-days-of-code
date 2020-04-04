// Challenge 55
import java.lang.*;
import java.math.*;

/* If we take 47, reverse and add, 47 + 74 = 121, which is palindromic.
 *
 * Not all numbers produce palindromes so quickly. For example,
 * 349 + 943 = 1292
 * 1292 + 2921 = 4213
 * 4213 + 3124 = 7337
 *
 * That is, 349 took three iterations to arrive at a palindrome.
 *
 * Although no one has proved it yet, it is thought that some numbers, like 196,
 * never produce a palindrome. A number that never forms a palindrome through the
 * reverse and add process is called a Lychrel number. Due to the theoretical nature
 * of these numbers, and for the purpose of this problem, we shall assume that a number
 * is Lychrel until proven otherwise. In addition, you are given that for every number
 * below ten-thousand, it will either (i) become a palindrome in less than fifty iterations
 *, or (ii) no one, with all the computing power that exists, has managed so far to map
 * it to a palindrome. In fact, 10677 is the first number to be shown to require over
 * fifty iterations before producing a palindrome. (53 iterations).
 *
 * Surprisingly, there are palindromic numbers that are themselves Lychrel numbers;
 * the first example is 4994.
 *
 * How many Lychrel numbers are there below ten-thousand?
 */

public class challenge55 {
  // Function to reverse an integer
  static BigInteger reverseInt(BigInteger n) {
    BigInteger reversed = BigInteger.valueOf(0);

    while(n.compareTo(BigInteger.valueOf(0)) > 0) {
      reversed = reversed.multiply(BigInteger.valueOf(10)).add(BigInteger.valueOf(n.mod(BigInteger.valueOf(10)).longValue()));
      n = n.divide(BigInteger.valueOf(10));
    }

    return reversed;
  }

  // Function to check if an int is a palindrome
  static boolean isPalin(BigInteger n) {
    return n.equals(reverseInt(n));
  }

  // Function to check if a number is a Lychrel number
  static boolean isLychrel(BigInteger n) {
    int iterations = 0;
    BigInteger curr = n;

    while(iterations++ < 50) {
      if(isPalin(curr.add(reverseInt(curr)))) return false;
      else curr = curr.add(reverseInt(curr));
    }

    return true;
  }

  // Function to find the number of Lychrel numbers below n
  static int numOfLychrelBelow(int n) {
    int totalBelow = 0;

    for(int i = 1; i < 10000; i++) {
      if(isLychrel(BigInteger.valueOf(i))) totalBelow++;
    }

    return totalBelow;
  }

  public static void main(String args[]) {
    System.out.println("The opposite of 1234 is " + reverseInt(BigInteger.valueOf(1234)));
    System.out.println("The number 34543 is a palindrome: " + isPalin(BigInteger.valueOf(34543)));
    System.out.println("The number 47 is a Lychrel number: " + isLychrel(BigInteger.valueOf(47)));
    System.out.println("The number 349 is a Lychrel number: " + isLychrel(BigInteger.valueOf(349)));
    System.out.println("The number 196 is a Lychrel number: " + isLychrel(BigInteger.valueOf(196)));

    System.out.println("The total number of Lychrel numbers below 10000 is " + numOfLychrelBelow(10000));
  }
}
