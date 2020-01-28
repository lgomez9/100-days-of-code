import java.util.*;
// Challenge 26

/* A unit fraction contains 1 in the numerator. The decimal representation of
 * the unit fractions with denominators 2 to 10 are given:
 * 1/2 = 0.5
 * 1/3 = 0.(3)
 * 1/4 = 0.25
 * 1/5 = 0.2
 * 1/6 = 0.1(6)
 * 1/7 = 0.(142857)
 * 1/8 = 0.125
 * 1/9 = 0.(1)
 * 1/10 = 0.1
 *
 * Where 0.1(6) means 0.1666666...., and has a 1-digit recurring cycle. It can be
 * seen that 1/7 has a 6-digit recurring cycle.
 *
 * Find the value of d < 1000 for which 1/d contains the longest recurring cycle
 * in its decimal fraction part.
 */

public class challenge26 {
  // Function to find the length of the cycle of a given unit fraction
  static long lengthOfCycle(int n) {
    // Find the remainder, and continue going until you get a remainder you've already gotten
    long len = 0;
    ArrayList<Integer> remainderPosition = new ArrayList<>();

    // Start with the beginning (should always be 1)
    int remainder = 1 % n;

    // Iterate through the remainders until we find a cycle or division is completed
    while(!remainderPosition.contains(remainder) && remainder != 0) {
      remainderPosition.add(remainder);
      remainder *= 10;
      remainder %= n;
      len++;
    }

    // Return the current length of the decimal minus the position of this remainder in the index
    return remainder != 0 ? len - remainderPosition.indexOf(remainder) : 0;
  }

  // Function to find the largest cycle in unit fractions up to n
  static long largestCycleUpToN(int n) {
    // Iterate through all of these, looking for the max
    long max = 0;
    long maxIndex = 0;

    for(int i = 1; i <= n; i++) {
      long len = lengthOfCycle(i);
      if(len > max) {
        max = len;
        maxIndex = i;
      }
    }

    return maxIndex;
  }


  public static void main(String args[]) {
    System.out.println("The longest cycle is 1/" + largestCycleUpToN(1000));
  }
}
