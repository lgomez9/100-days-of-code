// Challenge 14
//
// The following iterative sequence is defined for the set of positive integers:
//   n -> n/2 (n is even)
//   n -> 3n+1 (n is odd)
//
// Using the rule above and starting with 13, we generate the following sequence:
//
//     13 -> 40 -> 20 -> 10 -> 5 -> 16 -> 8 -> 4 -> 2 -> 1
//
// It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms.
// Although it has not been proven yet (Collatz Problem) it is thought that all starting
// numbers finish at 1.
//
// Which starting number, under one million, produces the longest chain?
// Once the chain starts the terms are allowed to go above one million.

public class challenge14 {
  // Function to find the longest Collatz chain up to 1 million.
  // N is the upper limit to the starting numbers
  static long longestChain(int n) {
    long longestIndex = 0;
    long longestLength = Integer.MIN_VALUE;
    long currLength;

    for(int i = 2; i < n; i++) {
      currLength = chainLength(i);
      if(currLength > longestLength) {
        longestIndex = i;
        longestLength = currLength;
      }
    }

    return longestIndex;
  }


  // Function to find the length of the Collatz chain
  static long chainLength(long n) {
    // Variable to hold the length of the chain
    long count = 1;

    // Continue until we reach one.
    while(n != 1) {
      if(n % 2 == 0) n = evenChain(n);
      else n = oddChain(n);
      count++;
    }

    return count;
  }

  // Function to change the odd numbers
  static long oddChain(long n) {
    return 3*n + 1;
  }

  // Function to change the even numbers
  static long evenChain(long n) {
    return n/2;
  }

  public static void main(String args[]) {
    System.out.println("The length of the Collatz chain starting at 13 is " + chainLength(13));
    System.out.println("The longest start for numbers up to 3 is " + longestChain(3));
    System.out.println("The longest start for numbers up to 1000000 is " + longestChain(1000000));
  }
}
