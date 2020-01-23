import java.util.*;
// Challenge 23

/* A perfect number is a number for which the sum of its proepr divisors is exactly
 * equal to the number. For example, the sum of the proper divisors of 28 would be
 * 1 + 2 + 4 + 7 = 28, which means that 28 is a perfect number.
 *
 * A number n is called deficient if the sum of its proper divisors is less than n
 * and it is called abundant if this sum exceeds n.
 *
 * As 12 is the smallest abudant number, 1 + 2 + 3 + 4 + 6 = 16, the smallest
 * number that can be written as the sum of two abundant numbers is 24. By mathematical
 * analysis, it can be shown that all integers greater than 28123 can be written
 * as the sum of two abundant numbers. However, this upper limit cannot be reduced
 * any further by analysis even though it is known that the greatest number that
 * cannot be expressed as the sum of two abundant numbers is less than this limit.
 *
 * Find the sum of all the positive integers which cannot be written as the sum
 * of two abundant numbers.
 */

public class challenge23 {
  // Function to find the sum of the proper divisors of n
  static int divisorSum(int n) {
    int sum = 1;

    for(int i = 2; i < n; i++) {
      if(n % i == 0) sum += i;
    }

    return sum;
  }

  // Function that returns whether a number is abundant or not
  static boolean isAbundant(int n) {
    return divisorSum(n) > n;
  }

  // Function to find the sum of all the positive integers which cannot be written as the sum of two abundant numbers
  static long notAbundantSum() {
    // Upper limit of abundant sum
    int upper = 28123;

    // Sum
    long sum = 0;

    // Create a boolean array to hold whether a number is the sum of two abundant numbers
    boolean[] sumOfAbundant = new boolean[upper+1];

    // Create a HashSet to contain all the abundant numbers
    HashSet<Integer> abundantNums = new HashSet<>();

    // Find all the abundant numbers up to the upper limit
    for(int i = 1; i <= upper; i++) {
      if(isAbundant(i)) abundantNums.add(i);
    }

    // Iterate through the abundant numbers, and put the sums in the boolean
    for(Integer i : abundantNums) {
      for(Integer j : abundantNums) {
        if(j < i) continue;
        if(i+j <= upper) sumOfAbundant[i+j] = true;
      }
    }

    // Iterate through the sumOfAbundant, adding to the ultimate sum if they cannot be expressed as the sum of two abundant numbers
    for(int i = 1; i < sumOfAbundant.length; i++) {
      if(!sumOfAbundant[i]) sum += i;
    }

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("Sum of numbers that are not the sum of abundant numbers is " + notAbundantSum());
  }
}
