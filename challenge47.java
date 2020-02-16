// Challenge 47: Distinct prime factors
import java.util.*;

/* The first two consecutive numbers to have two distinct prime factors are:
 *
 * 14 = 2 x 7
 * 15 = 3 x 5
 *
 * The first three consecutive numbers to have three distinct prime factors are:
 *
 * 644 = 2^2 x 7 x 23
 * 645 = 3 x 5 x 43
 * 646 = 2 x 17 x 19
 *
 * Find the first four consecutive integers to have four distinct prime factors each.
 * What is the first of these numbers?
 *
 */

public class challenge47 {
  // Function to find the prime factors of a number
  static HashSet<Integer> primeFactorization(int n) {
    // Create a set to hold the prime factors
    HashSet<Integer> primeFactors = new HashSet<>();

    // Starting with two, divide out numbers as much as possible
    for(int i = 2; i <= n; i++) {
        if(n % i == 0) primeFactors.add(i);
        while(n % i == 0) n /= i;
    }

    return primeFactors;
  }

  // Function to print the prime factors of a number
  static void printPrimeFactors(int n) {
    // Get the prime factors
    HashSet<Integer> primeFactors = primeFactorization(n);

    // Print out the factors
    System.out.print("Prime factors of " + n + " are: ");
    for(Integer i : primeFactors) {
      System.out.print(i + " ");
    }
    System.out.println();
  }

  // Function to find the first four consecutive integers to have four distinct prime factors each
  // Returns the first one
  static int nConsecutiveNPrimeFactors(int n) {
    int i = 1;
    boolean found = false;

    while(!found) {
      if(primeFactorization(i).size() != n) {
        i++;
        continue;
      }

      boolean works = true;
      for(int j = 1; j < n; j++) {
        if(primeFactorization(i+j).size() != n) works = false;
      }

      if(works) found = true;
      i++;
    }

    return i-1;
  }

  // Function to print n consecutive integers from start
  static void printConsecutiveInts(int start, int n) {
    for(int i = start; i < start+n; i++) System.out.print(i + " ");
    System.out.println();
  }

  public static void main(String args[]) {
    // Try 14, 15, 644, 645, and 646
    printPrimeFactors(14);
    printPrimeFactors(15);
    printPrimeFactors(644);
    printPrimeFactors(645);
    printPrimeFactors(646);

    // Check if nConsecutiveNPrimeFactors works for 2 and 3
    System.out.print("The first two consecutive numbers to have two distinct prime factors are ");
    printConsecutiveInts(nConsecutiveNPrimeFactors(2), 2);

    System.out.print("The first three consecutive numbers to have three distinct prime factors are ");
    printConsecutiveInts(nConsecutiveNPrimeFactors(3), 3);

    System.out.print("The first four consecutive numbers to have four distinct prime factors are ");
    printConsecutiveInts(nConsecutiveNPrimeFactors(4), 4);


  }
}
