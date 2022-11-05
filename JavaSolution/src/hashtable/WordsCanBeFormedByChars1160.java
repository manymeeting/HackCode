package hashtable;

import java.util.Arrays;

/**
 * 
You are given an array of strings words and a string chars.

A string is good if it can be formed by characters from chars (each character can only be used once).

Return the sum of lengths of all good strings in words.

 

Example 1:

Input: words = ["cat","bt","hat","tree"], chars = "atach"
Output: 6
Explanation: The strings that can be formed are "cat" and "hat" so the answer is 3 + 3 = 6.
Example 2:

Input: words = ["hello","world","leetcode"], chars = "welldonehoneyr"
Output: 10
Explanation: The strings that can be formed are "hello" and "world" so the answer is 5 + 5 = 10.
 

Constraints:

1 <= words.length <= 1000
1 <= words[i].length, chars.length <= 100
words[i] and chars consist of lowercase English letters.

思路：比较简单，用一个array记录可用char的freq，对每个str来判断freq是否够用。
 */
public class WordsCanBeFormedByChars1160 {
    
    public int countCharacters(String[] words, String chars) {
        
        int res = 0;
        int[] availableCharFreq = new int[26];
        for (char ch : chars.toCharArray()) {
            availableCharFreq[ch - 'a'] ++;
        }

        for (String w : words) {
            int[] cpFreq = Arrays.copyOf(availableCharFreq, 26);
            if (isGoodStr(w, cpFreq)) {
                res += w.length();
            }
        }        

        return res;
    }

    private boolean isGoodStr(String w, int[] freq) {
        for (int i = 0; i < w.length(); i++) {
            char ch = w.charAt(i);
            freq[ch - 'a']--;
            if (freq[ch - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
