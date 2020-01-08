// Challenge 9

/* There exists exactly one Pythagorean triplet for which a+b+c = 1000.
 * Find the product abc.
 */

public class challenge9 {
  // A function to find the Pythagorean triplet which sums to 1000
  static void pythagoreanTripletToN(int n) {
    boolean found = false;

    // Find the pythagorean triplet
    for(int a = 1; a < n-1; a++) {
      for(int b = a; b < n-1; b++) {
        double c = Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
        if(c % 1 == 0) {
          System.out.println(a + " " + b);
          if(a + b + c == n) {
            System.out.println("This is it! Product is: " + (a*b*(int)c));
            found = true;
            break;
          }
        }
      }
      if(found) break;
    }
  }


  public static void main(String args[]) {
    pythagoreanTripletToN(1000);
  }
}
