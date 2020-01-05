// Problem 3

/* The prime factors of 13195 are 5, 7, 13, and 29.
   What is the largest prime factor of the number 600851475143?
*/

public class challenge3 {
  // Function to print out the prime factors of a given number
  static void printPrimeFactors(long n) {
    System.out.print("Prime factors of " + n + " are: ");

    // Starting from 2, divide out the number as much as you can
    for(long i = 2; i < Math.sqrt(n); i ++) {
      // Divide out the prime number as much as possible
      while(n % i == 0) {
        System.out.print(i + " ");
        n /= i;
      }
    }

    // Handle the case where n is greater than 2 after division
    if(n > 2) System.out.println(n);
    else System.out.println();
  }


  public static void main(String args[]) {
    // Try it with the given example
    printPrimeFactors(13195L);

    // Try it with the challenge
    printPrimeFactors(600851475143L);
  }
}
