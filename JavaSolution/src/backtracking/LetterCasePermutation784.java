package backtracking;


import com.sun.deploy.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.  Return a list of all possible strings we could create.

 Examples:
 Input: S = "a1b2"
 Output: ["a1b2", "a1B2", "A1b2", "A1B2"]

 Input: S = "3z4"
 Output: ["3z4", "3Z4"]

 Input: S = "12345"
 Output: ["12345"]
 Note:

 S will be a string with length between 1 and 12.
 S will consist only of letters or digits.

 * */
// 可用多种dfs写法，backtracking + StringBuilder，或者直接修改index对应的字符+递归

public class LetterCasePermutation784 {
    public List<String> letterCasePermutation(String S) {

        List<String> res = new ArrayList<>();
        if(S.matches("\\d+")) {
            res.add(S);
            return res;
        }

        helper2(S.toCharArray(), 0, res);
        return res;
    }

    // 解法2：直接操作当前index的字符，遇到字母时invoke两个递归分支，否则invoke一个（call stack会自动回溯，比较简单）
    public void helper2(char[] chs, int pos, List<String> res) {
        if (pos == chs.length) {
            res.add(new String(chs));
            return;
        }
        if (chs[pos] >= '0' && chs[pos] <= '9') {
            helper2(chs, pos + 1, res);
            return;
        }

        chs[pos] = Character.toLowerCase(chs[pos]);
        helper2(chs, pos + 1, res);

        chs[pos] = Character.toUpperCase(chs[pos]);
        helper2(chs, pos + 1, res);
    }

    // 解法1：用典型的backtracking + string builder写法（需要手动清StringBuilder，比较麻烦）
    private void helper(char[] chs, int pos, List<String> res, StringBuilder sb) {

        for (int i = pos; i < chs.length; i++) {
            char ch = chs[i];

            if (!Character.isDigit(ch)) {
                if(Character.isUpperCase(ch)) {
                    sb.append(Character.toLowerCase(ch));
                }
                else {
                    sb.append(Character.toUpperCase(ch));
                }

                helper(chs, i + 1, res, sb);
                int count = 0;
                while(count < chs.length - i) { // 注意要revert之后的所有char
                    sb.deleteCharAt(sb.length()-1);
                    count++;
                }
                sb.append(ch);
            }
            else {
                sb.append(ch);
            }

        }

        res.add(sb.toString());

    }
}
