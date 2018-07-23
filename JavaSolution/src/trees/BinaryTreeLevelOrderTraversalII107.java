package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values.
 * (ie, from left to right, level by level from leaf to root).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its bottom-up level order traversal as:
 [
 [15,7],
 [9,20],
 [3]
 ]
 */
public class BinaryTreeLevelOrderTraversalII107 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> results = new LinkedList<>();

        if(root == null) return results;

        LinkedList<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(q.size() > 0)
        {
            int size = q.size();
            ArrayList<Integer> levelVals = new ArrayList<>();
            for (int i = 0; i < size; i++)
            {
                TreeNode node = q.poll();
                levelVals.add(node.val);
                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }

            results.add(0, levelVals);
        }

        return results;

    }
}
