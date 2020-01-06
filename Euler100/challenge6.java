// Challenge 6

/* Find the difference between the sum of the squares of the first one hundred natural
 * natural numbers and the square of the sum. */

public class challenge6 {
  // Function to find the square of the sum of the natural numbers up to n
  static int squareOfSum(int n) {
    int result = 0;

    for(int i = 1; i <= n; i++) {
      result += i;
    }

    result *= result;

    return result;
  }

  // Function to find the sum of squares of the natural numbres up to n
  static int sumOfSquares(int n) {
    int result = 0;

    for(int i = 1; i <= n; i++) {
      result += i*i;
    }

    return result;
  }

  // Function to find the difference between the square of the sum and the sum of squares
  // Up to the given number
  static int differenceBetweenSquareAndSum(int n) {
    int sum = sumOfSquares(n);
    int square = squareOfSum(n);
    return square-sum;
  }

  public static void main(String args[]) {
    System.out.println("The sum of the squares of the first ten natural numbers is " + sumOfSquares(10));
    System.out.println("The square of the sum of the first ten natural numbers is " + squareOfSum(10));
    System.out.println("The difference between the two is: " + differenceBetweenSquareAndSum(10));

    System.out.println("The difference for the first 100 natural numbers is: " + differenceBetweenSquareAndSum(100));
  }
}
