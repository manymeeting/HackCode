package datastruct;

import java.util.List;

/**
 * Given a linked list, swap every two adjacent nodes and return its head.

 Example:

 Given 1->2->3->4, you should return the list as 2->1->4->3.
 Note:

 Your algorithm should use only constant extra space.
 You may not modify the values in the list's nodes, only nodes itself may be changed.

 */

// 用多个pointer来操作，while循环里两个一组来操作，注意需要维护一个prevTail，在下一组交换顺序后连接到新的head
public class SwapNodesInPairs24 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

    }
    public ListNode swapPairs(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        ListNode finalHead = head;
        if(head != null) {
            if(head.next != null) {
                finalHead = head.next;
            }
        }
        ListNode prevTail = null;
        while(first != null && first.next != null) {

            second = first.next;
            ListNode nextFirst = second.next;
            second.next = first;
            first.next = nextFirst;
            // 注意判断border case，prevTail需要初始化为null否则会出现循环link
            if(prevTail != null) prevTail.next = second;


            prevTail = first;
            first = nextFirst;

        }

        return finalHead;
    }
}
