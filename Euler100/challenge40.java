// Challenge 40

/* An irrational decimal fraction is created by concatenating the positive integers:
 *
 * 0.123456789101112131415161718192021...
 *
 * It can be seen that the 12th digit of the fractional part is 1.
 *
 * If d_n represents the nth digit of the fractional part, find the value of the
 * following expression.
 *
 * d_1 x d_10 x d_100 x d_1000 x d_10000 x d_100000 x d_1000000
 *
 */

public class challenge40 {
  // Function to find the nth digit (going from right to left) of a number p
  static int nthDigitOfInteger(int n, int p) {
    while(n > 0) {
      p /= 10;
      n--;
    }
    return p % 10;
  }

  // Function to find the nth digit of the fractional concatenation of the positive integers
  static int nthDigitOfFractional(int n) {
    int currPlace = 1;
    int currNumDigits = 1;

    while(n > 0) {
      for(int i = currPlace; i < currPlace*10; i++) {
        n -= currNumDigits;
        if(n <= 0) return nthDigitOfInteger(Math.abs(n), i);
      }
      currNumDigits++;
      currPlace *= 10;
    }

    return 0;
  }

  // Function that returns the product of an int array
  static int arrProduct(int[] arr) {
    int product = 1;

    for(int i = 0; i < arr.length; i++) product *= arr[i];

    return product;
  }

  public static void main(String args[]) {
    int[] ds = new int[7];
    int magnitude = 1;

    for(int i = 0; i < ds.length; i++, magnitude *= 10) {
      ds[i] = nthDigitOfFractional(magnitude);
      System.out.println("d" + magnitude + " = " + ds[i]);
    }

    System.out.println("Product of these fractional parts is " + arrProduct(ds));
  }
}
