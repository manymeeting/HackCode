package trees;

/**
 * Given a binary search tree and the lowest and highest boundaries as L and R, trim the tree so that all its elements lies in [L, R] (R >= L). You might need to change the root of the tree, so the result should return the new root of the trimmed binary search tree.

Example 1:
Input: 
    1
   / \
  0   2

  L = 1
  R = 2

Output: 
    1
      \
       2
Example 2:
Input: 
    3
   / \
  0   4
   \
    2
   /
  1

  L = 1
  R = 3

Output: 
      3
     / 
   2   
  /
 1

 */


// 递归判断root的值，如果在范围之外则移动root，
// 否则root不变，更新左右children，保证左右child以及其children的val都落在目标范围内

public class TrimBST669 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public TreeNode trimBST(TreeNode root, int L, int R) {
        if(root == null) return root;

        // root在范围之外，更换root
        if(root.val < L) {
            return trimBST(root.right, L, R);
        }
        if(root.val > R) {
            return trimBST(root.left, L, R);
        }

        // root不变，找新的左右child（保证child以及其children的val都落在目标范围内）
        root.left = trimBST(root.left, L, R);
        root.right = trimBST(root.right, L, R);

        return root;
    }



}
