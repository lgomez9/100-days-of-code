// Challenge 52: Permuted multiples

/* It can be seen that the number, 125874, and its double, 251748, contain
 * exactly the same digits, but in a different order.
 *
 * Find the smallest positive integer, x, such that 2x, 3x, 4x, 5x, and 6x,
 * contain the same digits.
 */

public class challenge52 {
  // Function to check if two numbers have the same digits.
  static boolean sameDigits(int a, int b) {
    int[] digits = new int[10];

    // Increment the index associated with digits in the first number
    while(a > 0) {
      digits[a%10]++;
      a /= 10;
    }

    // Decrement the index associated with digits in the second number
    while(b > 0) {
      digits[b%10]--;
      b /= 10;
    }

    // If the array is filled with zeros, return true, else return false
    for(int i = 0; i < digits.length; i++) {
      if(digits[i] != 0) return false;
    }

    return true;
  }

  // Function to find the smallest positive integer, x, such that 2x, 3x, 4x, 5x,
  // and 6x contain the same digits
  static int smallestPermutedMultiple() {
    int i = 1;

    for(;; i++) {
      if(!sameDigits(i, i*2)) continue;
      if(!sameDigits(i, i*3)) continue;
      if(!sameDigits(i, i*4)) continue;
      if(!sameDigits(i, i*5)) continue;
      if(!sameDigits(i, i*6)) continue;
      break;
    }

    return i;
  }

  public static void main(String args[]) {
    System.out.println("125874 and 251748 have the same digits: " + sameDigits(125874, 251748));
    int smallest = smallestPermutedMultiple();
    System.out.println("Smallest positive integer so that x, 2x, 3x, 4x, 5x, and 6x have the same digits is " + smallest);
    for(int i = 2; i <= 6; i++) System.out.println("x: " + smallest + " and " + i + "x: " + (smallest*i));
  }
}
