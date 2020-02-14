// Challenge 43
import java.io.*;
/* The number, 1406357289, is a 0 to 9 pandigital number because it is made up
 * of each of the digits 0 to 9 in some order, but it also has a rather interesting
 * sub-string divisibility property.
 *
 * Let d1 be the 1st digit, d2 be the 2nd digit, and so on. In this way, we note
 * the following:
 *
 * d2d3d4 = 406 is divisible by 2
 * d3d4d5 = 063 is divisible by 3
 * d4d5d6 = 635 is divisible by 5
 * d5d6d7 = 357 is divisible by 7
 * d6d7d8 = 572 is divisible by 11
 * d7d8d9 = 728 is divisible by 13
 * d8d9d10 = 289 is divisible by 17
 *
 * Find the sum of all 0 to 9 pandigital numbers with this property.
 */

 public class challenge43 {
   // Function to convert 3 given integers into a 3-digit number abc
   static int toThreeDigit(int a, int b, int c) {
     return a*100 + b*10 + c;
   }

   // Function to swap two indices in an array
   static void swap(int[] arr, int i, int j) {
     int temp = arr[i];
     arr[i] = arr[j];
     arr[j] = temp;
   }

   // Function to tell whether a 10-digit number has sub-string divisibility property
   // 10-digit number represented by array of length 10
   static boolean subStringDivisible(int[] num) {
     return toThreeDigit(num[1], num[2], num[3]) % 2 == 0 &&
            toThreeDigit(num[2], num[3], num[4]) % 3 == 0 &&
            toThreeDigit(num[3], num[4], num[5]) % 5 == 0 &&
            toThreeDigit(num[4], num[5], num[6]) % 7 == 0 &&
            toThreeDigit(num[5], num[6], num[7]) % 11 == 0 &&
            toThreeDigit(num[6], num[7], num[8]) % 13 == 0 &&
            toThreeDigit(num[7], num[8], num[9]) % 17 == 0;
   }

   // Function to print out an array
   static void printArr(int[] arr) {
     for(int i = 0; i < arr.length; i++) System.out.print(arr[i]);
   }

   // Function to find whether an array is in reverse order (9-0)
   static boolean inReverseOrder(int[] arr) {
     for(int i = 0, j = arr.length-1; i < arr.length; i++, j--) {
       if(arr[i] != j) {
         return false;
       }
     }

     return true;
   }

   // Function to count up the permutations of 0-9
   static void permutations() {
     // Create an array to contain the integers we're permuting
     int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

     // Run the algorithm until count is equal to n
     while(!inReverseOrder(nums)) {
       int i = nums.length-1;
       while(nums[i-1] >= nums[i]) i = i-1;

       int j = nums.length;
       while(nums[j-1] <= nums[i-1]) j = j-1;

       swap(nums, i-1, j-1);

       i++;
       j = nums.length;
       while(i < j) {
         swap(nums, i-1, j-1);
         i++;
         j--;
       }

       if(subStringDivisible(nums)) {
         printArr(nums);
         System.out.println();
       }
     }
   }

   public static void main(String args[]) {
     int[] ex = {1, 4, 0, 6, 3, 5, 7, 2, 8, 9};
     int[] reverseOrder = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};

     try {
       PrintStream o = new PrintStream(new File("out43.txt"));

       PrintStream console = System.out;

       System.setOut(o);

       System.out.println("1406357289 is sub-string divisible: " + subStringDivisible(ex));

       System.out.println("9876543210 is in reverse order: " + inReverseOrder(reverseOrder));

       permutations();
     } catch(Exception e) {
       System.out.println(e);
     }
   }
 }
