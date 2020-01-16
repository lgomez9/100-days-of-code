class Solution {
    public int[] sumZero(int n) {
//         Create the return array
     int[] result = new int[n];
//         Find the midway point for the array
     int mid = n/2;
        
//         Fill in the first and second half of the array
     for(int i = 0; i < mid; i++) {
         result[i] = i+1;
         result[i+mid] = -(i+1);
     }
        
//         Check the odd case
     if(n % 2 != 0) {
         result[n-1] = 0;
     }
    
        
     return result;
    }
}
