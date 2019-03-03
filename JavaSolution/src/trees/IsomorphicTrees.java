package trees;


// https://blog.csdn.net/lafengxiaoyu/article/details/53256499
// 判断两个Tree是不是同构（可以通过有限次变换左右子树变为同一棵树）
public class IsomorphicTrees {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean isIsomorphic(TreeNode root1, TreeNode root2) {

        if(root1 == null && root2 == null) return true;
        if( (root1 != null && root2 == null) || (root1 == null && root2 != null) ) return false;

        // 现在两个root都不为空
        if(root1.val != root2.val) return false;
        if(root1.left != null && root2.left != null && root1.left.val == root2.left.val) {
            // 如果两个都不为空且左儿子相等，按照没有交换来预测
            return isIsomorphic(root1.left, root2.left) && isIsomorphic(root1.right, root2.right);
        }
        else {
            // 否则就是交换了，递归的判断左对应右，右对应左
            return isIsomorphic(root1.right, root2.left) && isIsomorphic(root1.left, root2.right);
        }

    }
}
