package trees;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the preorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,2,3]
 Follow up: Recursive solution is trivial, could you do it iteratively?

 */

// 用stack模拟递归循序，注意while循环的终止判断（当前node是否null，或stack里是否还有元素可以pop）

public class BinaryTreePreorderTraversal144 {

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> preorderTraversal(TreeNode root) {

        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();

        if(root == null) return list;

        TreeNode curr = root;

        while(curr != null || !stack.empty())
        {
            if(curr == null)
            {
                curr = stack.pop();
                curr = curr.right;
            }
            else
            {
                stack.push(curr);
                list.add(curr.val);
                curr = curr.left;
            }


        }
        return list;
    }

}
