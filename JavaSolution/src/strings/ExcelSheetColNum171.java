package strings;

/**
 * Given a column title as appear in an Excel sheet, return its corresponding column number.

 For example:

 A -> 1
 B -> 2
 C -> 3
 ...
 Z -> 26
 AA -> 27
 AB -> 28
 ...
 Example 1:

 Input: "A"
 Output: 1
 Example 2:

 Input: "AB"
 Output: 28
 Example 3:

 Input: "ZY"
 Output: 701
 */

// 累计叠加26的次方与char偏差值的乘积即可

public class ExcelSheetColNum171 {
    public int titleToNumber(String s) {
        char[] chs = s.toCharArray();
        int res = 0;
        for (int i = 0; i < chs.length; i++) {
            char ch = chs[i];
            res += Math.pow(26, chs.length - i - 1) * (ch - 'A' + 1);
        }

        return res;
    }
}
