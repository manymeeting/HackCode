package strings;


import java.util.HashSet;
import java.util.Set;

/**Given a string, determine if a permutation of the string could form a palindrome.

 Example 1:

 Input: "code"
 Output: false
 Example 2:

 Input: "aab"
 Output: true
 Example 3:

 Input: "carerac"
 Output: true
 */

//不能用int[26]因为case里有非字母字符，用set来解决，每次判断是否有重复，有则删除，无则add，最后看size
public class PalindromePermutation266 {

    public boolean canPermutePalindrome(String s) {

        Set<Character> set = new HashSet<>();
        for(char c : s.toCharArray())
        {
            if(set.contains(c))
            {
                set.remove(c);
            }
            else
            {
                set.add(c);
            }

        }
        if(set.size() <= 1)
        {
            return true;
        }
        return false;
    }
}
