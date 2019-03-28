package trees;

/**
 * Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

 For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

 Example:

 Given the sorted linked list: [-10,-3,0,5,9],

 One possible answer is: [0,-3,9,-10,null,5], which represents the following height balanced BST:

 0
 / \
 -3   9
 /   /
 -10  5

 */


// 用inorder遍历来搭建，每次取当前区间的中点作为root的值，
// 技巧：与108不同，这里的list是单链表，为了避免每次递归都从head开始loop，可以维护一个全局的ListNode指针，随着递归移动

public class ConvertSortedListToBST109 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // 全局变量，在递归时起到保存对ListNode中当前位置引用的作用
    private ListNode currListNode;

    public TreeNode sortedListToBST(ListNode head) {
        int max = 0;
        currListNode = head;
        while(head!=null) {
            head = head.next;
            max++;
        }
        if(max == 0) return null;

        return inOrderBuilder(0, max-1);
    }

    // In order形式搭建BST，符合BST的性质
    private TreeNode inOrderBuilder(int min, int max) {
        if(min > max) {
            return null;
        }

        int mid = min + (max - min) / 2;

        TreeNode left = inOrderBuilder(min, mid - 1); // 注意pointer的移动规律，依靠mid加减1来控制
        TreeNode root = new TreeNode(currListNode.val);
        currListNode = currListNode.next;
        TreeNode right = inOrderBuilder(mid + 1, max);

        root.left = left;
        root.right = right;

        return root;
    }
}
