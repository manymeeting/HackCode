package trees;

import java.util.*;

/**
Given a binary tree, determine if it is a complete binary tree.

Definition of a complete binary tree from Wikipedia:
In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.

 

Example 1:



Input: [1,2,3,4,5,6]
Output: true
Explanation: Every level before the last is full (ie. levels with node-values {1} and {2, 3}), and all nodes in the last level ({4, 5, 6}) are as far left as possible.
Example 2:



Input: [1,2,3,4,5,null,7]
Output: false
Explanation: The node with value 7 isn't as far left as possible.
 
Note:

The tree will have between 1 and 100 nodes.
*/

// 用bfs来遍历，只要判断如果之前出现过non complete的node，之后就不能出现任何不是leaf的node
class CheckCompletenessOfTree958 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    
	public boolean isCompleteTree(TreeNode root) {
     	if(root == null) return false;
    	ArrayDeque<TreeNode> q = new ArrayDeque<>();
    	q.add(root);
    	
    	boolean nonCompleteNodeAppeared = false; 

    	// level order 遍历
    	while(!q.isEmpty()) {
    		TreeNode curr = q.poll();
    		
    		if(curr.left == null && curr.right != null) return false; // 不能只有right child

    		if(nonCompleteNodeAppeared) { // 如果之前出现过non complete的node，之后就不能出现任何不是leaf的node
    			if(curr.left != null || curr.right != null) return false;
    		}

    		if(curr.right == null) {
    			nonCompleteNodeAppeared = true;	
    		}

    		if(curr.left != null) q.add(curr.left);
    		if(curr.right != null) q.add(curr.right);
    	}

    	return true;
    }



}