import java.util.*;
import java.math.*;
// Challenge 25

/* The Fibonacci sequence is defined by the recurrence relation:
 * Fn = Fn-1 + Fn-2, where F1 = 1 and F2 = 1.
 * Hence the first 12 terms will be:
 * F1 = 1
 * F2 = 1
 * F3 = 2
 * F4 = 3
 * F5 = 5
 * F6 = 8
 * F7 = 13
 * F8 = 21
 * F9 = 34
 * F10 = 55
 * F11 = 89
 * F12 = 144
 *
 * The 12th term, F12, is the first term to contain three digits.
 * What is the index of the first term in the Fibonacci sequence to contain 1000 digits?
 */

public class challenge25 {
  // Function to return the number of digits a BigInteger has
  static int numOfDigits(BigInteger bi) {
    int num = 0;

    while(bi.compareTo(BigInteger.ZERO) > 0) {
      bi = bi.divide(BigInteger.TEN);
      num++;
    }

    return num;
  }
  // A function to calculate the first Fibonacci number with 1000 digits
  static int nthFib() {
    // Array to hold the fibonacci sequence
    ArrayList<BigInteger> fib = new ArrayList<>();

    // Variable holding the current index
    int currIndex = 2;

    fib.add(BigInteger.valueOf(1));
    fib.add(BigInteger.valueOf(1));

    for(; numOfDigits(fib.get(currIndex-1)) != 1000; currIndex++) {
      fib.add(fib.get(currIndex-1).add(fib.get(currIndex-2)));
    }

    return currIndex-1;
  }

  public static void main(String args[]) {
    System.out.print("The index of the first Fibonacci number with 1000 digits is: ");
    System.out.println(nthFib());
  }
}
