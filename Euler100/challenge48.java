// Challenge 48: Self powers
import java.math.*;

/* The series, 1^1 + 2^2 + 3^3 + ... + 10^10 = 10405071317.
 *
 * Find the last ten digits of the series, 1^1 + 2^2 + 3^3 + ... + 1000^1000.
 *
 */

public class challenge48 {
  // Function to find the series of 1^1 + 2^2 + .... + n^n
  static BigInteger selfPowers(int n) {
    BigInteger result = BigInteger.valueOf(1);

    for(int i = 2; i <= n; i++) {
      result = result.add(BigInteger.valueOf(i).pow(i));
    }
    return result;
  }

  // Function to return the last ten digits of a given BigInteger
  static BigInteger lastTen(BigInteger bi) {
    long eleven = 1;
    for(int i = 0; i < 10; i++) eleven *= 10;
    System.out.println("Eleven digits: " + eleven);
    return bi.divideAndRemainder(BigInteger.valueOf(eleven))[1];
  }

  public static void main(String args[]) {
    System.out.println("The series of self powers up to 10 gives the sum " + selfPowers(10).toString());

    System.out.println("The last ten digits of the self powers series up to 1000 are " + lastTen(selfPowers(1000)));
  }
}
