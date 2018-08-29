package strings;

/***
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 Example 1:

 Input: "1 + 1"
 Output: 2
 Example 2:

 Input: " 2-1 + 2 "
 Output: 3
 Example 3:

 Input: "(1+(4+5+2)-3)+(6+8)"
 Output: 23
 Note:
 You may assume that the given expression is always valid.
 Do not use the eval built-in library function.

 */

import java.util.Stack;

// 注意只有加减，不用考虑乘除
// 处理左括号时先push当前的res，再push当前的sign，最后reset当前res和num，
// 处理右括号则先算当前res，再连续pop计算两次，
// 注意最后要check一下是否有未计算的num在末尾，有的话加到res里

public class BasicCalculators224 {

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        int sign = 1;
        int res = 0;

        for (int i=0; i < s.length(); i++)
        {
            char ch = s.charAt(i);

            if(Character.isDigit(ch))
            {
                num = num * 10 + ch - '0';
            }
            else
            {
                if(ch == '+')
                {
                    res += sign * num;
                    num = 0;
                    sign = 1;
                }
                if(ch == '-')
                {
                    res += sign * num;
                    num = 0;
                    sign = -1;
                }
                if(ch == '(')
                {
                    // push the result first, then push the sign
                    stack.push(res);
                    stack.push(sign);
                    // reset the sign and result for values in the parenthesis
                    res = 0;
                    sign = 1;

                }
                if(ch == ')')
                {
                    res += sign * num;
                    // calculate the value in this parenthesis
                    res *= stack.pop();
                    res += stack.pop();
                    num = 0;
                }
            }
        }

        if(num != 0) res = res + sign * num;

        return res;
    }
}
