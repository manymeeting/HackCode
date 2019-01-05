package math;

import javafx.beans.binding.StringBinding;

import java.util.ArrayDeque;

/**
 * Given a non-negative integer num represented as a string, remove k digits from the number so that the new number is the smallest possible.

 Note:
 The length of num is less than 10002 and will be ≥ k.
 The given num does not contain any leading zero.
 Example 1:

 Input: num = "1432219", k = 3
 Output: "1219"
 Explanation: Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
 Example 2:

 Input: num = "10200", k = 1
 Output: "200"
 Explanation: Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
 Example 3:

 Input: num = "10", k = 2
 Output: "0"
 Explanation: Remove all the digits from the number and it is left with nothing which is 0.
 */

// 等价于每次删除一个peak元素（从左往右遍历，遇到前一个比后一个数大时就删），直到删除k个为止，
// 用stack来操作，每次把数字放到stack，用pop来模拟删除过程，
// 注意可能没有足够的peak元素，需要最后pop直到k个为止

public class RemoveKDigits402 {
    public String removeKdigits(String num, int k) {

        ArrayDeque<Character> stack = new ArrayDeque<>();
        int len = num.length();
        if (k == len) return "0"; // Corner case

        int i = 0;
        while(i < len) {

            // 当遇到peak的数字时（前一个比后一个大），就pop出来，表示删除一个
            while(!stack.isEmpty() && stack.peek() > num.charAt(i) && k > 0) {
                stack.pop();
                k--;
            }
            stack.push(num.charAt(i));
            i++;
        }

        // 没有足够的peak元素，则从尾部开始pop出剩余的（比如221111）
        while (k > 0) {
            stack.pop();
            k--;
        }


        // 拼接最终结果
        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse(); // 需要reverse才能得到正确顺序


        // 可能出现0在头部的情况
        while(sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        return sb.toString();
    }
}
