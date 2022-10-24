package trees;

/**
 * 
 * Given the root of a perfect binary tree, reverse the node values at each odd level of the tree.

For example, suppose the node values at level 3 are [2,1,3,4,7,11,29,18], then it should become [18,29,11,7,4,3,1,2].
Return the root of the reversed tree.

A binary tree is perfect if all parent nodes have two children and all leaves are on the same level.

The level of a node is the number of edges along the path between it and the root node.

Constraints:

The number of nodes in the tree is in the range [1, 214].
0 <= Node.val <= 105
root is a perfect binary tree.


思路：注意递归时pass到下一层的node的选择，选取（左.左， 右.右）以及（左.右，右.左）
*/
public class ReverseOddLevelsBinaryTree2415 {
    
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    
    public TreeNode reverseOddLevels(TreeNode root) {
        if (root == null) return root;
        helper(root.left, root.right, 1);
        return root;
    }

    public void helper(TreeNode left, TreeNode right, int level) {
        if (left == null || right == null) {
            return;
        }

        if (level % 2 == 1) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
        }
        helper(left.left, right.right, level + 1);
        helper(left.right, right.left, level + 1);
    }
}
