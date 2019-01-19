package strings;

/**
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.

 Example 1:

 Input: S = "ab#c", T = "ad#c"
 Output: true
 Explanation: Both S and T become "ac".
 Example 2:

 Input: S = "ab##", T = "c#d#"
 Output: true
 Explanation: Both S and T become "".
 Example 3:

 Input: S = "a##c", T = "#a#c"
 Output: true
 Explanation: Both S and T become "c".
 Example 4:

 Input: S = "a#c", T = "b"
 Output: false
 Explanation: S becomes "c" while T becomes "b".
 Note:

 1 <= S.length <= 200
 1 <= T.length <= 200
 S and T only contain lowercase letters and '#' characters.
 Follow up:

 Can you solve it in O(N) time and O(1) space?
 */


// 思路1：直接开两个stack，遍历一遍后比较剩余的stack，需要额外空间，
// 思路2：可以从尾部往前看，遇到#就把pointer向左多移动，停下来时再比较当前char是否相等，不用额外空间，

// 总结：遇到从头部遍历需要stack的问题时可以考虑从尾部开始遍历，看是否能简化问题。

public class BackspaceStrCompare844 {
    public boolean backspaceCompare(String S, String T) {

        int i = S.length() > 0 ? S.length() - 1 : 0;
        int j = T.length() > 0 ? T.length() - 1 : 0;

        int bkS = 0, bkT = 0;

        while(i >= 0 || j >= 0) {

            while(i >= 0 && (bkS > 0 || S.charAt(i) == '#')) {
                if(S.charAt(i) == '#') bkS++;
                else bkS--;

                i--;
            }
            char chS = i >= 0 ? S.charAt(i) : '*'; // 全删除时用一个占位char，方便比较

            while(j >= 0 && (bkT > 0 || T.charAt(j) == '#')) {
                if(T.charAt(j) == '#') bkT++;
                else bkT--;

                j--;
            }
            char chT = j >= 0 ? T.charAt(j) : '*';

            if(chS != chT) return false;

            i--;
            j--;
        }

        return true;
    }
}
