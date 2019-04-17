package trees;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
Return:

[
   [5,4,11,2],
   [5,8,4,5]
]


 * */

// dfs遍历的同时记录当前path和sum，注意每次在最后要remove当前递归加入的那个node

public class PathSumII113 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private List<List<Integer>> allValidPaths;
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        allValidPaths = new ArrayList<>();
        traverse(root, sum, 0, new ArrayList<>());
        return allValidPaths;
    }

    private void traverse(TreeNode node, int sum, int currSum, List<Integer> sumPath) {
        if(node == null) return;

        currSum += node.val;
        sumPath.add(node.val);
        if(node.left == null && node.right == null && currSum == sum) {
            allValidPaths.add(new ArrayList<>(sumPath));
            sumPath.remove(sumPath.size()-1);
            return;
        }
        else {
            traverse(node.left, sum, currSum, sumPath);
            traverse(node.right, sum, currSum, sumPath);
        }

        sumPath.remove(sumPath.size()-1);

    }
}
