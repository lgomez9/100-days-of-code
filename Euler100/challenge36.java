// Challenge 36

/* The decimal number, 585 = 1001001001 (binary) is palindromic in both bases
 * Find the sum of all numbers, less than one million, which are palindromic in
 * base 10 and base 2.
 */

public class challenge36 {
  // Function to convert an integer to an int array
  static int[] intToArr(int n) {
    String temp = Integer.toString(n);
    int[] arr = new int[temp.length()];
    for(int i = 0; i < temp.length(); i++) arr[i] = temp.charAt(i) - '0';
    return arr;
  }

  // Function to return whether a string or array is a palindrome
  static boolean isPalin(String s) {
    int low = 0;
    int high = s.length()-1;

    while(low < high) {
      if(s.charAt(low++) != s.charAt(high--)) return false;
    }

    return true;
  }

  static boolean isPalin(int[] arr) {
    int low = 0;
    int high = arr.length-1;

    while(low < high) {
      if(arr[low++] != arr[high--]) return false;
    }

    return true;
  }

  // Find all the numbers below n that are palindromic in binary and decimal
  static long doublyPalin(int n) {
    long sum = 0;

    for(int i = 0; i < n; i++) {
      if(isPalin(intToArr(i)) && isPalin(Integer.toBinaryString(i))) {
          sum += i;
      }
    }

    return sum;
  }

  public static void main(String args[]) {
    System.out.println("585 (decimal) is a palindrome: " + isPalin(intToArr(585)));
    System.out.println("585 (binary) is a palindrome: " + isPalin(Integer.toBinaryString(585)));

    System.out.println("The sum of numbers below 1000000 that are palindromic in binary and decimal is " + doublyPalin(1000000));
  }
}
