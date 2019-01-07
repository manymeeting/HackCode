package strings;

import java.util.ArrayDeque;

/**
 *
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

 Example 1:
 Input: "Let's take LeetCode contest"
 Output: "s'teL ekat edoCteeL tsetnoc"
 Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */

// 先split，然后用stack来翻转word

public class ReverseWordsInStringIII557 {
    public String reverseWords(String s) {
        String[] words = s.split("\\s+");
        StringBuilder res = new StringBuilder();
        for (String word : words) {

            StringBuilder sb = new StringBuilder();
            ArrayDeque<Character> stack = new ArrayDeque<>();
            for (char ch : word.toCharArray()) {
                stack.push(ch);
            }

            while(!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            if(res.length() != 0) res.append(" ");
            res.append(sb.toString());
        }

        return res.toString();
    }
}
