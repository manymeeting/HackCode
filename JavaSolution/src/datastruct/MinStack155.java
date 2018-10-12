package datastruct;

/***
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.

 push(x) -- Push element x onto stack.
 pop() -- Removes the element on top of the stack.
 top() -- Get the top element.
 getMin() -- Retrieve the minimum element in the stack.
 Example:
 MinStack minStack = new MinStack();
 minStack.push(-2);
 minStack.push(0);
 minStack.push(-3);
 minStack.getMin();   --> Returns -3.
 minStack.pop();
 minStack.top();      --> Returns 0.
 minStack.getMin();   --> Returns -2.

 */

// 创建一个Node的class，里面包括val和min（当前的最小值），每次加入新node时，min取新val和当前min的最小值
    
public class MinStack155 {
    /** initialize your data structure here. */

    private  Node head;

    private class Node {
        int val;
        int min;
        Node next;

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }


    public void push(int x) {
        if(head == null) {
            head = new Node(x, x, null);
        }
        else {
            head = new Node(x, Math.min(x, head.min), head); // 每次添加新node时，在该node中保存min
        }
    }

    public void pop() {
        head = head.next;
    }

    public int top() {
        return head.val;
    }

    public int getMin() {
        return head.min;
    }
}
