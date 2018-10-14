package trees;

/**
 * Given a binary tree

 struct TreeLinkNode {
 TreeLinkNode *left;
 TreeLinkNode *right;
 TreeLinkNode *next;
 }
 Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

 Initially, all next pointers are set to NULL.

 Note:

 You may only use constant extra space.
 Recursive approach is fine, implicit stack space does not count as extra space for this problem.
 Example:

 Given the following binary tree,

 1
 /  \
 2    3
 / \    \
 4   5    7
 After calling your function, the tree should look like:

 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \    \
 4-> 5 -> 7 -> NULL

 */

// 用一个dummy节点来保存每一层最左端的引用，实现level order遍历，
// 此解法可以直接用于116

public class PopulatingNextRightPtrsII117 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
    public void connect(TreeLinkNode root) {

        while(root != null) {
            // dummyHead的next一直指向下一层的最左端元素，这样可以在每层结束后拿到下一层最左端的引用
            TreeLinkNode dummyHead = new TreeLinkNode(0);
            TreeLinkNode curr = dummyHead;
            while(root != null) {
                // 此处连接下一层的next关系
                if(root.left != null) {
                    curr.next = root.left;
                    curr = curr.next;
                }
                if(root.right != null) {
                    curr.next = root.right;
                    curr = curr.next;
                }
                root = root.next; // 在当前层中，向右移动root直到最右端
            }
            root = dummyHead.next;
        }
    }
}
