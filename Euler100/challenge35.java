// Challenge 35

/* The number 197 is called a circular prime because all rotations of the digits:
 * 197, 971, and 719, are themselves prime.
 *
 * There are thirteen such primes below 100: 2, 3, 5, 7, 11, 13, 17, 31, 37, 71, 73, 79, and 97.
 *
 * How many circular primes are there below one million?
 *
 */

public class challenge35 {
  // Function to find the primes up through n (returned array returns false for prime numbers)
  static boolean[] primesThroughN(int n) {
    boolean[] composite = new boolean[n+1];
    composite[0] = true;
    composite[1] = true;

    for(int i = 2; i < composite.length; i++) {
      if(composite[i]) continue;
      for(int step = 2; i * step < composite.length; step++) composite[i*step] = true;
    }

    return composite;
  }

  // Function to convert an integer to an int array
  static int[] intToArr(int n) {
    String temp = Integer.toString(n);
    int[] arr = new int[temp.length()];
    for(int i = 0; i < temp.length(); i++) arr[i] = temp.charAt(i) - '0';
    return arr;
  }

  // Function to convert an int array to an int
  static int arrToInt(int[] arr) {
    int place = 1;
    int ret = 0;

    for(int i = arr.length-1; i >= 0; i--) {
      ret += arr[i]*place;
      place *= 10;
    }

    return ret;
  }

  // Function to rotate an array
  static void rotArr(int[] arr) {
    int temp = arr[0];
    for(int i = 0; i < arr.length-1; i++) arr[i] = arr[i+1];
    arr[arr.length-1] = temp;
  }

  // Function to check if a number is a circular prime
  static boolean isCircularPrime(int n, boolean[] composite) {
    int[] nArr = intToArr(n);

    // If a single digit and prime, return true
    if(nArr.length == 1 && !composite[n]) return true;

    // If not prime, return false
    if(composite[n]) return false;

    // If not a single digit, check if the rotations work
    do {
      rotArr(nArr);
      if(composite[arrToInt(nArr)]) return false;
    } while(arrToInt(nArr) != n);

    return true;
  }

  // Function to print out an array
  static void printArr(int[] arr) {
    System.out.print("[ ");
    for(int i = 0; i < arr.length; i++) System.out.print(arr[i] + " ");
    System.out.println("]");
  }

  // Returns the number of circular primes below n
  static int circularPrimesBelowN(int n) {
    boolean[] composite = primesThroughN(n);
    int num = 0;
    for(int i = 0; i <= n; i++) {
      if(isCircularPrime(i, composite)) num++;
    }
    return num;
  }


  public static void main(String args[]) {
    System.out.println("There are " + circularPrimesBelowN(100) + " circular primes below 100");
    System.out.println("There are " + circularPrimesBelowN(1000000) + " circular primes below 1000000");
  }
}
