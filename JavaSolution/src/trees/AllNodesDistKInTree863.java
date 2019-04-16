package trees;

import java.util.*;
/**
We are given a binary tree (with root node root), a target node, and an integer value K.

Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.

 

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.
 

Note:

The given tree is non-empty.
Each node in the tree has unique values 0 <= node.val <= 500.
The target node is a node in the tree.
0 <= K <= 1000.

*/

// 分两步找，
// 1. 从target开始，往下找距离为K的
// 2. 从target开始，找target之上距离为K的，和经过某个parent能连通且距离为K的

class AllNodesDistKInTree863 {
	public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    List<Integer> res;
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        
        res = new ArrayList<>();

        findBelowTarget(target, K, 0);
        findAboveTarget(root, target, K);

        return res;

    }

    private int findAboveTarget(TreeNode root, TreeNode target, int K) {
    	if(root == null) return Integer.MIN_VALUE;
    	if(root == target) return 0;

    	int left = findAboveTarget(root.left, target, K);
    	int right = findAboveTarget(root.right, target, K);

    	if(left == K - 1 || right == K - 1) {
    		res.add(root.val);
    	}

    	int retVal = Integer.MIN_VALUE;
    	if(left != Integer.MIN_VALUE || right != Integer.MIN_VALUE) {
    		if(left >= 0) {
    			retVal = left + 1;
    			findBelowTarget(root.right, K, left + 2);	
    		}

    		if(right >= 0) {
    			retVal = right + 1;	
    			findBelowTarget(root.left, K, right + 2);
    		}
    	}

    	return retVal;

    }

    private void findBelowTarget(TreeNode curr, int K, int currDist) {
    	if(curr == null) return;

    	if(currDist == K) {
    		res.add(curr.val);
    	}
    	findBelowTarget(curr.left, K, currDist + 1);
    	findBelowTarget(curr.right, K, currDist + 1);
    }


    
}