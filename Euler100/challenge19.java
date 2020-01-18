// Challenge 19

/* You are given the following information, but you may prefer to do some research for yourself.
 * 1 Jan 1900 was a Monday.
 * Thirty days has September, April, June, and November.
 * All the rest have thirty-one,
 * Saving February alone,
 * which has twenty-eight, rain or shine.
 * And on leap years, twenty-nine.
 * A leap year occurs on any year evenly divisible by 4, but not on a century unless
 * it is divisible by 400.
 *
 * How many Sundays fell on the first of the month during the 20th century (1 Jan 1901 to 31 Dec 2000)
 */

public class challenge19 {
  // Create a function to check if the year is a leap year
  static boolean isLeap(int y) {
    return y % 4 == 0 && (y % 100 != 0 || y % 400 == 0);
  }

  // Create a function to see how many Sundays fell on the first of the month during the twentieth century
  static int sundaysOnFirst() {
    // Count the sundays on first
    int sundayCount = 0;
    // String array holding the days starting at Monday!
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
    // int array holding the month lengths starting with January
    // January 31, February 28/29, March 31, April 30, May 31, June 30
    // July 31, August 31, September 30, October 31, November 30, December 31
    int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int year;
    int currDay = 0;

    for(year = 1900; year <= 2000; year++) {
      // Set February up
      if(isLeap(year)) months[1] = 29;
      else months[1] = 28;

      // Iterate through the months
      for(int i = 0; i < months.length; i++) {
        // Iterate through days of the month
        for(int j = 0; j < months[i]; j++) {
          if(year >= 1901 && j == 0 && days[currDay%7].equals("Sunday")) sundayCount++;
          currDay++;
        }
      }
    }

    return sundayCount;
  }


  public static void main(String args[]) {
    System.out.println("There are " + sundaysOnFirst() + " Sundays on the first of the month during the 20th century.");
  }
}
