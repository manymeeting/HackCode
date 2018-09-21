package strings;

/**
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

 If the last word does not exist, return 0.

 Note: A word is defined as a character sequence consists of non-space characters only.

 Example:

 Input: "Hello World"
 Output: 5
 */

// 注意全是空格的case， if (words.length == 0) return 0;

public class LengthOfLastWord58 {
    public int lengthOfLastWord(String s) {
        if(s.length() == 0) return 0;
        String[] words = s.split(" ");
        if (words.length == 0) return 0;
        return words[words.length-1].length();
    }
}
