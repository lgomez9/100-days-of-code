// Challenge 34

/* 145 is a curious number, as 1! + 4! + 5! = 1 + 24 + 120 = 145
 * Find the sum of all numbers which are equal to the sum of the factorial
 * of their digits.
 * Note: as 1! = 1 and 2! = 2 are not sums they are not included.
 */

public class challenge34 {
  // Function to find the factorial of a number
  static int factorial(int a) {
    int result = 1;

    for(int i = a; i > 0; i--) result *= i;

    return result;
  }

  // Function to find the sum of the factorial of the digits of a number
  static int sumOfDigitFactorials(int a) {
    int sum = 0;

    while(a > 0) {
      sum += factorial(a%10);
      a /= 10;
    }

    return sum;
  }

  // Function to find the sum of all numbers which are equal to the sum of the factorial of their digits
  static long sumOfEqualToDigitFactorials(int upper) {
    long sum = 0;

    // Start with double digits
    for(int i = 10; i < upper; i++) if(sumOfDigitFactorials(i) == i) sum += i;

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("The sum of the factorials of the digits of 145 is " + sumOfDigitFactorials(145));

    // Setting an arbitrary upper limit
    int upper = factorial(9) * 5;

    // Find the sum of the equal to digit factorials
    System.out.println("The sum of the equal to digit factorials is " + sumOfEqualToDigitFactorials(upper));
  }
}
