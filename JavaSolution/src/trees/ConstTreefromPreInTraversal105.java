package trees;

/**
 *
 Given preorder and inorder traversal of a tree, construct the binary tree.

 Note:
 You may assume that duplicates do not exist in the tree.

 For example, given

 preorder = [3,9,20,15,7]
 inorder = [9,3,15,20,7]
 Return the following binary tree:

 3
 / \
 9  20
 /  \
 15   7
 */

// 用dfs构建，每次先看preStart（初始为0），该位置为root，然后在inorder里找到值相同的idx，
// idx左边到inStart为止就是inorder的左子树，右边到inEnd是右子树

public class ConstTreefromPreInTraversal105 {
    public class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode(int x) { val = x; }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return dfsBuilder(0, 0, inorder.length-1, preorder, inorder);
    }

    private TreeNode dfsBuilder(int preStart, int inStart, int inEnd,
                                int[] preOrder, int[]inOrder) {

        // 不要忘了check几个index是否越界
        if (preStart > preOrder.length - 1 || inStart > inEnd) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preStart]); // preStart代表当前层级root
        int rootIndexOfInorder = 0; // 再根据root的值找出root在inorder中的位置
        for (int i = inStart; i <= inEnd; i++) {
            if(inOrder[i] == root.val) {
                rootIndexOfInorder = i;
            }
        }

        root.left = dfsBuilder(preStart + 1, inStart, rootIndexOfInorder - 1,
                preOrder, inOrder);

        //右子树最顶端的节点在preorder数组中的起始位置：preStart + rootIndexOfInorder - inStart + 1
        root.right = dfsBuilder(preStart + rootIndexOfInorder - inStart + 1, rootIndexOfInorder + 1, inEnd,
                preOrder, inOrder);

        return root;

    }
}
