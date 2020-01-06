// Problem 5

/* 2520 is the smallest number that can be divided by each of the numbers from 1
 * to 10 without any remainder.
 * What is the smallest positive number that is evenly divisible by all of the
 * numbers from 1 to 20?
 */

public class challenge5 {
  // A function that checks if a number n is evenly divisible by all numbers up to d
  static boolean evenlyDivisibleUpTo(int n, int d) {
    boolean divisible = true;

    for(int i = 1; i < d; i++) {
      if(n % i != 0) divisible = false;
    }

    return divisible;
  }

  // A function that finds the smallest number that can be divided evenly by
  // each of the numbers up to d
  static int smallestDivisible(int d) {
    // Start with the largest number
    int result = d;

    // Run a while loop checking if result is divisible by all numbers
    // Increment by the largest number
    for(;!evenlyDivisibleUpTo(result, d); result += d);

    return result;
  }

  public static void main(String args[]) {
    System.out.println("Smallest evenly divisible number by each of 1 to 10 is: " + smallestDivisible(10));
    System.out.println("Smallest evenly divisible number by each of 1 to 20 is: " + smallestDivisible(20));
  }
}
