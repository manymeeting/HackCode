package strings;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

 Strings consists of lowercase English letters only and the length of both strings s and p
 will not be larger than 20,100.

 The order of output does not matter.

 Example 1:

 Input:
 s: "cbaebabacd" p: "abc"

 Output:
 [0, 6]

 Explanation:
 The substring with start index = 0 is "cba", which is an anagram of "abc".
 The substring with start index = 6 is "bac", which is an anagram of "abc".
 Example 2:

 Input:
 s: "abab" p: "ab"

 Output:
 [0, 1, 2]

 Explanation:
 The substring with start index = 0 is "ab", which is an anagram of "ab".
 The substring with start index = 1 is "ba", which is an anagram of "ab".
 The substring with start index = 2 is "ab", which is an anagram of "ab".
 */

// 采用滑动窗口，比较两组字符出现次数的arr即可，类似于567
// 滑动窗口总结：https://leetcode.com/problems/find-all-anagrams-in-a-string/discuss/92007/Sliding-Window-algorithm-template-to-solve-all-the-Leetcode-substring-search-problem.
public class FindAllAnagrams438 {
    public List<Integer> findAnagrams(String s, String p) {

        if(s.length() < p.length())
        {
            return new ArrayList<>();
        }

        int[] chArrP = new int[26];
        int[] chArrS = new int[26];
        List<Integer> res = new ArrayList<>();

        for (int i = 0; i < p.length(); i++)
        {
            chArrP[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++)
        {
            chArrS[s.charAt(i) - 'a']++;
            if(isSameArray(chArrP, chArrS))
            {
                res.add(i+1 - p.length());
            }
            if(i+1 >= p.length())
            {
                chArrS[s.charAt(i+1 - p.length()) - 'a']--;
            }

        }
        return res;
    }

    private boolean isSameArray(int[] arr1, int[] arr2)
    {
        if(arr1.length != arr2.length)
        {
            return false;
        }

        for (int i = 0; i < arr1.length; i++)
        {
            if(arr1[i] != arr2[i])
            {
                return false;
            }
        }

        return true;
    }

}
