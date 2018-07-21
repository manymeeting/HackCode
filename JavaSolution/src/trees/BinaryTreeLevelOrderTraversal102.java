package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 Given a binary tree, return the level order traversal of its nodes' values.
 (ie, from left to right, level by level).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its level order traversal as:
 [
 [3],
 [9,20],
 [15,7]
 ]
 */
public class BinaryTreeLevelOrderTraversal102 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    /**
     * 常规BFS，从上向下遍历，用queue存每一层的元素，一个个poll出来处理
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();

        if(root == null) return results;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while(q.size() > 0)
        {
            int n = q.size();
            ArrayList<Integer> levelVals = new ArrayList<>();
            for (int i = 0; i < n; i++)
            {
                TreeNode node = q.poll();
                levelVals.add(node.val);

                if(node.left != null) q.offer(node.left);
                if(node.right != null) q.offer(node.right);
            }
            results.add(levelVals);
        }

        return results;
    }
}
