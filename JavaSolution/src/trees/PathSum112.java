package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

 Note: A leaf is a node with no children.

 Example:

 Given the below binary tree and sum = 22,

 5
 / \
 4   8
 /   / \
 11  13  4
 /  \      \
 7    2      1
 return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */


// dfs遍历，把所有sum放到list里，
// 注意对leaf的定义：left和right都为空的node才算，所以放sum到list里的时机是判断到左右child都为空时

public class PathSum112 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    List<Integer> allSum;
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        allSum = new ArrayList<>();
        traverse(root, 0);
        return allSum.contains(sum);
    }

    private void traverse(TreeNode node, int currSum) {
        if(node == null) return;

        currSum += node.val;
        if(node.left == null && node.right == null) {
            allSum.add(currSum);
            return;
        }
        traverse(node.left, currSum);
        traverse(node.right, currSum);
    }

}
