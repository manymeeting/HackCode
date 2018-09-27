package dp;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

 Example 1:

 Input: "(()"
 Output: 2
 Explanation: The longest valid parentheses substring is "()"
 Example 2:

 Input: ")()())"
 Output: 4
 Explanation: The longest valid parentheses substring is "()()"

 */

// 用stack来放括号的index，每次遇到反括号时看stack顶端是不是正括号，是则pop出来，
// 最后逐一比较index的gap，取最大值，
// 注意最后计算gap时，a=len, b=stack.pop(), gap = a - b - 1

public class LongestValidParentheses32 {
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxLen = 0;

        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(') {
                stack.push(i);
            }
            else { // ')'
                if(!stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                    stack.pop();
                }
                else {
                    stack.push(i);
                }
            }
        }

        if(stack.isEmpty()) return s.length();

        int a = s.length(), b = 0;
        while(!stack.isEmpty()) {
            b = stack.pop();
            maxLen = Math.max(maxLen, a - b - 1);
            a = b;
        }
        maxLen = Math.max(maxLen, a); // 因为stack最后一轮pop出来以后不会进while，需要单独判断一次

        return maxLen;

    }
}
