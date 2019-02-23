package dp;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". It will automatically contact the police if two directly-linked houses were broken into on the same night.

 Determine the maximum amount of money the thief can rob tonight without alerting the police.

 Example 1:

 Input: [3,2,3,null,3,null,1]

 3
 / \
 2   3
 \   \
 3   1

 Output: 7
 Explanation: Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
 Example 2:

 Input: [3,4,5,1,3,null,1]

 3
 / \
 4   5
 / \   \
 1   3   1

 Output: 9
 Explanation: Maximum amount of money the thief can rob = 4 + 5 = 9.
 */


// 详细讲解：https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem

// DP思路，在每个node处用一个int[2]来存储两个值，第一个是不包括该node情况下的最大值，第二个是包括该node的最大值

public class HouseRobberIII337 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int rob(TreeNode root) {
        int[] res = robVal(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robVal(TreeNode root) {
        if(root == null) return new int[2];

        int[] left = robVal(root.left);
        int[] right = robVal(root.right);

        int[] res = new int[2];

        // case1：不包括该root，可以找children中两个值的最大者并相加
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        // case2: 包括该root，只能选择children中不包括自己的那个值
        res[1] = root.val + left[0] + right[0];

        return res;
    }
}
