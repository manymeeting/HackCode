package trees;

import java.util.ArrayList;
import java.util.List;

/**
 * Consider all the leaves of a binary tree.  From left to right order, the values of those leaves form a leaf value sequence.



 For example, in the given tree above, the leaf value sequence is (6, 7, 4, 9, 8).

 Two binary trees are considered leaf-similar if their leaf value sequence is the same.

 Return true if and only if the two given trees with head nodes root1 and root2 are leaf-similar.



 Note:

 Both of the given trees will have between 1 and 100 nodes.
 */

// 普通dfs
public class LeafSimilarTrees872 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leaves1 = new ArrayList<>();
        List<Integer> leaves2 = new ArrayList<>();
        dfs(root1, leaves1);
        dfs(root2, leaves2);

        if(leaves1.size() != leaves2.size()) return false;
        for (int i = 0; i < leaves1.size(); i++) {
            if(leaves1.get(i) != leaves2.get(i)) return false;
        }
        return true;

    }

    private void dfs(TreeNode root, List<Integer> leaves) {
        if(root == null) {
            return;
        }

        if(root.left == null && root.right == null) {
            leaves.add(root.val);
        }
        dfs(root.left, leaves);
        dfs(root.right, leaves);
    }
}
