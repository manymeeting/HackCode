package trees;

import java.util.ArrayList;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

 Note:
 You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

 Example 1:

 Input: root = [3,1,4,null,2], k = 1
 3
 / \
 1   4
 \
 2
 Output: 1
 Example 2:

 Input: root = [5,3,6,2,4,null,null,1], k = 3
 5
 / \
 3   6
 / \
 2   4
 /
 1
 Output: 3
 Follow up:
 What if the BST is modified (insert/delete operations)
 often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?


 */

// 中序遍历BST，把结果存在list里，自然就是按从小到大的循序排列的，直接get相应位置的值

public class KthSmallestElementInBST230 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int kthSmallest(TreeNode root, int k) {
        ArrayList<Integer> vals = new ArrayList<>();
        traversal(root, vals);
        return vals.get(k-1);
    }

    public void traversal(TreeNode node, ArrayList<Integer> vals)
    {
        if(node == null)
        {
            return;
        }

        traversal(node.left, vals);
        vals.add(node.val);
        traversal(node.right, vals);

    }
}
