package arrays;

/**
 * Given a singly linked list, determine if it is a palindrome.

 Example 1:

 Input: 1->2
 Output: false
 Example 2:

 Input: 1->2->2->1
 Output: true
 Follow up:
 Could you do it in O(n) time and O(1) space?


 */

// 解法1：递归，一个pointer存从左端开始的，用递归栈里保存的当前head来与之做比较
// 解法2：两个pointer，一个fast每次移动两个，一个slow每次移动一个，可以找出中点然后reverse后半段来比较 （算上递归的话不是o(1)空间）

public class PalindromeLinkedList234 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

    // used in recursion
    ListNode h;
    public boolean isPalindrome(ListNode head) {
        if(head == null) return true;

        if(h == null) h = head; // initialize

        boolean temp = true;
        if(head.next != null) temp &= isPalindrome(head.next);

        // compare value of two sides
        temp &= (head.val == h.val);
        h = h.next;
        return temp;


    }
}
