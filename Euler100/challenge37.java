// Challenge 37

/* The number 3797 has an interesting property. Being prime itself, it is possible
 * to continuously remove digits from left to right, and remain prime at each stage:
 * 3797, 797, 97, and 7. Similary we can work from right to left: 3797, 379, 3737, and 3.
 *
 * Find the sum of the only eleven primes that are both truncatable from left to right
 * and right to left.
 *
 * NOTE: 2, 3, 5, and 7 are not considered to be truncatable primes.
 */

public class challenge37 {
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

  // Function to find whether a number is truncatable from right to left
  static boolean truncRightToLeft(int n, boolean[] composite) {
    while(n > 0) {
      if(composite[n]) return false;
      n /= 10;
    }

    return true;
  }

  // Function to find whether a number is truncatable from left to right
  static boolean truncLeftToRight(int n, boolean[] composite) {
    // Find the largest order of magnitude for this number
    int orderOfMagnitude = 1;
    int place = 0;

    while(n / orderOfMagnitude != 0) orderOfMagnitude *= 10;

    while(n % orderOfMagnitude != 0) {
      if(composite[n%orderOfMagnitude]) return false;
      orderOfMagnitude /= 10;
    }

    return true;
  }

  // Function to find whether a number is fully truncatable
  static boolean fullyTruncatable(int n, boolean[] composite) {
    if(n == 2 || n == 3 || n == 5 || n == 7) return false;
    return truncLeftToRight(n, composite) && truncRightToLeft(n, composite);
  }

  // Function to find the sum of the first eleven primes that are truncatable
  static long sumOfFullyTruncatable(boolean[] composite) {
    int count = 0;
    long sum = 0;

    for(int i = 10; i < composite.length; i++) {
      if(fullyTruncatable(i, composite)) {
        count++;
        sum += i;
      }
    }

    System.out.println("Count of truncatable primes is " + count);
    return sum;
  }

  public static void main(String args[]) {
    boolean[] composite = primesThroughN(1000000);

    System.out.println("3797 is fully truncatable: " + fullyTruncatable(3797, composite));
    System.out.println("Sum of fully truncatable primes is " + sumOfFullyTruncatable(composite));

  }
}
