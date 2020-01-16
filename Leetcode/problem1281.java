class Solution {
//     Function to find the product of the digits of n
    long productOfDigits(int n) {
        long product = 1;
        
        while(n > 0) {
            product *= n % 10;
            n /= 10;
        }
        
        return product;
    }
    
    
//     Function to find the sum of the digits of n
    long sumOfDigits(int n) {
        long sum = 0;
        
        while(n > 0) {
            sum += n % 10;
            n /= 10;
        }
        
        return sum;
    }
    
//     Function that returns the difference between the product and sum of digits
    public int subtractProductAndSum(int n) {
        return (int) productOfDigits(n) - (int) sumOfDigits(n);
    }
}
