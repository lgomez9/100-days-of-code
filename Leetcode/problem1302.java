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
//     Function to find the deepest depth of a tree
    int deepestDepth(TreeNode root) {
        if(root == null) return -1;
        
        return 1 + Math.max(deepestDepth(root.left), deepestDepth(root.right));
    }
    
//     Find the sum of the deepest leaves of a tree
    int deepestSum(TreeNode root, int deepest, int currDepth) {
        if(root == null) return 0;
        if(currDepth == deepest) return root.val;
        else return deepestSum(root.left, deepest, currDepth+1) + deepestSum(root.right, deepest, currDepth+1);
    }
    
    
    public int deepestLeavesSum(TreeNode root) {
//         Find the deepest depth
        int deepest = deepestDepth(root);
        
//         Find the sum of the deepest leaves of a tree
        return deepestSum(root, deepest, 0);
    }
}
