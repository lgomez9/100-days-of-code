// Challenge 42
import java.util.*;
import java.io.*;

/* The nth term of the sequence of triangle numbers is given by t_n = (1/2)n(n+1)
 * So the first ten triangle numbers are: 1, 3, 6, 10, 15, 21, 28, 36, 45, 55
 * By converting each letter in a word to a number corresponding to its alphabetical
 * position and adding these values we form a word value.
 * For example, the word value for SKY is 19 + 11 + 25 = 55 = t_10. If the word
 * value is a triangle number then we shall call the word a triangle word.
 *
 * Using words.txt, a 16K text file containing nearly two-thousand common English
 * words, how many are triangle words?
 *
 */

 public class challenge42 {
   // Function to get the word value for a String
   static int wordValue(String s) {
     int value = 0;

     for(int i = 0; i < s.length(); i++) {
       value += s.charAt(i) - 'A' + 1;
     }

     return value;
   }

   // Function that returns the nth triangle number
   static int nthTriangle(int n) {
     return  (n * (n+1))/2;
   }

   // Function that returns the triangle numbers up to n
   static ArrayList<Integer> triangleNumbers(int n) {
     int i = 1;
     ArrayList<Integer> triangles = new ArrayList<>();

     while(nthTriangle(i) <= n) {
       triangles.add(nthTriangle(i++));
     }

     return triangles;
   }

   public static void main(String args[]) {

     System.out.println("Word value of SKY is " + wordValue("SKY"));

     ArrayList<String> words = new ArrayList<>();

     try {
       Scanner scan = new Scanner(new File("p042_words.txt"));
       scan.useDelimiter(",");

       // Put all the names in the words array list.
       while(scan.hasNext()) words.add(scan.next().replaceAll("\"", ""));

       // Sort the list!
       words.sort((String s1, String s2) -> s1.length() - s2.length());

       // Just print out the largest word
       System.out.println("Longest word is " + words.get(words.size()-1).length() + " characters long!");

       // The max triangle number we have to get to is thus that size
       int maxTriangle = (words.get(words.size()-1).length()) * 26;

       // Get the triangle numbers up to maxTriangle
       ArrayList<Integer> triangles = triangleNumbers(maxTriangle);
       triangles.forEach((triangle) -> System.out.println(triangle));

       // Find the number of triangle words in words.txt
       int numOfTriangles = 0;
       for(String word : words) {
         if(triangles.contains(wordValue(word))) numOfTriangles++;
       }

       System.out.println("There are " + numOfTriangles + " triangle words in words.txt");
     } catch(Exception e) {
       System.out.println(e);
     }
   }
 }
