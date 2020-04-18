// Challenge 59
import java.lang.*;
import java.io.*;
import java.util.*;

/* Each character on a computer is assigned a unique code and the preferred standard
 * is ASCII (American Standard Code for Information Interchange). For example,
 * uppercase A = 65, asterisk (*) = 42, and lowercase k = 107.
 *
 * A modern encryption method is to take a text file, convert the bytes to ASCII,
 * then XOR each byte with a given value, taken from a secret key. The advantage
 * with the XOR function is that using the same encryption key on the cipher text,
 * restores the plain text; for example, 65 XOR 42 = 107, then 107 XOR 42 = 65.
 *
 * For unbreakable encryption, the key is the same length as the plain text message,
 * and the key is made up of random bytes. The user would keep the encrypted message
 * and the encryption key in different locations, and without both "halves", it is
 * impossible to decrypt the message.
 *
 * Unfortunately, this method is impractical for most users, so the modified method
 * is to use a password as a key. If the password is shorter than the message, which
 * is likely, the key is repeated cyclically throughout the message. The balance for
 * this method is using a sufficiently long password key for security, but short enough
 * to be memorable.
 *
 * Your task has been made easy, as the encryption key consists of three lower case
 * characters. Using p059_cipher.txt (right click and 'Save Link/Target As...'),
 * a file containing the encrypted ASCII codes, and the knowledge that the plain
 * text must contain common English words, decrypt the message and find the sum of
 * the ASCII values in the original text.
 *
 */

public class challenge59 {
  // Function to convert a String to an array of integers
  static int[] strToIntArr(String s) {
    int[] newArr = new int[s.length()];
    for(int i = 0; i < s.length(); i++) {
      newArr[i] = s.charAt(i);
    }
    return newArr;
  }

  // Function to convert an array of integers to a String
  static String intArrToStr(int[] arr) {
    StringBuilder newString = new StringBuilder();
    for(int i = 0; i < arr.length; i++) newString.append((char) arr[i]);
    return newString.toString();
  }

  // Function to encrypt (or decrypt) a message given a three-letter key (int for ASCII)
  static int[] encrypt(int[] msg, int[] key) {
    int[] encrypted = new int[msg.length];

    for(int i = 0; i < msg.length; i++) {
      encrypted[i] = msg[i] ^ key[i%key.length];
    }

    return encrypted;
  }

  // Function that uses frequency analysis to find the most likely key for this problem
  static int[] getKey(int[] msg, int keyLength) {
    int maximum = 0;

    for(int i = 0; i < msg.length; i++) {
      if(msg[i] > maximum) maximum = msg[i];
    }

    // Need to split the letters into three groups (for each letter in key)
    int[][] frequencies = new int[keyLength][maximum+1];
    int[] key = new int[keyLength];

    // Loop through the message
    for(int i = 0; i < msg.length; i++) {
      // Index into key
      int j = i % keyLength;

      // Increase frequency of found ASCII
      frequencies[j][msg[i]]++;

      // Check if the frequency of the just changed message is more than the current key index
      if(frequencies[j][msg[i]] > frequencies[j][key[j]]) key[j] = msg[i];
    }

    // Assume that the most frequent character is the space
    int space = 32;
    for(int i = 0; i < keyLength; i++) key[i] = key[i] ^ space;

    return key;
  }

  public static void main(String args[]) {
    int[] hello = strToIntArr("Hello");
    System.out.print("Hello as an int array is ");
    for(int i = 0; i < hello.length; i++) System.out.print(hello[i] + " ");
    System.out.println("!");

    System.out.println("That int array as a string is " + intArrToStr(hello));

    int[] testKey = {97, 98, 99};
    int[] helloEncrypted = encrypt(hello, testKey);
    System.out.println("Encrypting and then decrypting Hello with the key 'abc' gives us: ");
    System.out.println(intArrToStr(encrypt(helloEncrypted, testKey)));

    try {
      Scanner sc = new Scanner(new File("p059_cipher.txt"));
      sc.useDelimiter(",");

      PrintStream o = new PrintStream(new File("p059_out.txt"));
      PrintStream console = System.out;
      System.setOut(o);

      // Create an ArrayList of Integers to hold the values from Scanner
      ArrayList<Integer> complete = new ArrayList<>();

      while(sc.hasNextInt()) {
        complete.add(sc.nextInt());
      }

      int[] text = new int[complete.size()];
      for(int i = 0; i < complete.size(); i++) text[i] = complete.get(i);

      int[] key = getKey(text, 3);
      System.setOut(console);
      System.out.print("Key is {");
      for(int i = 0; i < key.length; i++) {
        System.out.print(" " + key[i] + " ");
      }
      System.out.println("}");

      System.setOut(o);
      int[] decoded = encrypt(text, key);
      System.out.println(intArrToStr(decoded));

      // Find the sum of the ASCII characters
      int sum = 0;
      for(int i = 0; i < decoded.length; i++) sum += decoded[i];
      System.out.println("The total sum of the message is " + sum);
    } catch(Exception e) {
      System.out.println(e);
    }
  }
}
