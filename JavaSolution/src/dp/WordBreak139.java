package dp;

import java.util.List;

/**
 *
 Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input: s = "leetcode", wordDict = ["leet", "code"]
 Output: true
 Explanation: Return true because "leetcode" can be segmented as "leet code".
 Example 2:

 Input: s = "applepenapple", wordDict = ["apple", "pen"]
 Output: true
 Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
 Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output: false
 */

// DP解法，一个boolean数组canBreak，第i位代表到s的i-1位能不能被break，
// 对每个i，从左端开始移动j到i，看substring在不在dict里，在的话则设置i位为true，继续移动i
    
public class WordBreak139 {
    public boolean wordBreak(String s, List<String> wordDict) {

        // 第i位代表到s的i-1位能不能被break
        boolean[] canBreak = new boolean[s.length()+1];
        canBreak[0] = true;

        for (int i = 0; i < s.length() + 1; i++) {
            for (int j = 0; j < i; j++) {
                if (canBreak[j] && wordDict.contains(s.substring(j, i))) {
                    canBreak[i] = true;
                    break;
                }
            }
        }

        return canBreak[s.length()];

    }
}
