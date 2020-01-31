// Challenge 31

/* In the United Kingdom the currency is made up of pound (lb) and pence (p).
 * There are eight coins in general circulation:
 *
 * 1p, 2p, 5p, 10p, 20p, 50p, 100p (1lb) and 200p (2lb)
 *
 * It is possible to make 2lb in the following way:
 *
 * 1 x 1lb + 1 x 50p + 2 x 20p + 1 x 5p + 1 x 2p + 3 x 1p
 *
 * How many different ways can 2lb be made using any number of coins?
 *
 */

public class challenge31 {
  // Function to calculate the number of different ways 2lb can be made
  static long numOfWays() {
    // Create an array to hold the different values
    long[] combos = new long[201];

    // Fill an array with the coin values
    long[] coins = {1, 2, 5, 10, 20, 50, 100, 200};

    // Set the base case
    combos[0] = 1;

    // Iterate through the different values, and combine
    for(int i = 0; i < coins.length; i++) {
      for(int j = (int) coins[i]; j < combos.length; j++) {
        combos[j] += combos[j- (int) coins[i]];
      }
    }

    return combos[combos.length-1];
  }

  public static void main(String args[]) {
    System.out.println("The number of ways to combine coins to get to 200p is " + numOfWays());
  }
}
