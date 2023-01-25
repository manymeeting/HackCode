package trees;

import java.util.*;
/**
 * 
Given the root of a binary tree, collect a tree's nodes as if you were doing this:

Collect all the leaf nodes.
Remove all the leaf nodes.
Repeat until the tree is empty.


思路：用bottom up的思路来写递归，每次往上递进一个level。用map来存数据，把每个level里的node加到对应level的list里。

 */

 public class FindLeavesOfBinaryTree366 {

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

    Map<Integer, List<Integer>> levelNodes = new HashMap<>();
    public List<List<Integer>> findLeaves(TreeNode root) {
        int maxRank = helper(root);
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < maxRank; i++) {
            res.add(levelNodes.get(i));
        }
        return res;
        
    }

    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftRank = helper(root.left);
        int rightRank = helper(root.right);
        int rank = Math.max(leftRank, rightRank);
        List<Integer> nodes = levelNodes.getOrDefault(rank, new ArrayList<>());
        nodes.add(root.val);
        levelNodes.put(rank, nodes);
        return rank + 1;
    }
}
