// Challenge 10

/* The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
 * Find the sum of all the primes below two million.
 */

public class challenge10 {
  // Function to find the sum of primes below n
  static long sumOfPrimesBelowN(int n) {
    // Find all the primes below n using the Sieve of Eratosthenes
    boolean[] composite = new boolean[n+1];
    composite[0] = true;
    composite[1] = true;

    for(int i = 2; i < composite.length; i++) {
      if(composite[i]) continue;
      for(int step = 2; i * step < composite.length; step++) composite[i*step] = true;
    }

    // Now set up a variable to hold the sum of all primes below this number
    long sumOfPrimes = 0;

    // Iterate through the array, adding the index value for non-composite (prime) numbers
    for(int i = 0; i < composite.length; i++) {
      if(!composite[i]) sumOfPrimes += i;
    }

    return sumOfPrimes;
  }

  public static void main(String args[]) {
    System.out.println("Sum of primes below 10 is " + sumOfPrimesBelowN(10));
    System.out.println("Sum of primes below 2000000 is " + sumOfPrimesBelowN(2000000));
  }
}
