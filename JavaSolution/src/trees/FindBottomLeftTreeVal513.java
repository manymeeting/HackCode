package trees;

import java.util.LinkedList;

/**
 * Given a binary tree, find the leftmost value in the last row of the tree.

 Example 1:
 Input:

 2
 / \
 1   3

 Output:
 1
 Example 2:
 Input:

 1
 / \
 2   3
 /   / \
 4   5   6
 /
 7

 Output:
 7
 Note: You may assume the tree (i.e., the given root node) is not NULL.
 */

// BFS按层遍历（queue+每层按node数for-loop），每次更新res为最左端节点的val，遍历结束后即可得到结果

public class FindBottomLeftTreeVal513 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public int findBottomLeftValue(TreeNode root) {

        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int res = 0;
        int levelNodeCnt = 1;
        while(!queue.isEmpty()) {
            int nextLevelNodeCnt = 0;
            for (int i = 0; i < levelNodeCnt; i++) {
                TreeNode curr = queue.poll();

                if(i == 0) res = curr.val;

                if(curr.left != null) {
                    queue.offer(curr.left);
                    nextLevelNodeCnt++;

                }
                if(curr.right != null) {
                    queue.offer(curr.right);
                    nextLevelNodeCnt++;
                }
            }


            levelNodeCnt = nextLevelNodeCnt;

        }

        return res;
    }
}
