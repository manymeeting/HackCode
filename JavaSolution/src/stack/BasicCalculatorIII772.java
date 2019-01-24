package stack;

import java.util.ArrayDeque;

/**
 * Implement a basic calculator to evaluate a simple expression string.

 The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

 The expression string contains only non-negative integers, +, -, *, / operators , open ( and closing parentheses ) and empty spaces . The integer division should truncate toward zero.

 You may assume that the given expression is always valid. All intermediate results will be in the range of [-2147483648, 2147483647].

 Some examples:

 "1 + 1" = 2
 " 6-4 / 2 " = 4
 "2*(5+5*2)/3+(6/2+8)" = 21
 "(2+6* 3+5- (3*14/7+2)*5)+3"=-12


 Note: Do not use the eval built-in library function.
 */

// 整体思路类似于BasicCalculatorII，记录当前的sign，维护一个stack，把子表达式的值计算后放入stack，最后把stack中的数相加，
// 对括号的处理用递归来解决，遇到左括号就找到匹配的右括号，然后对内部的substring进行递归，返回一个计算结果给外层

public class BasicCalculatorIII772 {
    public int calculate(String s) {

        // Remove spaces
        s = s.replaceAll("\\s+", "");
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        char sign = '+';

        int i = 0;
        while(i < s.length()) {
            if(s.charAt(i) == '('){
                // 遇到左括号，继续向右看直到遇到匹配的右括号，并对括号内的表达式递归处理
                int j = i + 1;
                int parCount = 1;
                while(j < s.length() && parCount > 0) {
                    if(s.charAt(j) == '(') parCount++;
                    if(s.charAt(j) == ')') parCount--;
                    j++;
                }

                int blockVal = calculate(s.substring(i + 1, j - 1)); // (1+(1+2)), substring -> 1 + (1+2)
                i = j;
                if(sign == '+') {
                    stack.push(blockVal);
                }
                if(sign == '-') {
                    stack.push(-blockVal);
                }
                if(sign == '*') {
                    stack.push(stack.pop() * blockVal);
                }
                if(sign == '/') {
                    stack.push(stack.pop() / blockVal);
                }
            }
            else if(Character.isDigit(s.charAt(i))) {
                // 字符为数字，计算digit的值
                int j = i;
                int val = 0;

                while(j < s.length() && Character.isDigit(s.charAt(j))) {
                    val = val * 10 + (s.charAt(j) - '0');
                    j++;
                }

                i = j;
                if(sign == '+') {
                    stack.push(val);
                }
                if(sign == '-') {
                    stack.push(-val);
                }
                if(sign == '*') {
                    stack.push(stack.pop() * val);
                }
                if(sign == '/') {
                    stack.push(stack.pop() / val);
                }
            }
            else {
                // 其余情况为符号，更改当前符号状态
                sign = s.charAt(i);
                i++;
            }

        }

        // 把stack中值相加
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
