import java.util.*;
import java.io.*;
// Challenge 22

// Using names.txt, a 46K text file containing over five-thousand first names,
// begin by sorting it into alphabetical order. Then working out the alphabetical
// value for each name, multiply this value by its alphabetical position in the
// list to obtain a name score.

// For example, when the list is sorted into alphabetical order, COLIN, which is
// worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list. So, COLIN
// would obtain a score of 938 x 53 = 49714.

// What is the total of all the name scores in the file?

public class challenge22 {
  // Find the alphabetical value of a name
  static int alphaValue(String n) {
    int value = 0;

    for(int i = 0; i < n.length(); i++) {
      value += n.charAt(i) % 64;
    }

    return value;
  }

  // Find the name score of the given name
  static long nameScore(String n, int index) {
    return (long) (alphaValue(n) * index);
  }

  // Function to find the sum of the name scores in a given list
  static long nameScoreSum(ArrayList<String> a) {
    long sum = 0;

    for(int i = 0; i < a.size(); i++) {
      sum += nameScore(a.get(i), i+1);
    }

    return sum;
  }

  public static void main(String args[]) {
    // Create an array list containing the names in the file
    ArrayList<String> names = new ArrayList<>();

    // Check to make sure the alphaValue function works
    System.out.println("Alphabetical value of COLIN is " + alphaValue("COLIN"));

    // Check to make sure the nameScore function works
    System.out.println("Name score of COLIN at 938 is " + nameScore("COLIN", 938));

    try {
      // Create a Scanner object to read into the ArrayList
      Scanner scan = new Scanner(new File("p022_names.txt"));
      scan.useDelimiter(",");

      // Iterate through the file, adding names to the ArrayList
      while(scan.hasNext()) names.add(scan.next().replaceAll("\"", ""));

      // Sort the ArrayList!
      Collections.sort(names);

      // Check to make sure COLIN is in the right location
      System.out.println("Index of COLIN is " + (names.indexOf("COLIN")+1));

      // Sum together all the name scores in the file
      System.out.println("Sum of all the name scores is " + nameScoreSum(names));

    } catch(Exception e) {
      System.out.println(e);
    }
  }
}
