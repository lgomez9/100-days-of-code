// Challenge 29
import java.util.*;
import java.lang.*;
/* Consider all integer combinations of a^b for 2 <= a <= 5, and 2 <= b <= 5:
 *
 * 2^2 = 4, 2^3 = 8, 2^4 = 16, 2^5 = 32
 * 3^2 = 9, 3^3 = 27, 3^4 = 81, 3^5 = 243
 * 4^2 = 16, 4^3 = 64, 4^4 = 256, 4^5 = 1024
 * 5^2 = 25, 5^3 = 125, 5^4 = 625, 5^5 = 3125
 *
 * If they are then placed in numerical order, with any repeats removed, we get
 * the following sequence of 15 distinct terms:
 *
 *  4, 8, 9, 16, 25, 27, 32, 64, 81, 125, 243, 256, 625, 1024, 3125
 *
 * How many distinct terms are in the sequence generated by a^b for 2 <= a <= 100
 * and 2 <= b <= 100?
 */

public class challenge29 {
  // Function that returns a Set of the a^b combinations
  static long abCombos(double low, double high) {
    Set<Double> combos = new HashSet<>();

    for(double a = low; a <= high; a++) {
      for(double b = low; b <= high; b++) {
        combos.add(Math.pow(a, b));
      }
    }

    return (long) combos.size();
  }


  public static void main(String args[]) {
    System.out.println("The number of distinct terms from 2 to 5 is " + abCombos(2.0, 5.0));
    System.out.println("The number of distinct terms from 2 to 100 is " + abCombos(2.0, 100.0));
  }
}