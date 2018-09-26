package math;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in reverse order and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Example:

 Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 0 -> 8
 Explanation: 342 + 465 = 807.
 */

// 注意链表的操作，三个node指针，head，prev，curr，最终结果第一个值在head.next里

public class AddTwoNumbers2 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode prev = head;

        int carry = 0;

        while(l1 != null || l2 != null || carry != 0) {
            ListNode curr = new ListNode(0);
            int sum = ((l2 == null) ? 0 : l2.val ) + ((l1==null) ? 0 : l1.val )+ carry;
            curr.val = sum % 10;
            carry = sum >= 10 ? 1 : 0;

            prev.next = curr;
            prev = curr;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;

        }

        return head.next;

    }
}
