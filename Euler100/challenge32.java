// Challenge 32
import java.util.*;
/* We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once; for example, the 5-digit number, 15234, is 1 through
 * 5 pandigital.
 *
 * The product 7254 is unusual, as the identity, 39 x 186 = 7254, containing multiplicand
 * multiplier, and product is 1 through 9 pandigital.
 *
 * Find the sum of all products whose multiplicand/multiplier/product identity can
 * be written as a 1 through 9 pandigital.
 *
 */

public class challenge32 {
  // Function to fill in array with digits in number
  static boolean fillInDigits(int n, boolean[] arr) {
    while(n > 0) {
      if(!arr[(n%10)]) arr[(n%10)] = true;
      else return false;

      n /= 10;
    }

    return true;
  }

  // Checks if given numbers are 1 through 9 pandigital
  static boolean arePandigital(int a, int b, int c) {
    // Array to check that all digits are accounted for
    boolean[] nums = new boolean[10];

    // Fill in digits in a, b, c
    if(!fillInDigits(a, nums)) return false;
    if(!fillInDigits(b, nums)) return false;
    if(!fillInDigits(c, nums)) return false;

    if(nums[0]) return false;
    for(int i = 1; i < nums.length; i++) if(!nums[i]) return false;

    return true;
  }

  // Function to find the sum of all the products whose multiplicand/multiplier/product
  static long productPandigitalSum() {
    // Create a long to return
    long sum = 0;
    HashSet<Integer> products = new HashSet<>();

    // Has to be a product between a two and three digit number
    for(int a = 1; a < 100; a++) {
      for(int b = 100; b < 10000; b++) {
        // If pandigital, add to the set
        if(arePandigital(a, b, (a*b))) products.add(a*b);
      }
    }

    // Go through the set and add it to the set
    for(Integer i : products) sum += i;

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("The identity 39 x 186 = 7254 is pandigital: " + arePandigital(39, 186, 7254));
    System.out.println("The identity 78 x 345 = 26910 is pandigital: " + arePandigital(78, 345, 26910));
    System.out.println("The sum of all products whose identity can be written as a 1 through 9 pandigital is " + productPandigitalSum());
  }
}
