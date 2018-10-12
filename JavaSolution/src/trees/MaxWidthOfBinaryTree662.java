package trees;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

 The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

 Example 1:
 Input:

 1
 /   \
 3     2
 / \     \
 5   3     9

 Output: 4
 Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 Example 2:
 Input:

 1
 /
 3
 / \
 5   3

 Output: 2
 Explanation: The maximum width existing in the third level with the length 2 (5,3).
 Example 3:
 Input:

 1
 / \
 3   2
 /
 5

 Output: 2
 Explanation: The maximum width existing in the second level with the length 2 (3,2).
 Example 4:
 Input:

 1
 / \
 3   2
 /     \
 5       9
 /         \
 6           7
 Output: 8
 Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


 Note: Answer will in the range of 32-bit signed integer.


 */


// 用bfs，每次完整的遍历一层，同时把下一层的所有node的序号放到map里（2*i, 2*i+1, i为当前node序号），
// 当前层的宽度用结束node和开始node的序号相减得到，所有层中取最大width即可

public class MaxWidthOfBinaryTree662 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int widthOfBinaryTree(TreeNode root) {
        if(root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>(); // 记录node和序号的对应关系

        queue.offer(root);
        map.put(root, 1); // 编号从1开始（0也可以）

        int currWidth = 0, maxWidth = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();

            int start = 0, end = 0;

            // 每次遍历一层
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(i == 0) start = map.get(node);
                if(i == size-1) end = map.get(node);
                if(node.left != null) {
                    map.put(node.left, map.get(node) * 2);
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    map.put(node.right, map.get(node) * 2 + 1);
                    queue.offer(node.right);
                }
            }

             currWidth = end - start + 1;
            maxWidth = Math.max(currWidth, maxWidth);
        }

        return maxWidth;

    }
}
