package trees;

/**
 * 
 * 
Given the root of a binary tree, return the number of nodes where the value of the node is equal to the sum of the values of its descendants.

A descendant of a node x is any node that is on the path from node x to some leaf node. The sum is considered to be 0 if the node has no descendants.

Constraints:

The number of nodes in the tree is in the range [1, 105].
0 <= Node.val <= 105

思路：比较简单，traverse一遍，计算descendant sum值，同时更新一个global counter。
 */
public class CountNodesEqualDescendantsSum1973 {
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

    int res = 0;
    public int equalToDescendants(TreeNode root) {
        calcDescendantsSum(root);
        return res;
    }

    public int calcDescendantsSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int descendantsSum = calcDescendantsSum(root.left) + calcDescendantsSum(root.right);
        if (root.val == descendantsSum) {
            res++;
        }
        return root.val + descendantsSum;
    }
}
