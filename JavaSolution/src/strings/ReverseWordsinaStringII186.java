package strings;

/**
 * Given an input string , reverse the string word by word.

 Example:

 Input:  ["t","h","e"," ","s","k","y"," ","i","s"," ","b","l","u","e"]
 Output: ["b","l","u","e"," ","i","s"," ","s","k","y"," ","t","h","e"]
 Note:

 A word is defined as a sequence of non-space characters.
 The input string does not contain leading or trailing spaces.
 The words are always separated by a single space.
 Follow up: Could you do it in-place without allocating extra space?
 */

// 写一个reverse，先整个数组reverse（每个单词内也被reverse），
// 再遍历array，遇到空格则把之前的word内部reverse，
// 最后要把最后一个单词reverse（因为最后无空格）

public class ReverseWordsinaStringII186 {

    public void reverseWords(char[] str) {

        //1. reverse the whole sentence
        reverse(str, 0, str.length - 1);

        //2. reverse each word (except the last)
        int start = 0;
        for (int i = 0; i < str.length; i++)
        {
            if(str[i] == ' ')
            {
                reverse(str, start, i - 1);
                start = i+1;
            }
        }

        //3. reverse the last word
        reverse(str, start, str.length - 1);
    }

    private void reverse(char[] s, int start, int end)
    {

        while(start < end)
        {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }

}
