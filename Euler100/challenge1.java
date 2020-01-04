/* If we list all the natural numbers below 10 that are multiple of 3 or 5, we
 * get 3, 5, 6, and 9. The sum of these multiples is 23. Find the sum of all
 * multiples of 3 or 5 below 1000.
 */

public class challenge1 {

  // Function that finds the sum of all multiples of 3 and 5 below the given number.
  static int sumOfMultiples(int n) {
    // Create a sum int to return
    int sum = 0;

    // First, add all the multiples of 3
    for(int i = 3; i < n; i += 3) {
      sum += i;
    }

    // Next, add all the multiples of 5
    for(int i = 5; i < n; i += 5) {
      if(i % 3 != 0) sum += i;
    }

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("First example is 10: " + sumOfMultiples(10));
    System.out.println("Problem 1 is asking for 1000: " + sumOfMultiples(1000));
  }
}
