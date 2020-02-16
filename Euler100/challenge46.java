// Challenge 46

/* It was proposed by Christian Goldbach that every odd composite number can be
 * written as the sum of a prime and twice a square.
 *
 *    9 = 7 + 2x1^2
 *    15 = 7 + 2x2^2
 *    21 = 3 + 2x3^2
 *    25 = 7 + 2x3^2
 *    27 = 19 + 2x2^2
 *    33 = 31 + 2x1^2
 *
 * It turns out that the conjecture was false.
 *
 * What is the smallest odd composite that cannot be written as the sum of a prime
 * and twice a square?
 *
 */

public class challenge46 {
  // Function to find the primes up through n (returned array returns false for prime numbers)
  static boolean[] primesThroughN(int n) {
    boolean[] composite = new boolean[n+1];
    composite[0] = true;
    composite[1] = true;

    for(int i = 2; i < composite.length; i++) {
      if(composite[i]) continue;
      for(int step = 2; i * step < composite.length; step++) composite[i*step] = true;
    }

    return composite;
  }

  // Function that tells you whether a number n can be written as the sum of a given prime p and twice a square.
  static boolean sumOfGivenPrimeAndSquare(int n, int p) {
    int curr = 1;

    // Increment the square until you get past the number
    while(p + 2 * curr * curr < n) curr++;

    // Return whether the result is equal to the number
    return p + 2 * curr * curr == n ? true : false;
  }

  // Function that tells you whether a number n can be written as the sum of a prime and twice a square.
  static boolean sumOfPrimeAndSquare(int n, boolean[] composite) {
    // Check to make sure you have enough primes
    if(n >= composite.length) {
      System.out.println("Need more primes!");
      return false;
    }

    // Go through the prime numbers and check!
    for(int i = 2; i < n; i++) {
      if(!composite[i] && sumOfGivenPrimeAndSquare(n, i)) return true;
    }

    return false;
  }

  // Function that finds the smallest odd composite that cannot be written as the sum of a prime and twice a square!
  static int smallestOddComposite(boolean[] composite) {
    for(int i = 3; i < composite.length; i += 2) {
      // If the composite number cannot be written as the sum of prime and square, return it!
      if(composite[i] && !sumOfPrimeAndSquare(i, composite)) return i;
    }

    return -1;
  }

  public static void main(String args[]) {
    // Get prime numbers through 1 million
    boolean[] composite = primesThroughN(1000000);

    System.out.println("33 can be written as the sum of a prime and twice a square: " + sumOfPrimeAndSquare(33, composite));
    System.out.println("Smallest odd composite that cannot be written as the sum of a prime and twice a square is " + smallestOddComposite(composite));

  }
}
