package trees;

/**
Given the root of a binary tree with N nodes, each node in the tree has node.val coins, and there are N coins total.

In one move, we may choose two adjacent nodes and move one coin from one node to another.  (The move may be from parent to child, or from child to parent.)

Return the number of moves required to make every node have exactly one coin.

 

Example 1:



Input: [3,0,0]
Output: 2
Explanation: From the root of the tree, we move one coin to its left child, and one coin to its right child.
Example 2:



Input: [0,3,0]
Output: 3
Explanation: From the left child of the root, we move two coins to the root [taking two moves].  Then, we move one coin from the root of the tree to the right child.
Example 3:



Input: [1,0,2]
Output: 2
Example 4:



Input: [1,0,0,null,3]
Output: 4
 

Note:

1<= N <= 100
0 <= node.val <= N
*/

// 思路：传递需求值，该值可以是正也可以为负，每个root的递归里计算用root自己的val平衡左右child后还剩下的需求值，并返回给上一层
class DistributeCoinsInBinaryTree979 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    private int moveCountSum = 0;
    
    public int distributeCoins(TreeNode root) {
		helper(root);
		return moveCountSum;
	}

    private int helper(TreeNode root) {
    	if (root == null) {
        	return 0;
        }
        // Get left and right move count, update the sum
        int left = helper(root.left);
        int right = helper(root.right);

        moveCountSum += Math.abs(left) + Math.abs(right);

        return left + right + root.val - 1; // 在每个root处，left + right + root.val - 1的值就是用该root的val平衡左右的需求之后还剩下的需求值
    }

}