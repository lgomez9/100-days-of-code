import java.math.*;
// Problem 21

// Let d(n) be defined as the sum of proper divisors of n (numbers less than n
// divide evenly into n).

// If d(a) = b and d(b) = a, where a != b, then a and b are an amicable pair and
// each of a and b are called amicable numbers.

// For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55, and 110;
// therefore d(220) = 284. The proper divisors of 284 are 1, 2, 4, 71, and 142; so
// d(284) = 220.

// Evaluate the sum of all the amicable numbers under 1000.

public class challenge21 {
  // Function to find the sum of the proper divisors of n
  static int divisorSum(int n) {
    int sum = 1;

    for(int i = 2; i < Math.sqrt(n); i++) {
      if(n % i == 0) sum += i + n/i;
    }

    return sum;
  }

  // Function that returns whether two numbers are amicable.
  static boolean amicableNumbers(int a, int b) {
    if(divisorSum(a) == b && divisorSum(b) == a) return true;
    return false;
  }

  // Function to evaluate the sum of all the amicable numbers under n.
  static long amicableSum(int n) {
    // Create an array to hold whether a pair of numbers are amicable
    boolean[] amicablePairs = new boolean[n];

    for(int i = 1; i < n; i++ ) {
      // Find divisor sum of i
      int sumDivisor = divisorSum(i);
      if(i != sumDivisor && amicableNumbers(i , sumDivisor)) {
        amicablePairs[i] = amicablePairs[sumDivisor] = true;
      }
    }

    // Create a long to hold the response
    long sum = 0;

    for(int i = 0; i < n; i++) {
      if(amicablePairs[i]) sum += i;
    }

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("Sum of proper divisors of 220 is " + divisorSum(220));
    System.out.println("Sum of proper divisors of 284 is " + divisorSum(284));
    System.out.println("284 and 220 are amicable numbers: " + amicableNumbers(220, 284));
    System.out.println("The sum of all the amicable numbers under 10000 is " + amicableSum(10000));
  }
}
