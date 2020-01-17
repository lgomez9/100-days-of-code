// Could be refactored to be more efficient
class Solution {
    public int[] sortedSquares(int[] A) {
        int[] squares = new int[A.length];
        
        for(int i = 0; i < A.length; i++) {
            squares[i] = (int) Math.pow(A[i], 2);
        }
        
        Arrays.sort(squares);
        
        return squares;
    }
}
