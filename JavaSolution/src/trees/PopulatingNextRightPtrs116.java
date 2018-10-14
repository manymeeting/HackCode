package trees;


import java.util.LinkedList;
import java.util.Queue;

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
 You may assume that it is a perfect binary tree (ie, all leaves are at the same level, and every parent has two children).
 Example:

 Given the following perfect binary tree,

 1
 /  \
 2    3
 / \  / \
 4  5  6  7
 After calling your function, the tree should look like:

 1 -> NULL
 /  \
 2 -> 3 -> NULL
 / \  / \
 4->5->6->7 -> NULL

 */

// 需要O（1）空间：可以直接用117的解法
// 如果不考虑需要O（1）空间，如下，可以bfs遍历，每个node连接到queue里peek出来的，技巧：根据题意，可以通过每层后都加一个null节点（包括root层），保证每层最后一个指向null
public class PopulatingNextRightPtrs116 {
    public class TreeLinkNode {
        int val;
        TreeLinkNode left, right, next;
        TreeLinkNode(int x) { val = x; }
    }
    public void connect(TreeLinkNode root) {

        if(root == null) return; // 边界情况，不查会导致dead loop

        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.add(root);
        // 加一个null，为了让每一层最右边的元素指向null
        queue.add(null);
        while(!queue.isEmpty()) {

            TreeLinkNode node = queue.poll();
            if(node == null) { // 每层的最后一个null元素，如果下面一层还有，就需要再放回去
                if(queue.size() > 0) queue.add(null);
            }
            else {
                node.next = queue.peek(); // 连接node
                if(node.left != null ) queue.add(node.left);
                if(node.right != null ) queue.add(node.right);
            }
        }
    }
}
