package twopointer;

/**
 * Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

 You should preserve the original relative order of the nodes in each of the two partitions.

 Example:

 Input: head = 1->4->3->2->5->2, x = 3
 Output: 1->2->2->4->3->5

 */

// 维护两个pointer，都从头开始，一个连接起所有小于x的node，另一个连接>=x的node，最后把两个linked list首位相连

public class PartitionList86 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
    public ListNode partition(ListNode head, int x) {
        ListNode dummy1 = new ListNode(0);
        ListNode dummy2 = new ListNode(0);

        ListNode tail1 = dummy1;
        ListNode tail2 = dummy2;
        while(head != null) {
            if(head.val < x) {
                tail1.next = head;
                tail1 = head;
            }
            else {
                tail2.next = head;
                tail2 = head;
            }

            head = head.next;
        }

        tail2.next = null; // 注意清空尾部的pointer，否则可能有cycle出现
        tail1.next = dummy2.next;
        return dummy1.next;
    }
}
