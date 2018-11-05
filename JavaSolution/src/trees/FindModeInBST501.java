package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.

 Assume a BST is defined as follows:

 The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 Both the left and right subtrees must also be binary search trees.


 For example:
 Given BST [1,null,2,2],

 1
 \
 2
 /
 2


 return [2].

 Note: If a tree has more than one mode, you can return them in any order.

 Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).

 */

// 不用extra space：由于是BST，所以in order的遍历可以实现从小到大排列，因此维护一个当前重复count和maxCount，每次和prev的val比较
// 当count > maxCount就清空modes集合，加当前的进去，如果count == maxCount就把当前的加到结果集

public class FindModeInBST501 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeNode prev;
    int maxCount = 0;
    int count = 0;

    public int[] findMode(TreeNode root) {
        List<Integer> modes = new ArrayList<>();
        prev = root; // 避免了在inorder中检查prev是否为null
        inOrder(modes, root);

        int[] res = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            res[i] = modes.get(i);
        }
        return res;
    }

    private void inOrder(List<Integer> modes, TreeNode node) {
        if(node == null) return;

        inOrder(modes, node.left);
        count = node.val == prev.val ? count + 1 : 1;

        if(count == maxCount) {
            modes.add(node.val);
        }
        else if(count >= maxCount) {
            modes.clear();
            maxCount = count;
            modes.add(node.val);
        }

        prev = node;
        inOrder(modes, node.right);

    }
}
