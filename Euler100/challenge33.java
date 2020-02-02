// Challenge 33

/* The fraction 49/98 is a curious fraction, as an inexperienced mathematician in
 * attempting to simplify it may incorrectly believe that 49/98 = 4/8, which is correct,
 * is obtained by cancelling the 9s.
 *
 * We shall consider fractions, like 30/50 = 3/5, to be trivial examples.
 *
 * There are exactly four non-trivial examples of this type of fraction, less than
 * one in value, and containing two digits in the numerator and denominator.
 *
 * If the product of these four fractions is given in its lowest common terms,
 * find the value of the denominator.
 */

public class challenge33 {
  // Function that finds the GCF of two numbers
  static int gcf(int a, int b) {
    int smallest = a < b ? a : b;
    int gcFactor = 1;

    for(int i = 1; i <= smallest; i++) if(a % i == 0 && b % i == 0) gcFactor = i;

    return gcFactor;
  }

  // Function to return the lowest denominator of fraction a/b
  static int lowestDenominator(int a, int b) {
    // First find gcf
    int greatestFactor = gcf(a, b);

    return b / greatestFactor;
  }

  // Function that checks if two fractions a/b and c/d are the same
  static boolean sameFraction(int a, int b, int c, int d) {
    return (double) a / (double) b == (double) c / (double) d;
  }

  // Function that returns whether a fraction is a digit cancelling fraction a/b
  static boolean isDigitCancelling(int a, int b) {
    // If they're both multiples of ten, return false
    if(a % 10 == 0 && b % 10 == 0) return false;

    // Get all the relevant digits isolated
    int numTens = a / 10;
    int numOnes = a % 10;
    int denTens = b / 10;
    int denOnes = b % 10;

    // Check all cases
    // numTens = denTens
    if(numTens == denTens && sameFraction(a, b, numOnes, denOnes)) return true;
    // numTens = denOnes
    if(numTens == denOnes && sameFraction(a, b, numOnes, denTens)) return true;
    // numOnes = denTens
    if(numOnes == denTens && sameFraction(a, b, numTens, denOnes)) return true;
    // numOnes = denOnes
    if(numOnes == denOnes && sameFraction(a, b, numTens, denTens)) return true;


    return false;
  }

  // Function to find the lowest denominator of product of digit cancelling fractions
  static int lowestDenOfDigitCancelling() {
    // First get the numerator and denominator of the product of the digit cancelling fractions
    int productNum = 1;
    int productDen = 1;

    // Find the digit-cancelling fractions
    for(int a = 10; a < 100; a++) {
      for(int b = a+1; b < 100; b++) {
        if(isDigitCancelling(a, b)) {
          productNum *= a;
          productDen *= b;
        }
      }
    }

    //  Now find the gcf of the top and bottom
    int greatestFactor = gcf(productNum, productDen);

    return productDen / greatestFactor;
  }

  public static void main(String args[]) {
    System.out.println("The value of the denominator of the product of digit cancelling fractions is " + lowestDenOfDigitCancelling());
  }
}
