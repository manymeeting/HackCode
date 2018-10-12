package datastruct;

import java.util.List;

/**
 * Remove all elements from a linked list of integers that have value val.

 Example:

 Input:  1->2->6->3->4->5->6, val = 6
 Output: 1->2->3->4->5
 */


// 在head之前搞一个prev，同时维护一个statichead一直指向最开始的prev（为了最后返回头部）
public class RemoveLinkedListEle203 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode removeElements(ListNode head, int val) {
        if(head == null) return null;


        ListNode prev = new ListNode(0); // dummy node
        prev.next = head;

        ListNode staticHead = prev; // keep a pointer to the original head


        ListNode curr = head;

        while(curr != null) {
            if(curr.val == val) {
                prev.next = curr.next;
            }
            else {
                prev = curr; // 注意如果遇到需要删除的node，删除后prev本身不移动
            }
            curr = curr.next;
        }

        return staticHead.next;

    }
}
