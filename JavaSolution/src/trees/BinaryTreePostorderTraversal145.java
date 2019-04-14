package trees;

import java.util.*;


/**

Given a binary tree, return the postorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [3,2,1]
Follow up: Recursive solution is trivial, could you do it iteratively?

*/

// 解法1：用一个stack，从上到下然后再reverse。起始时把root push进去，每次pop出来处理，然后添加左右child（如果不为null），
// 最后reverse list
// (补充：实际上post-order的顺序就是反向（左右顺序颠倒）preOrder结果的reverse)

class BinaryTreePostorderTraversal145 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

	public List<Integer> postorderTraversal(TreeNode root) {
    	Stack<TreeNode> stack = new Stack<>();
    	List<Integer> res = new ArrayList<>();

    	TreeNode curr = null;
    	stack.push(root);
    	while(!stack.isEmpty()) {
    		curr = stack.pop()
    		if(curr.left != null) stack.push(curr.left);
			if(curr.right != null) stack.push(curr.right);
			res.add(curr.val);
    	}    

    	Collections.reverse(res);
    	return res;
    }
}