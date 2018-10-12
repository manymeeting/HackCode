package trees;

/**
 *
 Given a binary tree, flatten it to a linked list in-place.

 For example, given the following tree:

 1
 / \
 2   5
 / \   \
 3   4   6
 The flattened tree should look like:

 1
 \
 2
 \
 3
 \
 4
 \
 5
 \
 6
 */

// 用post order（right，left，root）来遍历，再维护一个prev，
// 每次递归中把当前node的left设为空，right设为prev，然后更新prev到当前node，进入下一轮递归

// https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share/150699

public class FlattenTreeToLinkedList114 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private TreeNode prev = null;

    private void flatten(TreeNode root) {
        if (root == null)
            return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
