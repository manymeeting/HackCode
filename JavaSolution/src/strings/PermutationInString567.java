package strings;


import java.util.Arrays;

/**
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1.
 * In other words, one of the first string's permutations is the substring of the second string.
 Example 1:
 Input:s1 = "ab" s2 = "eidbaooo"
 Output:True
 Explanation: s2 contains one permutation of s1 ("ba").
 Example 2:
 Input:s1= "ab" s2 = "eidboaoo"
 Output: False
 Note:
 The input strings only contain lower case letters.
 The length of both given strings is in range [1, 10,000].

 */

// 题意分析：s2中包含s1的permutation，等价于s2中的一部分字符集包含s1中所有字符集（包括个数）
// 用2 pointer（或者叫sliding window）来在s2中滑动，每次滑动后比较两个字符集，相同时即为match

// 注意典型的index操作：j-len1用来取得左侧的index

public class PermutationInString567 {
    public boolean checkInclusion(String s1, String s2) {

        if(s1.length() > s2.length())
        {
            return false;
        }

        int[] chArr1 = new int[26];
        int[] chArr2 = new int[26];

        int len1 = s1.length();
        int len2 = s2.length();
        for (int i = 0; i < len1; i++)
        {
            chArr1[s1.charAt(i) - 'a']++;
        }

        for (int j = 0; j < len2; j++)
        {
            chArr2[s2.charAt(j) - 'a']++;
            if(j >= len1)
            {
                chArr2[s2.charAt(j-len1) - 'a']--;
            }
            if(Arrays.equals(chArr1, chArr2))
            {
                return true;
            }
        }

        return false;

    }

}
