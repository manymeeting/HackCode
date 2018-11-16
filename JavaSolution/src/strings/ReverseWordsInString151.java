package strings;

/**
 * Given an input string, reverse the string word by word.

 Example:

 Input: "the sky is blue",
 Output: "blue is sky the".
 Note:

 A word is defined as a sequence of non-space characters.
 Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
 You need to reduce multiple spaces between two words to a single space in the reversed string.
 Follow up: For C programmers, try to solve it in-place in O(1) space.
 */

// 避免使用lib函数，单纯用2pointer来解决。先reverse整个str，然后单独reverse每个单词，最后压缩空格，然后返回结果
// 注意while+2pointer循环在各个函数中的使用
public class ReverseWordsInString151 {
    public String reverseWords(String s) {

        if(s == null) return "";
        char[] chars = s.toCharArray();

        reverse(chars, 0, s.length()-1);

        reverseWords(chars);

        return cleanSpace(chars);
    }

    private void reverseWords(char[] chars) {
        int len = chars.length;
        int i = 0, j = 0;
        while(i < len) {
            while(i < j || i < len && chars[i] == ' ') i++;
            while(j < i || j < len && chars[j] != ' ') j++;
            reverse(chars, i, j - 1);
        }
    }

    private String cleanSpace(char[] chars) {
        int len = chars.length;
        int i = 0, j = 0;
        while(j < len) {
            while(j < len && chars[j] == ' ') j++; // skip spaces
            while(j < len && chars[j] != ' ') chars[i++] = chars[j++]; // shift non-space characters
            while(j < len && chars[j] == ' ') j++; // skip spaces
            if(j < len) chars[i++] = ' '; // add one space after each word
        }
        // return the final string
        return new String(chars).substring(0, i);

    }

    private void reverse(char[] chars, int i, int j) {
        while (i < j) {
            char temp = chars[i];
            chars[i] = chars[j];
            chars[j] = temp;
            i++;
            j--;
        }
    }
}
