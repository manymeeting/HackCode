package trees;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * 
 * You are given a 2D integer array descriptions where descriptions[i] = [parenti, childi, isLefti] indicates that parenti is the parent of childi in a binary tree of unique values. Furthermore,

If isLefti == 1, then childi is the left child of parenti.
If isLefti == 0, then childi is the right child of parenti.
Construct the binary tree described by descriptions and return its root.

The test cases will be generated such that the binary tree is valid.

Constraints:

1 <= descriptions.length <= 104
descriptions[i].length == 3
1 <= parenti, childi <= 105
0 <= isLefti <= 1
The binary tree described by descriptions is valid.


思路：用两个map来记录之前的node，一个记录value到node的ref，一个记录val到parent的ref，帮助之后找到root。
 */

public class CreateBinaryTreeFromDescriptions2196 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode createBinaryTree(int[][] descriptions) {

        Map<Integer, TreeNode> valToNode = new HashMap<>();
        Map<Integer, TreeNode> valToParent = new HashMap<>();
        for (int[] rawNode : descriptions) {
            
            int childVal = rawNode[1];
            int currVal = rawNode[0];
            TreeNode child = valToNode.getOrDefault(childVal, new TreeNode(childVal));
            TreeNode curr = valToNode.getOrDefault(currVal, new TreeNode(currVal));
            if (rawNode[2] == 1) {
                curr.left = child;
            }
            else if (rawNode[2] == 0) {
                curr.right = child;
            }
            valToNode.putIfAbsent(currVal, curr);
            valToNode.putIfAbsent(childVal, child);
            
            valToParent.put(childVal, curr);
        }
        
        // Choose a random node (e.g. the 1st one in the input), and use the hashmap to the the final root.
        int rootVal = descriptions[0][0];
        TreeNode root = valToNode.get(rootVal);
        while(valToParent.containsKey(rootVal)) {
            root = valToParent.get(rootVal);
            rootVal = root.val;
        }

        return root;
    }
}
