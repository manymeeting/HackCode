package strings;

/**
 * Given a non-empty string s, you may delete at most one character.
 * Judge whether you can make it a palindrome.

 Example 1:
 Input: "aba"
 Output: True
 Example 2:
 Input: "abca"
 Output: True
 Explanation: You could delete the character 'c'.
 Note:
 The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

// 用两个pointer，ch相等一个++一个--，最后比较两个pointer，
// 如果不是i>j就再check一下以i+1,j开始，和i,j-1开始是不是palindrome即可

public class ValidPalindromeII680 {
    public boolean validPalindrome(String s) {
        int i = 0; int j = s.length()-1;
        while(i < j && s.charAt(i) == s.charAt(j))
        {
            i++; j--;
        }

        if( i >=j ) return true;

        if(isPalindrome(s, i+1, j) || isPalindrome(s, i, j-1))
        {
            return true;
        }

        return false;
    }

    private boolean isPalindrome(String s, int i, int j)
    {

        while (i < j)
        {
            if(s.charAt(i) == s.charAt(j))
            {
                i++; j--;
            }
            else
            {
                return false;
            }

        }

        return true;
    }

}
