package datastruct.linkedlist;

import java.util.Random;

/**
 * Given a singly linked list, return a random node's value from the linked list.
 * Each node must have the same probability of being chosen.

 Follow up:
 What if the linked list is extremely large and its length is unknown to you?
 Could you solve this efficiently without using extra space?

 Example:

 // Init a singly linked list [1,2,3].
 ListNode head = new ListNode(1);
 head.next = new ListNode(2);
 head.next.next = new ListNode(3);
 Solution solution = new Solution(head);

 // getRandom() should return either 1, 2, or 3 randomly.
 Each element should have equal probability of returning.
 solution.getRandom();

 */

// 水塘抽样，从head开始遍历，用rand让下一个value成为res的概率从1递减即可

public class LinkedListRandomNode382 {

    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    ListNode head;
    Random rand;
    public LinkedListRandomNode382(ListNode head) {
        this.head = head;
        rand = new Random();
    }

    /** Returns a random node's value. */
    public int getRandom() {

        int res = this.head.val;
        ListNode node = this.head;

        for (int i = 1; node.next != null; i++)
        {
            node = node.next;
            if(rand.nextInt(i+1) == 0)
            {
                res = node.val;
            }
        }

        return res;
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
