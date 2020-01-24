// Challenge 24

/* A permutation is an ordered arrangement of objects. For example, 3124 is one
 * possible permutation of the digits 1, 2, 3, and 4. If all the permutations are
 * listed numerically or alphabetically, we call it lexicographic order. The
 * lexicographic permutations of 0, 1, 2 are:
 *          012 021 102 120 201 210
 *
 * What is the millionth lexicographic permutation of the digits 0, 1, 2, 3, 4, 5, 6, 7, 8, and 9?
 */

public class challenge24 {
  // Function to swap two indices in an array
  static void swap(int[] arr, int i, int j) {
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
  }

  // Function to print out an array
  static void printArr(int[] arr) {
    for(int i = 0; i < arr.length; i++) System.out.print(arr[i]);
  }

  // Function to count up the permutations of 0-9 up until n
  static int[] nthPermutation(int n) {
    // Create an array to contain the integers we're permuting
    int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    // Set up a count variable to count how many permutations we've looked through
    int count = 1;

    // Run the algorithm until count is equal to n
    while(count < n) {
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

      count++;
    }

    return nums;
  }


  public static void main(String args[]) {
    // Find the millionth permutation
    System.out.print("The millionth permutation of 0-9 is ");
    printArr(nthPermutation(1000000));
    System.out.println();
  }
}
