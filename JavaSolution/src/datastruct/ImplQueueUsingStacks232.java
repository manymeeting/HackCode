package datastruct;

import java.lang.reflect.Array;
import java.util.ArrayDeque;

/**
 * Implement the following operations of a queue using stacks.

 push(x) -- Push element x to the back of queue.
 pop() -- Removes the element from in front of queue.
 peek() -- Get the front element.
 empty() -- Return whether the queue is empty.
 Example:

 MyQueue queue = new MyQueue();

 queue.push(1);
 queue.push(2);
 queue.peek();  // returns 1
 queue.pop();   // returns 1
 queue.empty(); // returns false
 Notes:

 You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty operations are valid.
 Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque (double-ended queue), as long as you use only standard operations of a stack.
 You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */

// 用两个stack，每次往A中push，需要peek和pop时，从A中一个个pop出来放到B里，这样B里的顺序就是queue的顺序，
// 时间复杂度为Amortized O(1)，因为每个元素最多只会被移动一次

public class ImplQueueUsingStacks232 {
    /** Initialize your data structure here. */
    ArrayDeque<Integer> stackA;
    ArrayDeque<Integer> stackB;

    public ImplQueueUsingStacks232() {
        stackA = new ArrayDeque<>();
        stackB = new ArrayDeque<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stackA.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        peek(); // 先peek，保证stackB里有元素
        return stackB.pop();
    }

    /** Get the front element. */
    public int peek() {
        if(stackB.isEmpty()) {
            while(!stackA.isEmpty()) {
                stackB.push(stackA.pop());
            }
        }
        return stackB.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stackA.isEmpty() && stackB.isEmpty();
    }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */