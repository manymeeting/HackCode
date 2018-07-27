package trees;

/**
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.

 Note:
 A subtree must include all of its descendants.

 Example:

 Input: [10,5,15,1,8,null,7]

 10
 / \
 5  15
 / \   \
 1   8   7

 Output: 3
 Explanation: The Largest BST Subtree in this case is the highlighted one.
 The return value is the subtree's size, which is 3.
 Follow up:
 Can you figure out ways to solve it with O(n) time complexity?
 */


/**
 * 用动态规划的思想，在每个节点的处理返回一个int数组，
      [0] --> min
      [1] --> max
      [2] --> largest BST in its subtree(inclusive)
 然后根据左边最大值和右边最小值来更新最大BST的size
 注意null节点的处理，要保证叶节点的val比右边最小值小且比左边最大值大，于是null节点须返回 [MAX_VALUE, MIN_VALUE, 0]

 * */
public class LargestBSTSubtree333 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int largestBSTSubtree(TreeNode root) {
        int[] res = traverse(root);
        return res[2];
    }

    public int[] traverse(TreeNode node) {
        if(node == null)
        {
            return new int[]{Integer.MAX_VALUE, Integer.MIN_VALUE, 0};
        }

        int[] leftStatus = traverse(node.left);
        int[] rightStatus = traverse(node.right);
        if(leftStatus[1] < node.val && rightStatus[0] > node.val)
        {
            return new int[]{Math.min(node.val, leftStatus[0]), Math.max(node.val,
                    rightStatus[1]), leftStatus[2] + rightStatus[2] + 1};
        }
        else {
            return new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(leftStatus[2], rightStatus[2])};
        }

    }

}
