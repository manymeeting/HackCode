package heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

 Example:

 Input:
 [
 1->4->5,
 1->3->4,
 2->6
 ]
 Output: 1->1->2->3->4->4->5->6
 */

// 一开始把list里起始的node放到pq里，不断把当前node的next放到pq里，直到pq为空，
// 注意维护一个指向head的指针

public class MergeKSortedLists23 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        for (ListNode node : lists) {
            if(node != null) pq.add(node);
        }

        ListNode head = new ListNode(0);
        ListNode tail = head;


        while(!pq.isEmpty()) {
            tail.next = pq.poll();
            tail = tail.next;

            if(tail.next != null) {
                pq.add(tail.next);
            }
        }

        return head.next;
    }

}
