// Challenge 41

/* We shall say that an n-digit number is pandigital if it makes use of all the
 * digits 1 to n exactly once. For example, 2143 is a 4-digit pandigital and is
 * also prime.
 *
 * What is the largest n-digit pandigital prime that exists?
 */

public class challenge41 {
  // Function that returns whether a number is n-digit pandigital or not
  static boolean isNDigitPandigital(int num, int n) {
    boolean[] digits = new boolean[n];

    if(num % 10 == 0) return false;

    // Go through the number
    while(num > 0) {
      if(num % 10 > n) return false;
      if(num % 10 == 0) return false;
      if(digits[(num%10)-1]) return false;
      digits[(num%10)-1] = true;

      num /= 10;
    }

    return true;
  }

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

  // Function to find the largest n-digit pandigital prime that exists
  static int largestNDigitPandigitalPrime(boolean[] composite) {
    int currPlace = 1;
    int currNumDigits = 1;
    int largest = 0;

    for(int i = 1; i <= 9; i++) {
      for(int j = currPlace; j < currPlace*10; j++) {
        if(!composite[j] && isNDigitPandigital(j, currNumDigits)) largest = j;
      }
      currNumDigits++;
      currPlace *= 10;
    }

    return largest;
  }

  public static void main(String args[]) {
    System.out.println("2143 is a 4-digit pandigital: " + isNDigitPandigital(2143, 4));
    boolean[] composite = primesThroughN(999999999);

    System.out.println("The largest n-digit pandigital prime is " + largestNDigitPandigitalPrime(composite));
  }
}
