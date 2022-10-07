package datastruct;
/**
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

 Example:

 Input: 1->2->4, 1->3->4
 Output: 1->1->2->3->4->4

 */

// 每次比较l1和l2的next，选小的作为下一个
// 技巧：维护两个pointer，prev和head

public class MergeTwoSortedLists21 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;};
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode prev = new ListNode(0);
        ListNode head = prev;
        while(l1 != null && l2 != null) {
            if(l1.val > l2.val) {
                prev.next = l2;
                l2 = l2.next;
            }
            else {
                prev.next = l1;
                l1 = l1.next;
            }
            prev = prev.next;
        }

        prev.next = l1 == null ? l2 : l1;

        return head.next;
    }
}
