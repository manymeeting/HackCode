package trees;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
Given tree t:
   4 
  / \
 1   2
Return true, because t has the same structure and node values with a subtree of s.
Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0
Given tree t:
   4
  / \
 1   2
Return false.

 */


// 先写一个判断从两个给定root开始，是否两个tree完全相同的function isTreeSame，
// 然后在对入口函数做递归，每次只移动s的节点，t不动，每遍递归调用一次isTreeSame

public class SubtreeOfAnotherTree572 {

    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {
        if(s == null || t == null) return false;

        if (isTreeSame(s, t)) {
            return true;
        }
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }


    private boolean isTreeSame(TreeNode rootA, TreeNode rootB) {

        if(rootA == null && rootB == null) return true;
        if(rootA == null || rootB == null) return false;
        if(rootA.val != rootB.val) return false;

        return isTreeSame(rootA.left, rootB.left) && isTreeSame(rootA.right, rootB.right);
    }

}
