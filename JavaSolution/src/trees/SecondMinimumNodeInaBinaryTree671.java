package trees;

import java.util.*;

/**
 *
 * Given a non-empty special binary tree consisting of nodes with the non-negative value,
 * where each node in this tree has exactly two or zero sub-node. If the node has two sub-nodes,
 * then this node's value is the smaller value among its two sub-nodes.

 Given such a binary tree, you need to output the second minimum value in the set made of all the nodes'
 value in the whole tree.

 If no such second minimum value exists, output -1 instead.

 Example 1:
 Input:
 2
 / \
 2   5
 / \
 5   7

 Output: 5
 Explanation: The smallest value is 2, the second smallest value is 5.
 Example 2:
 Input:
 2
 / \
 2   2

 Output: -1
 Explanation: The smallest value is 2, but there isn't any second smallest value.

 */

// Pre order 遍历，注意要把叶节点的值也算上，而且要去重复

public class SecondMinimumNodeInaBinaryTree671 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int findSecondMinimumValue(TreeNode root) {

        ArrayList<Integer> vals = new ArrayList<>();
        traversal(root, vals);

        vals.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        Set<Integer> set = new HashSet<>(vals);
        if(set.size() < 2)
        {
            return -1;
        }

        ArrayList<Integer> sorted = new ArrayList<>();
        for (int x : set)
        {
            sorted.add(x);
        }
        sorted.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
        });

        return sorted.get(1);

    }

    public void traversal(TreeNode node, ArrayList<Integer> vals)
    {
        if(node == null)
        {
            return;
        }
        if(node.left == null || node.right == null)
        {
            vals.add(node.val);
            return;
        }

        vals.add(Math.min(node.left.val, node.right.val));
        traversal(node.left, vals);
        traversal(node.right, vals);
    }


}
