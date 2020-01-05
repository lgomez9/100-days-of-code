// Challenge 4

/* A palindromic number reads the same both ways. The largest palindrome made
 * from the product of two 2-digit numbers is 9009 = 91 x 99.
 * Find the largest palindrome made from the product of two 3-digit numbers.
 */

public class challenge4 {

  // Function that returns true when given int is a palindrome
  static boolean isPalindrome(int n) {
    // Reverse the int by dividing by 10 continuously and adding the result
    int reversed = 0;
    int temp = n;

    while(temp > 0) {
      reversed *= 10;
      reversed += temp%10;
      temp /= 10;
    }

    return reversed == n;
  }

  // Function that returns the largest palindrome made from the product of 3-digit numbers
  static int largestPalindrome() {
    int largest = 0;

    // Iterate through all combinations of three-digit numbers
    // j = i rather than 100 to reduce repetition
    for(int i = 100; i < 1000; i++) {
      for(int j = i; j < 1000; j++) {
        if(isPalindrome(i*j) && i*j > largest) largest = i*j;
      }
    }

    // Return largest found palindrome
    return largest;
  }

  public static void main(String args[]) {
    System.out.println("Largest palindrome is " + largestPalindrome());
  }
}
