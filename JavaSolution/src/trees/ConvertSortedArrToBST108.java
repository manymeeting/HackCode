package trees;

/**
 *
 Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted array: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

 0
 / \
 -3   9
 /   /
 -10  5
 */

// 每次找到中间的node，作为root，然后向两边递归，
// 注意边界移动的操作，start和end都是index值，通过mid加减1来移动区间

public class ConvertSortedArrToBST108 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return inOrderBuildTree(nums,0, nums.length - 1);
    }

    private TreeNode inOrderBuildTree(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }

        int mid = start + (end - start) / 2;

        TreeNode left = inOrderBuildTree(nums, start, mid-1);
        TreeNode root = new TreeNode(nums[mid]);
        TreeNode right = inOrderBuildTree(nums, mid + 1, end);

        root.left = left;
        root.right = right;

        return root;

    }
}
