package trees;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.

 Calling next() will return the next smallest number in the BST.

 Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

 Credits:
 Special thanks to @ts for adding this problem and creating all test cases.
 */
// 用一个stack，先把从root开始所有left的node都放进去，
// 然后每次next的时候，pop出一个，同时以pop出的node的right child为root，放所有的left进去，
// 这个next平均是O（1）时间因为平均每个node其实只放了一次，只有少数node进while循环

public class BSTIterator173 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private Deque<TreeNode> stack = new ArrayDeque<>();

    public BSTIterator173(TreeNode root) {
        pushAll(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode curr = stack.pop();
        pushAll(curr.right);
        return curr.val;
    }

    private void pushAll(TreeNode node) {
        while(node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */