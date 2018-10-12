package strings;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

 An input string is valid if:

 Open brackets must be closed by the same type of brackets.
 Open brackets must be closed in the correct order.
 Note that an empty string is also considered valid.

 Example 1:

 Input: "()"
 Output: true
 Example 2:

 Input: "()[]{}"
 Output: true
 Example 3:

 Input: "(]"
 Output: false
 Example 4:

 Input: "([)]"
 Output: false
 Example 5:

 Input: "{[]}"
 Output: true
 */

// 用stack来保存，遇到左括号压栈，遇到右括号检查栈顶，最后看stack是否为空

public class ValidParentheses20 {
    public boolean isValid(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);

            // 遇到左括号，直接push
            if(curr == '(' || curr == '[' || curr == '{') {
                stack.push(curr);
            }
            // 遇到右括号，检查栈顶元素（注意判断stack是否为空）
            else if(curr == ')' && !stack.isEmpty() && stack.peek() == '(') {
                stack.pop();
            }
            else if(curr == ']' && !stack.isEmpty() && stack.peek() == '[') {
                stack.pop();
            }
            else if(curr == '}' && !stack.isEmpty() && stack.peek() == '{') {
                stack.pop();
            }
            else {
                return false;
            }
        }

        return stack.isEmpty();
    }
}
