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

// 每次找到中间的node，作为root，然后向两边递归，在遇到end==start或end-start==1时终止
// 1. end==start，直接返回对于位置的node，
// 2. end-start == 1，如果在最顶部root左边，则start是end的left child，否则end是start的right child
public class ConvertSortedArrToBST108 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) return null;
        return buildTree(nums,0, nums.length - 1);
    }

    private TreeNode buildTree(int[] nums, int start, int end) {
        // 只有一个node
        if(end == start) return new TreeNode(nums[start]);
        // 只有两个node，根据相对最顶端root的位置来判断当前两个node的关系
        if(end - start == 1) {
            if(end <= ((nums.length - 1) / 2)) {
                TreeNode newRoot = new TreeNode(nums[end]);
                newRoot.left = new TreeNode(nums[start]);
                return newRoot;

            }
            else {
                TreeNode newRoot = new TreeNode(nums[start]);
                newRoot.right = new TreeNode(nums[end]);
                return newRoot;
            }

        }

        // 找到中间的node，作为root，然后向两边递归
        int rootIdx = start + (end - start) / 2;
        TreeNode newRoot = new TreeNode(nums[rootIdx]);
        TreeNode left = buildTree(nums, start, rootIdx - 1);
        TreeNode right = buildTree(nums, rootIdx + 1, end);
        newRoot.left = left;
        newRoot.right = right;

        return newRoot;
    }
}
