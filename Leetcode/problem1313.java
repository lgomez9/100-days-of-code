class Solution {
    public int[] decompressRLElist(int[] nums) {
//         Find the final length of the return array
        int returnLength = 0;
        for(int i = 0; i < nums.length; i += 2) returnLength += nums[i];
        
//         Create the return array
        int[] decompressed = new int[returnLength];
        int currIndex = 0;
        
        for(int i = 0; i < nums.length; i += 2) {
            for(int j = 0; j < nums[i]; j++) decompressed[currIndex++] = nums[i+1];
        } 
        
        return decompressed;
    }
}
