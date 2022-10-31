package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 
 * You are given a string s and a robot that currently holds an empty string t. Apply one of the following operations until s and t are both empty:

Remove the first character of a string s and give it to the robot. The robot will append this character to the string t.
Remove the last character of a string t and give it to the robot. The robot will write this character on paper.
Return the lexicographically smallest string that can be written on the paper.


Example:

Input: s = "bac"
Output: "abc"
Explanation: Let p denote the written string.
Perform first operation twice p="", s="c", t="ba". 
Perform second operation twice p="ab", s="c", t="". 
Perform first operation p="ab", s="", t="c". 
Perform second operation p="abc", s="", t="".

Constraints:

1 <= s.length <= 10^5
s consists of only English lowercase letters.


思路：不能只考虑相邻char的关系，要考虑全局。比如类似于bdda这样的情况，当遇到d开始升序时，由于后面有a，需要坚持add直到a的位置。
可以用一个freq map来判断当前位置的后面还有没有更小的char，如果有则继续add，否则一直pop直到后面没有全局更小的char为止。

 */
public class PrintLexicographicallySmallestStr2434 {
    public String robotWithString(String s) {

        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        
        Deque<Character> robotStack = new ArrayDeque<>();
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            freq[ch - 'a']--;
            robotStack.addFirst(ch);
            while (!robotStack.isEmpty()) {
                char top = robotStack.peekFirst();
                if (hasSmallerLater(top, freq)) {
                    break;
                }
                res.append(robotStack.removeFirst());
            }
            
        }
        return res.toString();
        
    }

    private boolean hasSmallerLater(char ch, int[] freq) {
        int chIndex = (int) (ch - 'a');
        for (int i = 0; i < chIndex; i++) {
            if (freq[i] > 0) {
                return true;
            }
        }
        return false;
    }

}
