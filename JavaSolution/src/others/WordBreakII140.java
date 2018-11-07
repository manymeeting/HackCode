package others;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.

 Note:

 The same word in the dictionary may be reused multiple times in the segmentation.
 You may assume the dictionary does not contain duplicate words.
 Example 1:

 Input:
 s = "catsanddog"
 wordDict = ["cat", "cats", "and", "sand", "dog"]
 Output:
 [
 "cats and dog",
 "cat sand dog"
 ]
 Example 2:

 Input:
 s = "pineapplepenapple"
 wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
 Output:
 [
 "pine apple pen apple",
 "pineapple pen apple",
 "pine applepen apple"
 ]
 Explanation: Note that you are allowed to reuse a dictionary word.
 Example 3:

 Input:
 s = "catsandog"
 wordDict = ["cats", "dog", "sand", "and", "cat"]
 Output:
 []*/


// 单纯的dfs会超时，需要用一个map，key是字符串，value是该字符串可以被parse的结果集，
// 每次dfs中，遍历wordDict，看当前str是否可以startWith(word)，
// 可以的话取substring然后递归，把返回值拼成一个句子，加到结果list里

public class WordBreakII140 {
    public List<String> wordBreak(String s, List<String> wordDict) {

        Map<String, List<String>> memory = new HashMap<>();

        return dfs(memory, s, wordDict);
    }

    private List<String> dfs(Map<String, List<String>> memory, String s, List<String> wordDict) {

        if(memory.containsKey(s)) {
            return memory.get(s);
        }

        List<String> res = new ArrayList<>(); // 每次递归内部new一个新的list
        for (String word : wordDict) {
            if(s.startsWith(word)) {
                String next = s.substring(word.length());
                if(next.length() == 0) {
                    // the whole word is in the dict
                    res.add(word);
                }
                else {
                    for (String sub : dfs(memory, next, wordDict)) {
                        res.add(word + " " + sub);
                    }
                }

            }
        }

        memory.put(s, res);
        return res;
    }
}
