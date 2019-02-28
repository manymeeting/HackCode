package trees;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

 For example:
 Given binary tree [3,9,20,null,null,15,7],
 3
 / \
 9  20
 /  \
 15   7
 return its zigzag level order traversal as:
 [
 [3],
 [20,9],
 [15,7]
 ]
 */


// 在传统bfs基础上通过add(0, ele)来改变向结果集中添加的方向

public class BTZigzagLvlOrderTravsl103 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        ArrayDeque<TreeNode> queue = new ArrayDeque<>();
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;

        queue.add(root);
        int size = 1;
        boolean dirRight = false;
        while(!queue.isEmpty()) {
            int newSize = 0;
            List<Integer> currLvl = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                TreeNode curr = queue.poll();
                if(dirRight) {
                    currLvl.add(0, curr.val); // 用add(0, ele)来不断在head之前添加
                }
                else {
                    currLvl.add(curr.val); // 向尾部添加
                }


                if(curr.left != null){
                    queue.add(curr.left);
                    newSize++;
                }
                if(curr.right != null){
                    queue.add(curr.right);
                    newSize++;
                }
            }
            res.add(currLvl);
            size = newSize;
            dirRight = !dirRight;
        }

        return res;
    }

}
