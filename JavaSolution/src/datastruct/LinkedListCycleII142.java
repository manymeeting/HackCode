package datastruct;

/**
 * Given a linked list, return the node where the cycle begins. If there is no cycle, return null.

 Note: Do not modify the linked list.

 Follow up:
 Can you solve it without using extra space?


 */

// leetcode.com/problems/linked-list-cycle-ii/discuss/44774/Java-O(1)-space-solution-with-detailed-explanation./44281
// 画图分析可知，在找到fast和slow重合的node后，再用另一个node（temp）从head开始移动，同时移动slow，
// 当slow和temp相遇时就是环开始的位置

public class LinkedListCycleII142 {
    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public ListNode detectCycle(ListNode head) {
        if(head == null || head.next == null) return null;
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && slow != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;

            if(fast == slow) {
                ListNode temp = head;
                while(temp != slow) {
                    temp = temp.next;
                    slow = slow.next;
                }
                return slow;
            }
        }

        return null;
    }
}
