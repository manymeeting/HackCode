package datastruct;

/**
 * Given a linked list, remove the n-th node from the end of list and return its head.

 Example:

 Given linked list: 1->2->3->4->5, and n = 2.

 After removing the second node from the end, the linked list becomes 1->2->3->5.
 Note:

 Given n will always be valid.

 Follow up:

 Could you do this in one pass?

 */

// 用两个pointer，一个left一个right，先把right向右移动n位，然后两个指针同时移动，
// 当right到结尾时，left的下一位就是要删除的node
// 技巧：在head之前加一个start节点，用来处理边界情况（删除的是head）

public class RmNthNodeFromListEnd19 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode right = head;
        ListNode start = new ListNode(0); // dummy head;
        start.next = head;
        ListNode left = start;


        for (int i = 0; i <= n; i++) {
            right = right.next;
        }

        while(right != null) {
            right = right.next;
            left = left.next;
        }

        left.next = left.next.next;
        return start.next;
    }
}
