package datastruct;

import java.util.*

/**
Given a sorted linked list, delete all duplicates such that each element appear only once.

Example 1:

Input: 1->1->2
Output: 1->2
Example 2:

Input: 1->1->2->3->3
Output: 1->2->3
*/


// 每次判断curr和nextNode，遇到dup的时候用while，直到遇到不一样的
class RemoveDupFromSortedList83 {

	public class ListNode {
		int val;
		ListNode next;
		ListNode(int x) { val = x; }
	}
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;

        while(curr != null && curr.next != null) {
        	ListNode nextNode = curr.next;
        	if(curr.val == nextNode.val) {
        		while(nextNode != null && curr.val == nextNode.val) { // 注意判断null
	        		nextNode = nextNode.next;

	        	}
	        	// Delete node(s)
	    		curr.next = nextNode;
	    		curr = nextNode;
	        }
	        else {
	        	curr = nextNode;
	        }
        	
        	
        }

        return head;
    }
}