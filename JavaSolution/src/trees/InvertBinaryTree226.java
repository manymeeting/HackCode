package trees;

/**
 * Invert a binary tree.

 Example:

 Input:

 4
 /   \
 2     7
 / \   / \
 1   3 6   9
 Output:

 4
 /   \
 7     2
 / \   / \
 9   6 3   1
 */

/**
 * Definition for a binary tree node.
*/

// Not difficult, don't panic and solve it.
public class InvertBinaryTree226 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        if(root.left == null)
        {
            root.left = invertTree(root.right);
            root.right = null;
            return root;
        }
        if(root.right == null)
        {
            root.right = invertTree(root.left);
            root.left = null;
            return root;
        }
        TreeNode temp = root.right;
        root.right = invertTree(root.left);
        root.left = invertTree(temp);
        return root;
    }



}
