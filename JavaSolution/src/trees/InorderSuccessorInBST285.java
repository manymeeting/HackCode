package trees;


/**
Given a binary search tree and a node in it, find the in-order successor of that node in the BST.

The successor of a node p is the node with the smallest key greater than p.val.

 

Example 1:


Input: root = [2,1,3], p = 1
Output: 2
Explanation: 1's in-order successor node is 2. Note that both p and the return value is of TreeNode type.
Example 2:


Input: root = [5,3,6,2,4,null,null,1], p = 6
Output: null
Explanation: There is no in-order successor of the current node, so the answer is null.
 

Note:

If the given node has no in-order successor in the tree, return null.
It's guaranteed that the values of the tree are unique.

*/


// 直接inorder遍历，用一个全局变量来表示是否找到了given的node，
// 如果在left递归中出现就返回left，否则看是否上一个node是given，是的话return当前node，如果不是再看right
// 最后看right中是否出现，没有的话在结尾返回null

class InorderSuccessorInBST285 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private boolean isGivenNodeFound = false;
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root == null) return null;

        TreeNode left = inorderSuccessor(root.left, p);
        if(left != null) return left;

        if(isGivenNodeFound) {
        	return root;
        }
        if(root.val == p.val) {
        	isGivenNodeFound = true;
        }

        TreeNode right = inorderSuccessor(root.right, p);
        if(right != null) return right;

        return null;
    }
}