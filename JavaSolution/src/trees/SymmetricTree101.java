package trees;

/**
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

 For example, this binary tree [1,2,2,3,4,4,3] is symmetric:

 1
 / \
 2   2
 / \ / \
 3  4 4  3
 But the following [1,2,2,null,3,null,3] is not:
 1
 / \
 2   2
 \   \
 3    3
 Note:
 Bonus points if you could solve it both recursively and iteratively.


 */

// 递归参数为left和right，每次先看left和right的val是否相等，
// 然后再递归检查left.left, right.right和left.right，right.left是否相等

public class SymmetricTree101 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);

    }

    private boolean helper(TreeNode left, TreeNode right) {
        if(left == null || right == null) {
            return left == right;
        }
        if(left.val != right.val) return false;

        return helper(left.left, right.right) && helper(left.right, right.left);
    }

    /*
    不用递归的解法（用stack每次pop两个，然后push两个node的左右children）

    * private boolean isSymmetricISC(TreeNode root){
        if(root == null) return true;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root.left);
        stack.push(root.right);
        TreeNode left, right;

        while(!stack.empty()){
            left = stack.pop();
            right = stack.pop();

            if(left == null && right == null){
                continue;
            }
            if(left==null || right == null || left.val != right.val){
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }**/

}
