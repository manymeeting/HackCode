package hashtable;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two integers representing the numerator and denominator of a fraction, return the fraction in string format.

 If the fractional part is repeating, enclose the repeating part in parentheses.

 Example 1:

 Input: numerator = 1, denominator = 2
 Output: "0.5"
 Example 2:

 Input: numerator = 2, denominator = 1
 Output: "2"
 Example 3:

 Input: numerator = 2, denominator = 3
 Output: "0.(6)"

 */

// 先append整数部分，然后处理小数，用一个Map<余数，出现的index>来记录出现过的余数，
// 遇到重复的余数时就给出现的index+1位置插入左括号，然后在末尾加右括号，结束


public class FractionToRecurringDecimal166 {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        StringBuilder res = new StringBuilder();
        // 正负号处理
        res.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
        // 转换为正数，同时变为Long（因为负数int最大绝对值比正数大1，所以−2147483648/-1会越界）
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);

        // 整数部分
        res.append(num / den);
        num %= den;
        if (num == 0) {
            return res.toString();
        }

        // 小数部分
        res.append(".");
        Map<Long, Integer> map = new HashMap<>(); // 余数，出现的index
        map.put(num, res.length() - 1);
        while(num != 0) {
            num *= 10;
            res.append(num / den);
            num %= den;
            if(map.containsKey(num)) {
                int idx = map.get(num); // 循环开始的index
                res.insert(idx + 1, "(");
                res.append(")");
                break;
            }
            else {
                map.put(num, res.length() - 1);
            }
        }

        return res.toString();
    }
}
