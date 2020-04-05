// Challenge 57: Square root convergents
import java.util.*;
import java.io.*;
import java.math.*;

/* It is possible to show that the square root of two can be expressed as an
 * infinite continued fraction:
 *
 * sqrt(2) = 1 + 1/(2 + 1/(2 + 1/(...)))
 *
 * By expanding this for the first four iterations, we get:
 *
 * 1 + 1/2 = 3/2 = 1.5
 * 1 + 1/(2 + 1/2) = 7/5 = 1.4
 * 1 + 1/(2 + 1/(2+1/2)) = 17/12 = 1.41666....
 * 1 + 1/(2 + 1/(2 + 1/(2 + 1/2))) = 41/29 = 1.41379...
 *
 * The next three expansions are 99/70, 239/169, and 577/408, but the eighth expansion,
 * 1393/985, is the first example where the number of digits in the numerator exceeds
 * the number of digits in the denominator.
 *
 * In the first one-thousand expansions, how many fractions contain a numerator
 * with more digits than the denominator.
 */

class Fraction {
  BigInteger num;
  BigInteger den;

  Fraction(BigInteger n, BigInteger d) {
    num = n;
    den = d;
  }

  void printFraction(String msg) {
    System.out.println(msg + num + "/" + den);
  }
}

public class challenge57 {
  // Function that returns if numerator has more digits than denominator
  static boolean moreDigits(Fraction f) {
    BigInteger a = f.num;
    BigInteger b = f.den;

    while(a.compareTo(BigInteger.ZERO) > 0 && b.compareTo(BigInteger.ZERO) > 0) {
      a = a.divide(BigInteger.TEN);
      b = b.divide(BigInteger.TEN);
    }

    return a.compareTo(BigInteger.ZERO) > 0;
  }

  // Get all added fractions of the expansion of sqrt(2) up to nth iteration
  static ArrayList<Fraction> getFractions(int n) {
    ArrayList<Fraction> addedFractions = new ArrayList<>();

    addedFractions.add(new Fraction(BigInteger.valueOf(1), BigInteger.valueOf(2)));

    for(int i = 1; i < n; i++) {
      Fraction prevFraction = addedFractions.get(i-1);
      Fraction currFraction = new Fraction(prevFraction.den, prevFraction.den.multiply(BigInteger.valueOf(2)).add(prevFraction.num));
      addedFractions.add(currFraction);
    }

    return addedFractions;
  }

  // Function that takes a given fraction and returns what the actual iteration fraction would be
  static Fraction iterationFraction(Fraction f) {
    return new Fraction(f.num.add(f.den), f.den);
  }

  // Function that returns a list of the fractions for iterations up to n
  static ArrayList<Fraction> getIterations(int n) {
    ArrayList<Fraction> addedFractions = getFractions(n);
    ArrayList<Fraction> iterations = new ArrayList<>();

    for(int i = 0; i < n; i++) {
      iterations.add(iterationFraction(addedFractions.get(i)));
    }

    return iterations;
  }

  // Function that returns the number of iterations that have numerators with more digits than denominators up to n
  static int moreInNum(int n) {
    int count = 0;
    ArrayList<Fraction> iterations = getIterations(n);

    for(int i = 0; i < iterations.size(); i++) {
      if(moreDigits(iterations.get(i))) count++;
    }

    return count;
  }

  public static void main(String args[]) {
    System.out.println("234 has more digits than 15: " + moreDigits(new Fraction(BigInteger.valueOf(234), BigInteger.valueOf(15))));
    System.out.println("234 has more digits than 153: " + moreDigits(new Fraction(BigInteger.valueOf(234), BigInteger.valueOf(153))));

    ArrayList<Fraction> upTo8 = getIterations(8);

    for(int i = 0; i < upTo8.size(); i++) {
      upTo8.get(i).printFraction("Iteration " + (i+1) + ": ");
    }

    System.out.println("Up to 8 iterations, " + moreInNum(8) + " iteration(s) have more digits in their numerator than their denominator.");

    try {
      ArrayList<Fraction> upTo100 = getIterations(100);
      PrintStream o = new PrintStream(new File("p57out.txt"));
      PrintStream console = System.out;
      System.setOut(o);


      for(int i = 0; i < upTo100.size(); i++) {
        upTo100.get(i).printFraction("Iteration " + (i+1) + ": ");
      }

      System.setOut(console);
      System.out.println("Up to 100 iterations, " + moreInNum(100) + " iteration(s) have more digits in their numerator than their denominator.");
      System.out.println("Up to 1000 iterations, " + moreInNum(1000) + " iteration(s) have more digits in their numerator than their denominator.");
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}
