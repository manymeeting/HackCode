package datastruct.linkedlist;

/**
 *
 Given a linked list, determine if it has a cycle in it.

 Follow up:
 Can you solve it without using extra space?

 */

// 用fast和slow来解决，注意while循环的空值判断

public class LinkedListCycle141 {
    class ListNode {
         int val;
         ListNode next;
         ListNode(int x) {
             val = x;
             next = null;
         }
    }
    public boolean hasCycle(ListNode head) {
        if(head == null || head.next == null) return false;

        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null && fast.next != null) {
            if(fast == slow) return true;
            fast = fast.next.next;
            slow = slow.next;
        }

        return false;
    }
}
