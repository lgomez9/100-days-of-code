// Challenge 60: Prime Pair Sets
import java.util.*;

/* The primes 3, 7, 109, and 673, are quite remarkable. By taking any two primes
 * concatenating them in any order the result will always be prime. For example,
 * taking 7 and 109, both 7109 and 1097 are prime. The sum of these four primes,
 * 792, represents the lowest sum for a set of four primes with this property.
 *
 * Find the lowest sum for a set of five primes for which any two primes
 * concatenate to produce another prime.
 *
 */

public class challenge60 {
  // Function to find the primes up to n
  static boolean[] findPrimes(int n) {
    // Find all the primes below n using the Sieve of Eratosthenes
    boolean[] composite = new boolean[n+1];
    composite[0] = true;
    composite[1] = true;

    for(int i = 2; i < composite.length; i++) {
      if(composite[i]) continue;
      for(int step = 2; i * step < composite.length; step++) composite[i*step] = true;
    }

    return composite;
  }

  // Function to return if a number is prime
  static boolean isPrime(int n) {
    if(n % 2 == 0) return false;
    for(int i = 3; i < Math.sqrt(n); i += 2) {
      if(n % i == 0) return false;
    }
    return true;
  }

  // A function that concatenates two numbers
  static int concat(int a, int b) {
    int tmp = b;

    while(tmp > 0) {
      a *= 10;
      tmp /= 10;
    }

    return a + b;
  }

  // A function that checks if two numbers concatenated are prime
  static boolean concatToPrime(int a, int b) {
    return isPrime(concat(a, b)) && isPrime(concat(b, a));
  }

  // A function that creates the list of primes that concatenate for n
  static ArrayList<Integer> getConcatPrimes(boolean[] composite, int n) {
    ArrayList<Integer> primes = new ArrayList<>();

    for(int m = n+1; m < composite.length; m++) {
      if(concatToPrime(m, n)) primes.add(m);
    }

    return primes;
  }

  public static void main(String args[]) {
    // Guessed upper limit is 20000
    boolean[] composite = findPrimes(20000);

    // Check concatenation function
    System.out.println("7 and 109 concatenated is " + concat(7, 109));
    System.out.println("109 and 7 concatenated is " + concat(109, 7));

    int lowestSum = Integer.MAX_VALUE;
    int currSum;

    // Create a list of the primes that concatenate to primes for each prime
    HashMap<Integer, List<Integer>> primes = new HashMap<>();

    // Brute force!
    for(int a = 1; a < composite.length; a++) {
      if(composite[a]) continue;
      if(!primes.containsKey(a)) primes.put(a, getConcatPrimes(composite, a));

      for(int b = a+1; b < composite.length; b++) {
        if(composite[b]) continue;
        if(!primes.get(a).contains(b)) continue;
        if(!primes.containsKey(b)) primes.put(b, getConcatPrimes(composite, b));

        for(int c = b+1; c < composite.length; c++) {
          if(composite[c]) continue;
          if(!primes.get(a).contains(c) || !primes.get(b).contains(c)) continue;
          if(!primes.containsKey(c)) primes.put(c, getConcatPrimes(composite, c));

          for(int d = c+1; d < composite.length; d++) {
            if(composite[d]) continue;
            if(!primes.get(a).contains(d) || !primes.get(b).contains(d) || !primes.get(c).contains(d)) continue;
            if(!primes.containsKey(d)) primes.put(d, getConcatPrimes(composite, d));
            for(int e = d+1; e < composite.length; e++) {
              if(composite[e]) continue;
              if(!primes.get(a).contains(e) || !primes.get(b).contains(e) ||
                 !primes.get(c).contains(e) || !primes.get(d).contains(e)) continue;

              currSum = a + b + c + d + e;
              System.out.println(a + " + " + b + " + " + c + " + " + d + " + " + e + " = " + currSum);
              if(currSum < lowestSum) lowestSum = currSum;
            }
          }
        }
      }
    }

    System.out.println("The lowest sum for a set of five primes for which any two primes concatenate to produce another prime is " + lowestSum);
  }
}
