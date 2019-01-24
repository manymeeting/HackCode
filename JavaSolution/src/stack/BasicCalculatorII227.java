package stack;

import java.util.Stack;

/**Implement a basic calculator to evaluate a simple expression string.

 The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 The integer division should truncate toward zero.

 Example 1:

 Input: "3+2*2"
 Output: 7
 Example 2:

 Input: " 3/2 "
 Output: 1
 Example 3:

 Input: " 3+5 / 2 "
 Output: 5
 Note:

 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.
 */

// 维护一个stack，预设初始sing为+，遇到+或-直接push，
// 遇到*或/则先pop，再push计算后的结果（保证了乘除优先运算）
// 最后遍历stack把结果相加即可

// 注意要在i==s.length()-1的时候也执行sign计算，否则会漏掉最后一部分表达式

public class BasicCalculatorII227 {

    public int calculate(String s) {
        if(s == null || s.length() == 0) return 0;
        Stack<Integer> stack = new Stack<Integer>();

        char sign = '+';
        int num = 0;

        for (int i = 0; i < s.length(); i++)
        {
            if(Character.isDigit(s.charAt(i)))
            {
                num = num * 10 + s.charAt(i) - '0';
            }
            if((!Character.isDigit(s.charAt(i)) && ' '!=s.charAt(i)) || i==s.length()-1)
            {

                if(sign == '+')
                {
                    stack.push(num);
                }
                if(sign == '-')
                {
                    stack.push(-num);
                }
                if(sign == '*')
                {
                    stack.push(stack.pop() * num);
                }
                if(sign == '/')
                {
                    stack.push(stack.pop() / num);
                }

                // reset num
                num = 0;
                sign = s.charAt(i);
            }
        }

        int res = 0;
        for (int x : stack) res += x;
        return res;
    }

}
