package trees;

/**
 * Given a non-empty binary search tree and a target value,
 * find the value in the BST that is closest to the target.

 Note:

 Given target value is a floating point.
 You are guaranteed to have only one unique value in the BST that is closest to the target.
 Example:

 Input: root = [4,2,5,1,3], target = 3.714286

 4
 / \
 2   5
 / \
 1   3

 Output: 4

 */

// dfs遍历树，比较node和target大小，更新diff绝对值
public class ClosestBSTValue270 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }
    public int closestValue(TreeNode root, double target) {
        return findCloset(root, target, root.val, Double.MAX_VALUE);
    }

    public int findCloset(TreeNode root, double target, int resVal, double minDiff)
    {
        if(root == null) return resVal;
        
        if(Math.abs(root.val - target) < minDiff ) {
            resVal = root.val;
            minDiff= Math.abs(root.val - target);
        }

        if(root.val > target)
        {
            return findCloset(root.left, target, resVal, minDiff);
        }
        else
        {
            return findCloset(root.right, target, resVal, minDiff);
        }

    }

}
