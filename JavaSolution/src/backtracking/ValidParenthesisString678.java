package backtracking;

/**
 * Given a string containing only three types of characters: '(', ')' and '*', write a function to check whether this string is valid. We define the validity of a string by these rules:

 Any left parenthesis '(' must have a corresponding right parenthesis ')'.
 Any right parenthesis ')' must have a corresponding left parenthesis '('.
 Left parenthesis '(' must go before the corresponding right parenthesis ')'.
 '*' could be treated as a single right parenthesis ')' or a single left parenthesis '(' or an empty string.
 An empty string is also valid.
 Example 1:
 Input: "()"
 Output: True
 Example 2:
 Input: "(*)"
 Output: True
 Example 3:
 Input: "(*))"
 Output: True
 Note:
 The string size will be in the range [1, 100].

 */

// 对于没有*的情况来说，维护一个count就能判断出是否valid，因此遇到*就在该处分支进行dfs尝试，一旦一个可能分支valid则判定为valid，
// 需要注意在count减少时直接判断一次是否已经invalid，可以起到加速作用

public class ValidParenthesisString678 {
    public boolean checkValidString(String s) {
        return checkFromPosition(s, 0, 0);
    }


    private boolean checkFromPosition(String s, int pos, int count) {
        if (count < 0) return false;

        for (int i = pos; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (ch == ')') {
                count--;
                if(count < 0) return false; // 如果发现invalid直接return，不再继续loop，起到剪枝加速作用
            }
            if(ch == '(') {
                count++;
            }
            if(ch == '*') {
                return checkFromPosition(s, i+1, count + 1)
                        || checkFromPosition(s, i+1, count)
                        || checkFromPosition(s, i+1, count-1);
            }
        }

        return count == 0;
    }
}
