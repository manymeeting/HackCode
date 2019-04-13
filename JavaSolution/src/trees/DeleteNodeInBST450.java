package trees;

/**
 * Given a root node reference of a BST and a key, delete the node with the given key in the BST. Return the root node reference (possibly updated) of the BST.

 Basically, the deletion can be divided into two stages:

 Search for a node to remove.
 If the node is found, delete the node.
 Note: Time complexity should be O(height of tree).

 Example:

 root = [5,3,6,2,4,null,7]
 key = 3

 5
 / \
 3   6
 / \   \
 2   4   7

 Given key to delete is 3. So we find the node with value 3 and delete it.

 One valid answer is [5,4,6,2,null,null,7], shown in the following BST.

 5
 / \
 4   6
 /     \
 2       7

 Another valid answer is [5,2,6,null,4,null,7].

 5
 / \
 2   6
 \   \
 4   7
 */

// 找到目标node后，如果目标节点有左右children，则找出右子树中最小的（最左下方），作为新root

public class DeleteNodeInBST450 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode deleteNode(TreeNode root, int key) {

        if(root == null) return null;
        if(root.val < key) root.right = deleteNode(root.right, key);
        else if(root.val > key) root.left = deleteNode(root.left, key);
        else {
            // found the target
            if(root.left == null) return root.right;
            else if(root.right == null) return root.left;
            else {
                TreeNode newRoot = root.right;
                TreeNode parent = null;
                // 找出这部分树中最小的作为new root
                while(newRoot.left != null) {
                    parent = newRoot;
                    newRoot = newRoot.left;
                }
                if(parent == null) {
                    newRoot.left = root.left;
                    return newRoot;
                }

                parent.left = newRoot.right;
                newRoot.left = root.left;
                newRoot.right = root.right;
                return newRoot;

            }

        }

        return root;

    }

    // Exercise
    // public TreeNode deleteNodeFromBST(TreeNode root, int val) {

    //     if(root == null) {
    //         return null;
    //     }

    //     if(root.val > val) {
    //         root.left = deleteNode(root.left, val);
    //     }
    //     if(root.val < val) {
    //         root.right = deleteNode(root.right, val);   
    //     }
    //     if(root.val == val) {
    //         // If is leaf node
    //         if(root.left == null && root.right == null) {
    //             return null;
    //         }

    //         // If has left child but no right child
    //         if(root.left != null && root.right == null) {
    //             return root.left;
    //         }

    //         // If has right child but no left child
    //         if(root.right != null && root.left == null) {
    //             return root.right;
    //         }

    //         // Has both children, find the smallest one in the right subtree
    //         TreeNode smallestRight = findSmallestRight(root);
    //         TreeNode smallestRightParent = findParent(smallestRight);
            
    //         smallestRightParent.left = smallestRight.right;
    //         smallestRight.right = root.right;
    //         smallestRight.left = root.left;
    //         return smallestRight;

    //     }

    // }
}
