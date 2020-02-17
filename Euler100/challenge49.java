// Challenge 49: Prime permutations
import java.util.*;
/* The arithmetic sequence, 1487, 4817, 8147, in which each of the terms increases by
 * 3330, is unusual in two ways: (i) each of the three terms are prime, and (ii) each of the
 * 4-digit numbers are permutations of one another.
 *
 * There are no arithmetic sequences made up of three 1-, 2-, or 3-digit primes,
 * exhibiting this property, but there is one other 4-digit increasing sequence.
 *
 * What 12-digit number do you form by concatenating the three terms in this sequence?
 */

public class challenge49 {
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

  // Function that tells you whether two numbers are permutations of each other
  static boolean arePermutations(int a, int b) {
    int[] digits = new int[10];

    while(a > 0) {
      digits[a%10]++;
      a /= 10;
    }

    while(b > 0) {
      digits[b%10]--;
      b /= 10;
    }

    for(int i = 0; i < digits.length; i++) if(digits[i] != 0) return false;
    return true;
  }

  // Function to find the two 3-term arithmetic sequences that are (i) all prime and (ii) all permtutations
  static ArrayList<List<Integer>> primePermutations(boolean[] composite) {
    // Create the list you're going to return
    ArrayList<List<Integer>> permutationLists = new ArrayList<List<Integer>>();

    for(int i = 1000; i < 9999; i++) {
      // All the possible differences
      for(int d = 1; d < 9000; d++) {
        int first = i;
        int second = i+d;
        int third = i+2*d;

        // Check to make sure you don't go outside the bounds
        if(second > 9999 || third > 9999) break;

        // Check if the three are all prime
        if(composite[first] || composite[second] || composite[third]) continue;

        // Check if the three are permutations of each other
        if(!arePermutations(first, second) || !arePermutations(first, third)) continue;

        // If past all those points, make a new List and add it to the permutations list
        ArrayList<Integer> newPerm = new ArrayList<>();
        newPerm.add(first);
        newPerm.add(second);
        newPerm.add(third);

        // Add the new permutation list to permutation lists
        permutationLists.add(newPerm);
      }
    }

    return permutationLists;
  }

  public static void main(String args[]) {
    // Get the primes through 5 digits
    boolean[] composite = primesThroughN(10000);

    System.out.println("1487, 4817, and 8147 are all permutations of each other is " + (arePermutations(1487, 4817) && arePermutations(1487, 8147)));

    // Find the two sequences that are both (i) prime and (ii) permutations of one another
    ArrayList<List<Integer>> perms = primePermutations(composite);

    for(List<Integer> lst : perms) {
      System.out.print("One permutation list: ");
      for(Integer i : lst) {
        System.out.print(i + " ");
      }
      System.out.println();
    }
  }
}
