// Challenge 58
import java.lang.*;
import java.util.*;

/*
 * Starting with 1 and spiraling anticlockwise, a square spiral with side length 7 is formed.
 * It is interesting to note that the odd squares lie along the bottom right diagonal, but
 * what is more interesting is that 8 out of 13 numbers lying along both diagonals are prime;
 * that is, a ration of 8/13 = 63%
 *
 * If one complete new layer is wrapped around the spiral above, a square spiral with
 * side length 9 will be formed. If this process is continued, what is the side length of
 * the square spiral for which the ration of primes along both diagonals first falls below
 * 10%?
 */


public class challenge58 {
  // Function that returns whether n is prime
  static boolean isPrime(int n) {
    for(int i = 2; i <= Math.sqrt(n); i++) {
      if(n % i == 0) return false;
    }
    return true;
  }

  // Function to find a ratio given two ints
  static double ratio(int a, int b) {
    return (double) a / (double) b;
  }


  // Function to find the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%
  static int findLengthBelow10() {
    int currLength = 7;
    int diagonals = 13;
    int primes = 8;
    System.out.println("Ratio of primes in diagonals for side length 7 is " + ratio(primes, diagonals));

    while(ratio(primes, diagonals) > .10) {
      currLength += 2;
      diagonals += 4;
      for(int i = 0; i < 4; i++) {
        int currDiagonal = currLength*currLength - i*(currLength-1);
        if(isPrime(currDiagonal)) primes++;
      }
    }

    return currLength;
  }

  public static void main(String args[]) {
    // Find the side length of the square spiral for which the ratio of primes along both diagonals first falls below 10%
    System.out.println("The side length of the square spiral for which the ratio of primes along both diagonals first falls below 10% is " + findLengthBelow10());

  }
}
