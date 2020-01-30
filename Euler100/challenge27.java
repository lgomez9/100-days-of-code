// Challenge 27
import java.lang.*;

/* Euler discovered the remarkable quadratic formula:
 * n^2 + n + 41
 * It turns out that the formula will produce 40 primes for the consecutive integer
 * values 0 <= n <= 39. However, when n = 40, 40^2 + 40 + 41 = 40(40+1) + 41 is
 * divisible by 41, and certainly when n = 41, 41^2 + 41 + 41 is clearly divisible
 * by 41.
 *
 * The incredible formula n^2 - 79n + 1601 was discovered, which produces 80 primes
 * for the consecutive values 0 <= n <= 79. The product of the coefficients, -79
 * and 1601, is -126479.
 *
 * Considering quadratics of the form:
 * n^2 + an + b, where |a| < 1000 and |b| < 1000
 * where |n| is the modulus/absolute value of n
 *
 * Find the product of the coefficients, a and b, for the quadratic expression that
 * produces the maximum number of primes for consecutive values of n, starting with n = 0.
 */

public class challenge27 {
  // Function to find the primes up to n
  static boolean[] findPrimes(int n) {
    // Find all the primes below n using the Sieve of Eratosthenes
    boolean[] notPrimes = new boolean[n+1];
    notPrimes[0] = true;
    notPrimes[1] = true;

    for(int i = 2; i < notPrimes.length; i++) {
      if(notPrimes[i]) continue;
      for(int step = 2; i * step < notPrimes.length; step++) notPrimes[i*step] = true;
    }

    return notPrimes;
  }

  // Function to find the maximum number of primes for consecutive values of n
  static int maxPrimes() {
    int maxA = 0;
    int maxB = 0;
    int maxN = 0;

    // Find the primes up until 1000*1000.
    boolean[] notPrimes = findPrimes(1000*1000);

    for(int a = -1000; a <= 1000; a++) {
      for(int b = -1000; b <= 1000; b++) {
        int n = 0;
        while(!notPrimes[Math.abs(n*n + a*n + b)]) {
          n++;
        }

        if(n > maxN) {
          maxA = a;
          maxB = b;
          maxN = n;
        }
      }
    }

    return maxA*maxB;
  }


  public static void main(String args[]) {
    System.out.println("The product of the coefficients a and b which produces the maximum number of primes is " + maxPrimes());
  }
}
