package trees;

/**
 * Given a binary tree, determine if it is a valid binary search tree (BST).

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than the node's key.
 The right subtree of a node contains only nodes with keys greater than the node's key.
 Both the left and right subtrees must also be binary search trees.
 Example 1:

 Input:
 2
 / \
 1   3
 Output: true
 Example 2:

 5
 / \
 1   4
 / \
 3   6
 Output: false
 Explanation: The input is: [5,1,4,null,null,3,6]. The root node's value
 is 5 but its right child's value is 4.

 */

// 在helper里递归，对每个node，传入一个min和max作为允许的下界和上界，和val比较，同时递归检查左右child

public class ValidateBST98 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        return helper(root, Long.MAX_VALUE, Long.MIN_VALUE);
    }

    private boolean helper(TreeNode node, long max, long min) {
        if(node == null) return true;

        if(node.val <= min || node.val >= max) { // 注意是 >= 和 <=
            return false;
        }

        return helper(node.left, node.val, min) && helper(node.right, max, node.val);
    }
}
