// Challenge 39
import java.util.*;
/* If p is the perimeter of a right triangle with integral length sides, {a,b,c},
 * there are exactly three solutions for p = 120.
 *
 * {20, 48, 52}, {24, 45, 51}, {30, 40, 50}
 *
 * For which value of p <= 1000, is the number of solutions maximized?
 *
 */

public class challenge39 {
  // Know that a^2 + b^2 = c^2 and a + b + c = p
  // Rearranging, can get b = (p^2-2ap)/(2p-2a)

  // Function to find the number of solutions for a triangle with perimeter p
  static int solutionsForP(int p) {
    // Variable to contain the number of solutions
    int solutions = 0;

    for(long a = 2; a < p/3; a++) {
      if((p*p-2*a*p) % (2*p-2*a) == 0) solutions++;
    }

    return solutions;
  }

  // Function to find the max number of solutions for a triangle with perimeter up to p
  static int maxSolutionsForTri(int p) {
    int maxSolutions = Integer.MIN_VALUE;
    int solution = 0;

    for(int i = 2; i <= p; i++) {
      int solutions = solutionsForP(i);
      if(solutions > maxSolutions) {
        maxSolutions = solutions;
        solution = i;
      }
    }

    return solution;
  }

  public static void main(String args[]) {
    System.out.println("There are " + solutionsForP(120) + " solutions for a triangle with perimeter 120");
    System.out.println("The maximum number of solutions for p <= 1000 is " + maxSolutionsForTri(1000));
  }
}
