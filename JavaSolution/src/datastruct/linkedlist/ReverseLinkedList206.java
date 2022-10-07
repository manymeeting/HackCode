package datastruct;

/**
 * Reverse a singly linked list.

 Example:

 Input: 1->2->3->4->5->NULL
 Output: 5->4->3->2->1->NULL
 Follow up:

 A linked list can be reversed either iteratively or recursively. Could you implement both?
 */

// 解法2：直接reverse，（可以两两一组也可以一个个reverse）
    
public class ReverseLinkedList206 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) {
            return head;
        }
        // 两两一组reverse
        ListNode currHead = null;
        ListNode curr = head;
        while(curr != null) {
            ListNode nxt = curr.next;
            if(nxt == null) {
                curr.next = currHead;
                currHead = curr;
                break;
            }

            ListNode nextHead = nxt.next;
            curr.next = null;
            nxt.next = curr;
            curr.next = currHead;
            currHead = nxt;
            curr = nextHead;

        }

        return currHead;
    }



    // 解法1：递归解法：每次递归遇到null或者next为null，直接返回，
    // 否则交换当前node和nextNode，并返回用nextNode递归之后的节点
    // public ListNode reverseList(ListNode head) {
    //     if(head == null || head.next == null) {
    //         return head;
    //     }

    //     ListNode nextNode = head.next;
    //     ListNode newHead = reverseList(nextNode);
    //     nextNode.next = head; // 此处做reverse
    //     head.next = null;
    //     return newHead; // 最后会返回原list的最后一个节点
    // }
}
