package datastruct.linkedlist;


/**
 *
 * Given a singly linked list, group all odd nodes together followed by the even nodes. Please note here we are talking about the node number and not the value in the nodes.

 You should try to do it in place. The program should run in O(1) space complexity and O(nodes) time complexity.

 Example 1:

 Input: 1->2->3->4->5->NULL
 Output: 1->3->5->2->4->NULL
 Example 2:

 Input: 2->1->3->5->6->4->7->NULL
 Output: 2->3->6->7->1->5->4->NULL
 Note:

 The relative order inside both the even and odd groups should remain as it was in the input.
 The first node is considered odd, the second node even and so on ...

 */

// 用奇偶两个pointer加两个head来做，注意while中用evenPointer来判断是否终止，保证odd的指针最后不是空

public class OddEvenLinkedList328 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode oddEvenList(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return head;

        ListNode oddPointer = head;
        ListNode evenPointer = head.next;

        ListNode oddHead = oddPointer;
        ListNode evenHead = evenPointer;

        // 注意while要用evenPointer来判断终止，因为这样可以保证最后oddPointer不是null，直接连到evenHead即可
        while(evenPointer != null && evenPointer.next != null) {

            oddPointer.next = oddPointer.next.next;
            evenPointer.next = evenPointer.next.next;

            evenPointer = evenPointer.next;
            oddPointer = oddPointer.next;
        }

        oddPointer.next = evenHead;

        return oddHead;
    }
}
