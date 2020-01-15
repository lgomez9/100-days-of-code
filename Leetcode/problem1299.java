class Solution {
    public int[] replaceElements(int[] arr) {
//         Create a temporary array to contain the current greatest number
//         Set it equal to the last element of the array
        int currGreatest = arr[arr.length-1];
        
//         Replace the end of the array with -1.
        arr[arr.length-1] = -1;
        
//         Iterate backwards through the array
        for(int i = arr.length-2; i >= 0; i--) {
            if(currGreatest >= arr[i]) arr[i] = currGreatest;
            else {
                int temp = arr[i];
                arr[i] = currGreatest;
                currGreatest = temp;
            }
        }
        
        return arr;
    }
}
