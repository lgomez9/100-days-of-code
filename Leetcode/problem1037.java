class Solution {
//     A function to find the slope of a line given two points
    double findSlope(int[] a, int[] b) {
        return (double) (b[1]-a[1]) / (double) (b[0]-a[0]);
    }
    
//     A function to determine if a point is on a line given a point on that line and the slope
    boolean onSameLine(int[] p, int[] onLine, double slope) {
        return (double) (onLine[1] - p[1]) == slope * (double) (onLine[0] - p[0]);
    }
    
//     Print out given point
    void printPoint(int[] p) {
        System.out.print("(" + p[0] + ", " + p[1] + ")");
    }
    
    public boolean isBoomerang(int[][] points) {
//         Test if points are distinct
        if(Arrays.equals(points[0], points[1]) || Arrays.equals(points[0], points[2]) || Arrays.equals(points[1], points[2])) return false;
        
//         Test if points are on vertical line
        if(points[0][0] == points[1][0]) {
            if(points[0][0] == points[2][0]) return false;
            else return true;
        }
        
        
        double slope = findSlope(points[0], points[1]);
        if(onSameLine(points[2], points[0], slope)) return false;
        else return true;
    }
}
