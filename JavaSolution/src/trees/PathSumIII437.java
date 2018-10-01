package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * You are given a binary tree in which each node contains an integer value.

 Find the number of paths that sum to a given value.

 The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).

 The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.

 Example:

 root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

 10
 /  \
 5   -3
 / \    \
 3   2   11
 / \   \
 3  -2   1

 Return 3. The paths that sum to 8 are:

 1.  5 -> 3
 2.  5 -> 2 -> 1
 3. -3 -> 11

 */

// 用prefix来存已经存在的pathsum，每次在presum中找currSum - target，以此为基础再加上左右child的sum，
// 最后要注意backtrack，把当前的sum出现次数减1

public class PathSumIII437 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1); // sum为0的话总能满足
        return helper(root, 0, sum, preSum);
    }

    private int helper(TreeNode node, int currSum, int target, Map<Integer, Integer> preSum) {
        if(node == null) return 0;

        currSum += node.val;


        // 注意是在presum中找currSum - target
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        res += helper(node.left, currSum, target, preSum) + helper(node.right, currSum, target, preSum);

        preSum.put(currSum, preSum.get(currSum) - 1); // Backtracking
        return res;
    }


    // 两重递归解法，效率低
    /**
    public int pathSum(TreeNode root, int sum) {
        if(root == null) return 0;
        return pathSumFrom(root, sum, 0) + pathSum(root.left, sum)
                + pathSum(root.right, sum);
    }

    private int pathSumFrom(TreeNode node, int sum, int currSum) {
        if(node == null) return 0;
        currSum += node.val;
        return (sum == currSum ? 1 : 0) + pathSumFrom(node.left, sum, currSum)
                + pathSumFrom(node.right, sum, currSum);
    }
     */
}
