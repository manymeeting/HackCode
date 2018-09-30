package trees;

/**
 * Given a non-empty binary tree, find the maximum path sum.

 For this problem, a path is defined as any sequence of nodes from some starting node to any node in the tree along the parent-child connections. The path must contain at least one node and does not need to go through the root.

 Example 1:

 Input: [1,2,3]

 1
 / \
 2   3

 Output: 6
 Example 2:

 Input: [-10,9,20,null,null,15,7]

 -10
 / \
 9  20
 /  \
 15   7

 Output: 42

 */
// dfs做两件事，
// 1.向上传递左右子树最大的sum加上当前的val，
// 2.更新一个global的sum，Math.max（max，左sum+右sum+当前val）

public class BinaryTreeMaxPathSum124 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        traverse(root);
        return maxSum;
    }

    private int traverse(TreeNode node) {
        if(node == null) return 0;

        // 跟0比较，因为有负数存在，如果发现一端sum小于0，则舍弃该sum，按0计算（path一端从自身开始）
        int leftMaxSum = Math.max(0, traverse(node.left));
        int rightMaxSum = Math.max(0, traverse(node.right));

        maxSum = Math.max(maxSum, leftMaxSum + rightMaxSum + node.val);
        return Math.max(leftMaxSum, rightMaxSum) + node.val;
    }
}
