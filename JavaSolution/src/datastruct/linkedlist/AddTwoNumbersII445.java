package datastruct.linkedlist;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

 You may assume the two numbers do not contain any leading zero, except the number 0 itself.

 Follow up:
 What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

 Example:

 Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 Output: 7 -> 8 -> 0 -> 7

 */

// 分别遍历两个list，用两个stack来存放数字，最后一个个pop出来做数字相加操作
    
public class AddTwoNumbersII445 {
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {val = x;}
    }
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();

        while(l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while(l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        int carry = 0;
        int digit = 0;
        ListNode prev = new ListNode(0); // 一个dummy node用来保持指向head的引用

        while(!stack1.isEmpty() && !stack2.isEmpty()) {
            int val1 = stack1.pop();
            int val2 = stack2.pop();
            sum = val1 + val2 + carry;
            carry = sum / 10;
            digit = sum % 10;

            ListNode curr = new ListNode(digit);
            curr.next = prev.next;
            prev.next = curr;
        }

        // 看哪个stack还没空，就把剩下的数都加到list里，注意判断最后剩一个carry的情况
        Deque<Integer> nonEmptyStack = stack1.isEmpty() ? stack2 : stack1;
        while (!nonEmptyStack.isEmpty() || carry > 0) {
            if(!nonEmptyStack.isEmpty()) {
                int val = nonEmptyStack.pop();
                sum = val + carry;
            }
            else {
                sum = carry;
            }

            carry = sum / 10;
            digit = sum % 10;
            ListNode curr = new ListNode(digit);
            curr.next = prev.next;
            prev.next = curr;
        }

        return prev.next;
    }
}
