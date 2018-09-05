package datastruct;

import java.util.List;

/***
 *
 * Reverse a linked list from position m to n. Do it in one-pass.

 Note: 1 ≤ m ≤ n ≤ length of list.

 Example:

 Input: 1->2->3->4->5->NULL, m = 2, n = 4
 Output: 1->4->3->2->5->NULL
 */

// 需要画图来分析连接关系
public class ReverseLinkedListII92 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null) return null;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;

        for (int i = 0; i < m - 1; i++)
        {
            pre = pre.next;
        }

        ListNode start = pre.next;
        ListNode then = start.next;

        for (int i = 0; i < n-m; i++)
        {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }


        return dummy.next;

    }
}
