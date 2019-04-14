package trees;

import java.util.*;
/**

Given a binary tree, count the number of uni-value subtrees.

A Uni-value subtree means all nodes of the subtree have the same value.

Example :

Input:  root = [5,1,5,5,5,null,5]

              5
             / \
            1   5
           / \   \
          5   5   5

Output: 4


*/


// 注意univalue subtree的定义，所有leaf节点都是，除此之外要求以该node为root的所有children都是同一个值
// 用一个boolean的post order dfs来判断左右是否满足uni val subtree，然后再比较root的值和左右children的值
// 一个global变量来记录总数

class CountUnivalueSubtrees250 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int uniSubtreeCnt = 0;
	public int countUnivalSubtrees(TreeNode root) {
        
		helper(root);
		return uniSubtreeCnt;
    }

    public boolean helper(TreeNode root) {
    	if(root == null) return true;
    	boolean left = helper(root.left);
    	boolean right = helper(root.right);

    	if(left && right) { // left && right are all uni value subtrees
    		if(root.left != null && root.val != root.left.val) return false;
    		if(root.right != null && root.val != root.right.val) return false;

    		uniSubtreeCnt++;
    		return true;

    	}

    	return false; // Left or right is not a uni value subtree


    }

}