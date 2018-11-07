package trees;

import java.util.*;

/**
 *
 Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).

 If two nodes are in the same row and column, the order should be from left to right.

 Examples 1:

 Input: [3,9,20,null,null,15,7]

 3
 /\
 /  \
 9  20
 /\
 /  \
 15   7

 Output:

 [
 [9],
 [3,15],
 [20],
 [7]
 ]
 Examples 2:

 Input: [3,9,8,4,0,1,7]

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7

 Output:

 [
 [4],
 [9],
 [3,0,1],
 [8],
 [7]
 ]
 Examples 3:

 Input: [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5)

 3
 /\
 /  \
 9   8
 /\  /\
 /  \/  \
 4  01   7
 /\
 /  \
 5   2

 Output:

 [
 [4],
 [9,5],
 [3,0,1],
 [8,2],
 [7]
 ]

 */

// 把根节点给个序号0，然后开始层序遍历，凡是左子节点则序号减1，右子节点序号加1，这样就可以通过col序号来把相同列的节点值放到一起，
// 用一个TreeMap来建立序号和其对应的节点值的映射，好处是其自动排序功能可以按照col（key）从左到右排序

// 为什么要BFS：根据要求，the order should be from left to right

public class BinaryTreeVerticalTraversal314 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }


    public List<List<Integer>> verticalOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> map = new TreeMap<>();

        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> colQueue = new LinkedList<>();

        // 先把root放进去，col编号为0
        nodeQueue.add(root);
        colQueue.add(0);

        while(!nodeQueue.isEmpty()) {
            TreeNode node = nodeQueue.poll();
            int col = colQueue.poll();

            if(!map.containsKey(col)) {
                map.put(col, new ArrayList<>());
            }
            map.get(col).add(node.val);

            if(node.left != null) {
                nodeQueue.add(node.left);
                colQueue.add(col-1);
            }
            if(node.right != null) {
                nodeQueue.add(node.right);
                colQueue.add(col+1);
            }
        }

        return new ArrayList<>(map.values());
    }
}
