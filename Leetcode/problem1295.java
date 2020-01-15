class Solution {
//     Function that returns whether the given number has an even number of digits
    boolean evenDigits(int n) {
        int numDigits = 0;
        
        while(n > 0) {
            n /= 10;
            numDigits++;
        }
        
        return numDigits % 2 == 0;
    }
  
//  Find the number of evens
    public int findNumbers(int[] nums) {
        int numEven = 0;
        
        for(int i = 0; i < nums.length; i++) {
            if(evenDigits(nums[i])) numEven++;
        }
        
        return numEven;
    }
}
