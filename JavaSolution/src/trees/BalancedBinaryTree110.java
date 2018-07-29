package trees;

/**
 * Given a binary tree, determine if it is height-balanced.

 For this problem, a height-balanced binary tree is defined as:

 a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example 1:

 Given the following tree [3,9,20,null,null,15,7]:

 3
 / \
 9  20
 /  \
 15   7
 Return true.

 Example 2:

 Given the following tree [1,2,2,3,3,null,null,4,4]:

 1
 / \
 2   2
 / \
 3   3
 / \
 4   4
 Return false.


 */

// 基本的DFS思路
public class BalancedBinaryTree110 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isBalanced(TreeNode root) {
        int res = calcHeight(root);
        if(res < 0)
        {
            return false;
        }
        return true;
    }

    public int calcHeight(TreeNode node) {
        if(node == null){
            return 0;
        }

        int leftH = calcHeight(node.left);
        int rightH = calcHeight(node.right);

        if(leftH < 0 || rightH < 0)
        {
            return -1;
        }

        if(Math.abs(leftH - rightH) > 1)
        {
            return -1;
        }

        return Math.max(leftH, rightH) + 1;
    }
}
