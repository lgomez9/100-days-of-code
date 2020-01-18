class Solution {
    public List<Integer> findDuplicates(int[] nums) {
//         Create ArrayList to hold result
        List<Integer> repeats = new ArrayList<>();
        
        Arrays.sort(nums);

        for(int i = 0; i < nums.length-1; i++) {            
            if(nums[i] == nums[i+1]) repeats.add(nums[i]);
        }
        
        return repeats;
    }
}
