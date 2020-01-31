/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    int sumEvenGrandparent(TreeNode root, boolean evenParent) {
        int sum = 0;
        
        if(root == null) return sum;
        
        if(evenParent) {
            if(root.left != null) sum += root.left.val;
            if(root.right != null) sum += root.right.val;
        }
        
        if(root.val % 2 == 0) return sum + sumEvenGrandparent(root.left, true) + sumEvenGrandparent(root.right, true);
        else return sum + sumEvenGrandparent(root.left, false) + sumEvenGrandparent(root.right, false);
    }
    
    public int sumEvenGrandparent(TreeNode root) {
        return sumEvenGrandparent(root, false);
    }
}
