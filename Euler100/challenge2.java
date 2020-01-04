// Challenge 2: Even Fibonacci Numbers

/* By considering the terms in the Fibonacci sequence whose values do not exceed
 * four million, find the sum of the even-valued terms.
 */

public class challenge2 {
  // Function to find the term in fibonacci that does not exceed 4 million
  static int fourMillionthTerm() {
    // Some slapped together Fibonacci to find the correct term
    int prev = 1;
    int next = 2;
    int temp;
    int n = 1;

    while(next < 4000000) {
      temp = prev;
      prev = next;
      next = temp + prev;
      n++;
    }

    return n;
  }

  // Function to find the sum of the even-valued terms in Fibonacci
  static int fibEvenSum(int n) {
    // Set up our dynamic programming to get the values of the Fibonacci sequence
    int[] memo = new int[n];

    memo[0] = 1;
    memo[1] = 2;

    for(int i = 2; i < n; i++) {
      memo[i] = memo[i-1] + memo[i-2];
    }

    // Iterate back through the array, now looking for only the even terms
    int evenSum = 0;
    for(int i = 0; i < n; i++) {
      if(memo[i] % 2 == 0) evenSum += memo[i];
    }

    return evenSum;
  }

  public static void main(String args[]) {
    System.out.println("The " + fourMillionthTerm() + " term is the last term under 4 million.");
    System.out.println("The even sum for the first ten terms is: " + fibEvenSum(10));
    System.out.println("The even sum for the first 32 terms is: " + fibEvenSum(32));
  }
}
