package datastruct;

/**
 * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

 k is a positive integer and is less than or equal to the length of the linked list. If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

 Example:

 Given this linked list: 1->2->3->4->5

 For k = 2, you should return: 2->1->4->3->5

 For k = 3, you should return: 3->2->1->4->5

 Note:

 Only constant extra memory is allowed.
 You may not alter the values in the list's nodes, only nodes itself may be changed.


 */

// 写一个helper来reverse两个node之间的所有node，注意一开始的区间head在最后位置会变
    
public class ReverseNodesInKGroup25 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;

        ListNode begin;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        begin = dummy;

        int i = 0;
        while(head != null) {
            i++;
            if(i % k == 0) {
                begin = reverse(begin, head.next);
                head = begin.next;
            }
            else {
                head = head.next;
            }
        }

        return dummy.next; // prev是reverse之后的head
    }

    /**
     * Reverse a link list between begin and end exclusively
     * an example:
     * a linked list:
     * 0->1->2->3->4->5->6
     * |           |
     * begin       end
     * after call begin = reverse(begin, end)
     *
     * 0->3->2->1->4->5->6
     *          |  |
     *      begin end
     * @return the reversed list's 'begin' node, which is the precedence of node end
     */
    private ListNode reverse(ListNode begin, ListNode end) {
        ListNode curr = begin.next;
        ListNode next, prev, first;
        prev = begin;
        first = curr;
        while(curr != end) {
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        begin.next = prev;
        first.next = curr;

        return first;
    }

}
