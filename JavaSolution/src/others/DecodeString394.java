package others;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given an encoded string, return it's decoded string.

 The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated exactly k times. Note that k is guaranteed to be a positive integer.

 You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.

 Furthermore, you may assume that the original data does not contain any digits and that digits are only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].

 Examples:

 s = "3[a]2[bc]", return "aaabcbc".
 s = "3[a2[c]]", return "accaccacc".
 s = "2[abc]3[cd]ef", return "abcabccdcdcdef".

 */

// 用两个stack，一个记录当前的字符串，另一个记录当前需要的重复次数，遇到数字时更新次数，遇到[]时做字符串拼接

public class DecodeString394 {
    public String decodeString(String s) {
        String res = "";
        Deque<Integer> countStack = new ArrayDeque<>();
        Deque<String> resStack = new ArrayDeque<>();

        int idx = 0;
        while(idx < s.length()) {

            if(Character.isDigit(s.charAt(idx))) {
                int count = 0;
                while(Character.isDigit(s.charAt(idx))) {
                    count = count * 10 + (s.charAt(idx) - '0'); // 通过直接减'0'来得到char代表的数字
                    idx++;
                }
                countStack.push(count);
            }
            else if(s.charAt(idx) == '[') {
                resStack.push(res);
                res = "";
                idx++;
            }
            else if(s.charAt(idx) == ']') {
                // 从两个stack中分别pop出当前字符串和需要重复的次数
                StringBuilder sb = new StringBuilder(resStack.pop());
                int repeatTime = countStack.pop();
                for (int i = 0; i < repeatTime; i++) {
                    sb.append(res);
                }

                res = sb.toString();
                idx++;
            }
            else {
                // 重复体
                res += s.charAt(idx);
                idx++;
            }
        }

        return res;
    }
}
