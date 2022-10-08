package datastruct.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * In a linked list of size n, where n is even, the ith node (0-indexed) of the linked list is known as the twin of the (n-1-i)th node, if 0 <= i <= (n / 2) - 1.

For example, if n = 4, then node 0 is the twin of node 3, and node 1 is the twin of node 2. These are the only nodes with twins for n = 4.
The twin sum is defined as the sum of a node and its twin.

Given the head of a linked list with even length, return the maximum twin sum of the linked list.

Input: head = [5,4,2,1]
Output: 6
Explanation:
Nodes 0 and 1 are the twins of nodes 3 and 2, respectively. All have twin sum = 6.
There are no other nodes with twins in the linked list.
Thus, the maximum twin sum of the linked list is 6. 

Constraints:

The number of nodes in the list is an even integer in the range [2, 105].
1 <= Node.val <= 105

思路：最简单的方法是先找到中点，然后用stack存前一半，到后一半的时候一个个pop出来比较结果。
可以优化空间复杂度，先走到中点，然后把后半部分list reverse一下，再用另一个pointer从起点开始，两个pointer的值一个个加起来即可。
 */
public class MaxTwinSumOfLinkedList2130 {
    

//  Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
 

    public int pairSum(ListNode head) {
        
        int listLen = 0;
        ListNode oldHead = head;
        while(head != null) {
            listLen++;
            head = head.next;
        }

        int maxSum = 0;
        head = oldHead;
        Deque<Integer> leftHalfStack = new ArrayDeque<>();
        for(int i = 0; i < listLen; i++) {
            if (i < listLen / 2) {
                leftHalfStack.addFirst(head.val);
            }
            else {
                maxSum = Math.max(maxSum, leftHalfStack.removeFirst() + head.val);
            }
            head = head.next;
        }

        return maxSum;
    }
}
