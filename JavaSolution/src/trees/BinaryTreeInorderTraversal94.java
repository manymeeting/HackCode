package trees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 *
 * Given a binary tree, return the inorder traversal of its nodes' values.

 Example:

 Input: [1,null,2,3]
 1
 \
 2
 /
 3

 Output: [1,3,2]
 Follow up: Recursive solution is trivial, could you do it iteratively?
 */

// 用stack来维护当前的root，在一个while循环里，用一个curr指针从root开始，如果left不为空就一直curr=curr.left，
// 否则curr回退到pop出的node，进行处理，然后curr = curr.right

// 循环终止条件：curr和stack都为空

public class BinaryTreeInorderTraversal94 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();

        Stack<TreeNode> stack = new Stack<>();
        if(root == null) return list;

        TreeNode curr = root;

        while(curr != null || !stack.empty())
        {
            if(curr == null)
            {
                curr = stack.pop();
                list.add(curr.val);
                curr = curr.right;
            }
            else
            {
                stack.push(curr);
                curr = curr.left;
            }

        }

        return list;
    }
}
