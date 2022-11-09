package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: s = "()"
Output: 1
Example 2:

Input: s = "(())"
Output: 2
Example 3:

Input: s = "()()"
Output: 2
 

Constraints:

2 <= s.length <= 50
s consists of only '(' and ')'.
s is a balanced parentheses string.

(())
stack:
((
stack size:
2
Pop Score:
1 * 2

((())())
socre
(2 + 1) * 2  = 6
2 * 2 + 1 * 2
one l3 + one l2


思考：观察发现一个规律，只要在左括号变成右括号的时候，在pop的同时按照stack里的size来加一下当前这一对的最终等效value即可。

优化：进一步观察可发现，只需要一个int就可以替换stack，int代表当前累计的左括号的个数。
 */
public class ScoreOfParentheses856 {

    // 优化：用int代替stack
    public int scoreOfParentheses2(String s) {
        int sum = 0;
        int balance = 0;

        boolean addingLeft = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                balance++;
                addingLeft = true;
            }
            else {
                if (addingLeft) {
                    // Direction changed, update sum.
                    sum += (1 << balance - 1);
                    addingLeft = false;
                }
                balance--;
            }
        }
        return sum;
    }

    // 基本解法
    public int scoreOfParentheses(String s) {
        int sum = 0;
        Deque<Character> stack = new ArrayDeque<>();

        boolean addingLeft = true;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.addFirst(ch);
                addingLeft = true;
            }
            else {
                if (addingLeft) {
                    // Direction changed, update sum.
                    sum += Math.pow(2, stack.size() - 1);
                    addingLeft = false;
                }
                stack.removeFirst();
            }
        }
        return sum;
    }
}
