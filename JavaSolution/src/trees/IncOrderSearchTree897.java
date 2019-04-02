package trees;


/**
Given a tree, rearrange the tree in in-order so that the leftmost node in the tree is now the root of the tree, and every node has no left child and only 1 right child.

Example 1:
Input: [5,3,6,2,4,null,8,1,null,null,null,7,9]

       5
      / \
    3    6
   / \    \
  2   4    8
 /        / \ 
1        7   9

Output: [1,null,2,null,3,null,4,null,5,null,6,null,7,null,8,null,9]

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
            \
             7
              \
               8
                \
                 9  
Note:

The number of nodes in the given tree will be between 1 and 100.
Each node will have a unique integer value from 0 to 1000.

*/


// 维护一个全局变量curr，指向inorder遍历中的前一个node，不断添加当前node到curr.right

class IncOrderSearchTree897 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    private TreeNode curr; // 一个保留inorder遍历顺序中前一个node的引用，不断添加当前node到curr.right，然后更新curr
    public TreeNode increasingBST(TreeNode root) {
    	TreeNode dummy = new TreeNode(0); 
    	curr = dummy;
    	inOrder(root);
    	return dummy.right;

    }

    private void inOrder(TreeNode root) {
    	if(root == null) return;
    	inOrder(root.left);
    	root.left = null;
    	curr.right = root;
    	curr = root;
    	inOrder(root.right);

    }
}
