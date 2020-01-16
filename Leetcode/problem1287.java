class Solution {
    public int findSpecialInteger(int[] arr) {
        double percent;
        int curr = arr[0];
        double count = 1.0;
        
        for(int i = 1; i < arr.length; i++) {
            if(arr[i] == curr) count += 1.0;
            else {
                percent = count / (double) arr.length;
                if(percent > .25) return arr[i-1];
                else {
                    curr = arr[i];
                    count = 1.0;
                }
            }
        }
        
        return arr[arr.length-1];
    }
}
