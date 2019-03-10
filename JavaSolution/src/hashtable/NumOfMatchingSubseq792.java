package hashtable;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;

/**
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.

 Example :
 Input:
 S = "abcde"
 words = ["a", "bb", "acd", "ace"]
 Output: 3
 Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 Note:

 All words in words and S will only consists of lowercase letters.
 The length of S will be in the range of [1, 50000].
 The length of words will be in the range of [1, 5000].
 The length of words[i] will be in the range of [1, 50].


 */

// 暴力解法是对每个list中的元素都判断一遍是否为subseq，优化方法是用一个数据结构来记录list中各个string的check状态，
// 维护一个Map<char，以当前char开头的substring的list>，遍历S，以每个当前的char为准在map中"消去"以当前char开头的string，并把剩下的substring加到对于的entry里，
// 当遇到某个entry中只剩下1个char并且和当前char相同，则说明找到了一个subseq。

// 暴力解的复杂度：O(length(S) * length(word) * num_words)，
// 优化后的复杂度：O(length(S) + length(word)*num_words)

public class NumOfMatchingSubseq792 {
    public int numMatchingSubseq(String S, String[] words) {
        Map<Character, ArrayDeque<String>> map = new HashMap<>();
        for (char ch = 'a'; ch <= 'z'; ch++){
            map.putIfAbsent(ch, new ArrayDeque<>());
        }

        for (String w : words) {
            map.get(w.charAt(0)).add(w);
        }

        int count = 0;

        for (char ch : S.toCharArray()) {
            ArrayDeque<String> candidates = map.get(ch);
            int size = candidates.size();
            for (int i = 0; i < size; i++) {
                String candidate = candidates.removeFirst();
                if(candidate.length() == 1) {
                    count++;
                }
                else {
                    map.get(candidate.charAt(1)).add(candidate.substring(1));
                }
            }
        }

        return count;
    }
}
