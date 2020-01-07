// Challenge 7

/* By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see
 * that the 6th prime is 13. What is the 10001st prime number?
 */

public class challenge7 {

  // Function to find the Nth prime
  static int findNthPrime(int n) {
    // Set an arbitrarily large size (1 billion)
    int maxSize = 1000000000;

    // Create an array that will hold whether a number is prime up to the max size
    boolean[] composite = new boolean[maxSize+1];

    // True for the purposes of checking later
    composite[0] = true;
    composite[1] = true;

    // Sieve of Eratosthenes to set all composite numbers as such
    for(int i = 2; i < maxSize+1; i++) {
      if(composite[i]) continue;
      for(int step = 2; i * step < maxSize; step++) composite[i*step] = true;
    }

    // Find the prime!
    int primeNo = 0;

    for(int i = 0; i < maxSize+1; i++) {
      if(!composite[i]) primeNo++;
      if(primeNo == n) return i;
    }

    return 0;
  }

  public static void main(String args[]) {
    System.out.println("6th prime is " + findNthPrime(6));
    System.out.println("10001st prime is " + findNthPrime(10001));
  }
}
