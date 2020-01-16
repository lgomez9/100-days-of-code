// Challenge 17

// If the numbers 1 to 5 are written out in words: one, two, three, four, five,
// then there are 3+3+5+4+4 = 19 letters used in total.
// If all the numbers from 1 to 1000 (one thousand) inclusive were written out
// in words, how many letters would be used?

// NOTE: Do not count spaces or hyphens. For example, 342 (three hundred and forty-two)
// contains 23 letters and 115 (one hundred and fifteen) contains 20 letters. The use of
// "and" when writing out numbers is in compliance with British usage.

public class challenge17 {
  // Function to find the length of the given number
  static int letterLength(int n) {
    int length = 0;
    String[] digits = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    String[] tens = {"", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    String[] teens = {"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};

    if(n < 1000) {
      if(n >= 100) {
        // System.out.println("n >= 100:" + digits[n/100]);
        length += digits[n/100].length() + "hundred".length();
        if(n % 100 != 0) length += "and".length();
        n %= 100;
      }

      if(n >= 20) {
        // System.out.println("n >= 20:" + tens[n/10]);
        length += tens[n/10].length();
        n %= 10;
      }

      if(n >= 10) {
        // System.out.println("n >= 10:" + teens[n/10]);
        length += teens[n%10].length();
        return length;
      }

      // System.out.println(digits[n]);
      length += digits[n].length();
    } else length = "onethousand".length();

    return length;
  }

  // Function to find the sum of the letters up to 1000
  static long totalLetters() {
    long sum = 0;

    for(int i = 1; i <= 1000; i++) sum += letterLength(i);

    return sum;
  }

  public static void main(String args[]) {
    // for(int i = 1; i <= 10; i++) System.out.println(i + " has " + letterLength(i) + " letters.");
    System.out.println("100 has " + letterLength(100) + " letters.");
    System.out.println("Up to 1000 has " + totalLetters() + " letters.");
  }
}
