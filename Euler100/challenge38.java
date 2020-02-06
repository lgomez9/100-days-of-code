// Challenge 38

/* Take the number 192 and multiply it by each of 1, 2, and 3:
 *
 * 192 x 1 = 192
 * 192 x 2 = 384
 * 192 x 3 = 576
 *
 * By concatenating each product we get the 1 to 9 pandigital, 192384576.
 * We will call 192384576 the concatenated product of 192 and (1, 2, 3)
 * The same can be achieved by starting with 9 and multiplying by 1, 2, 3, 4, 5,
 * giving the pandigital, 918273645, which is the concatenated product of 9 and
 * (1, 2, 3, 4, 5).
 *
 * What is the largest 1 to 9 pandigital 9-digit number that can be formed as the
 * concatenated product of an integer with (1, 2, ..., n) where n > 1?
 */

public class challenge38 {
  // Count the total number of digits in a given int array
  static int totalDigitsInArr(int[] arr) {
    int total = 0;

    for(int i = 0; i < arr.length; i++) {
      int temp = arr[i];
      while(temp > 0) {
        total++;
        temp /= 10;
      }
    }

    return total;
  }

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
  static boolean arePandigital(int[] multiples) {
    // Array to check that all digits are accounted for
    boolean[] nums = new boolean[10];

    // Fill in digits
    for(int i = 0; i < multiples.length; i++) {
      if(!fillInDigits(multiples[i], nums)) return false;
    }

    if(nums[0]) return false;
    for(int i = 1; i < nums.length; i++) if(!nums[i]) return false;

    return true;
  }

  // Function to get the multiples of number and array of (1, 2, ..., maxFactor)
  static int[] getMultiples(int n, int maxFactor) {
    // Create an array of the factors up to maxFactor
    int[] factors = new int[maxFactor];
    factors[0] = 1;
    for(int i = 1; i < factors.length; i++) factors[i] = factors[i-1] + 1;

    // Create a new array of the multiples that result from multiplying n with the factors
    int[] multiples = new int[factors.length];
    for(int i = 0; i < factors.length; i++) multiples[i] = n * factors[i];

    return multiples;
  }

  // Checks if given number and array of (1, 2, ..., maxFactor) concatenated is pandigital
  static boolean isPandigitalMultiple(int n, int maxFactor) {
    // Get multiples of n and array up to maxFactor
    int[] multiples = getMultiples(n, maxFactor);

    // Check the number of digits in multiples
    if(totalDigitsInArr(multiples) > 9) return false;

    // Now check if the multiples are pandigital
    return arePandigital(multiples);
  }

  // Concatenates given multiples and returns them
  static int concatenatedMultiples(int[] multiples) {
    String rep = "";

    for(int i = 0; i < multiples.length; i++) rep += multiples[i];

    return Integer.parseInt(rep);
  }

  // Find the largest 1 to 9 pandigital number that can be formed as the concatenated product
  static int largestPandigital() {
    int largest = 0;

    for(int maxFactor = 1; maxFactor < 30; maxFactor++) {
      for(int i = 1; i < 10000; i++) {
        int[] multiples = getMultiples(i, maxFactor);
        if(totalDigitsInArr(multiples) > 9) continue;
        if(isPandigitalMultiple(i, maxFactor)) {
          int concat = concatenatedMultiples(multiples);
          if(concat > largest) {
            largest = concat;
          }
        }
      }
    }

    return largest;
  }

  public static void main(String args[]) {
    System.out.println("The concatenated product of 192 and (1,2,3) is pandigital: " + isPandigitalMultiple(192, 3));
    System.out.println("The largest pandigital multiple is " + largestPandigital());
  }
}
