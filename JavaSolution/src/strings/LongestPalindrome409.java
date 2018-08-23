package strings;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string which consists of lowercase or uppercase letters,
 * find the length of the longest palindromes that can be built with those letters.

 This is case sensitive, for example "Aa" is not considered a palindrome here.

 Note:
 Assume the length of given string will not exceed 1,010.

 Example:

 Input:
 "abccccdd"

 Output:
 7

 Explanation:
 One longest palindrome that can be built is "dccaccd", whose length is 7.
 */

// 用set，每次遇到已有ch就remove然后count++，否则add当前ch，最后count size==0说明count*2为最大长度，size > 0说明count*2 + 1 为最大长度
public class LongestPalindrome409 {

    public int longestPalindrome(String s) {

        Set<Character> set = new HashSet<>();
        int count = 0;
        for(char ch : s.toCharArray())
        {
            if(set.contains(ch))
            {
                count++;
                set.remove(ch);
            }
            else
            {
                set.add(ch);
            }
        }

        if(set.size() == 0)
        {
            return count * 2;
        }
        return count * 2 + 1;
    }

}
