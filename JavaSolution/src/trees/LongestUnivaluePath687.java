package trees;

/**
 * Given a binary tree, find the length of the longest path where each node in the path has the same value. This path may or may not pass through the root.

 Note: The length of path between two nodes is represented by the number of edges between them.

 Example 1:

 Input:

 5
 / \
 4   5
 / \   \
 1   1   5
 Output:

 2
 Example 2:

 Input:

 1
 / \
 4   5
 / \   \
 4   4   5
 Output:

 2
 Note: The given binary tree has not more than 10000 nodes. The height of the tree is not more than 1000.

 */

// divide-conquer风格的dfs
public class LongestUnivaluePath687 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    private int maxLen = 0;
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxLen;
    }

    // int[], [0]: number, [1] count
    private int[] dfs(TreeNode root) {

        if(root == null) return new int[]{Integer.MIN_VALUE, 0};
        int[] left = dfs(root.left);
        int[] right = dfs(root.right);

        int leftLen = left[1];
        int rightLen = right[1];

        if(root.val == left[0] && root.val == right[0]) {
            maxLen = Math.max(maxLen, leftLen + rightLen + 2); // 计算通过root并且连接左右子树时要+2
            return new int[] {root.val, Math.max(leftLen, rightLen) + 1}; // 注意这里是取左右的max然后+1，因为路径从上到下不能分叉
        }
        else if(root.val == left[0]) {
            maxLen = Math.max(maxLen, leftLen + 1);
            return new int[] {root.val, leftLen + 1};
        }
        else if(root.val == right[0]) {
            maxLen = Math.max(maxLen, rightLen + 1);
            return new int[] {root.val, rightLen + 1};
        }
        else {
            return new int[] {root.val, 0};
        }
    }
}
