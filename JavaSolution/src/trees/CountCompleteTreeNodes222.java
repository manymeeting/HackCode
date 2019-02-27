package trees;

/**
 * Given a complete binary tree, count the number of nodes.

 Note:

 Definition of a complete binary tree from Wikipedia:
 In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 Example:

 Input:
 1
 / \
 2   3
 / \  /
 4  5 6

 Output: 6
 */

// 写两个函数分别计算left的高度和right高度，如果两个高度相等直接按2^n-1计算node，否则递归左右child，同时 + 1

// 复杂度：(logN)^2

public class CountCompleteTreeNodes222 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int leftDepth = leftDepth(root);
        int rightDepth = rightDepth(root);
        if(leftDepth == rightDepth) {
            return (1 << leftDepth) - 1;
        }
        else {
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }

    private int leftDepth(TreeNode node) {
        int depth = 0;
        while(node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }

    private int rightDepth(TreeNode node) {
        int depth = 0;
        while(node != null) {
            depth++;
            node = node.right;
        }
        return depth;
    }
}
