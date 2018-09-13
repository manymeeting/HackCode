package others;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

 Only one letter can be changed at a time.
 Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:

 Return 0 if there is no such transformation sequence.
 All words have the same length.
 All words contain only lowercase alphabetic characters.
 You may assume no duplicates in the word list.
 You may assume beginWord and endWord are non-empty and are not the same.
 Example 1:

 Input:
 beginWord = "hit",
 endWord = "cog",
 wordList = ["hot","dot","dog","lot","log","cog"]

 Output: 5

 Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
 return its length 5.
 Example 2:

 Input:
 beginWord = "hit"
 endWord = "cog"
 wordList = ["hot","dot","dog","lot","log"]

 Output: 0

 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

 */

// 维护两个set，一个reached，一个是原来的wordList转换成的set，先给reached里放beginWord，
// 每次从reached里拿word出来，遍历替换每个char，看是否跟wordset里的某个match，
// match则再判断是否==endWord，等于则结束，不等则放入下一轮的reached里

public class WordLadder127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> reached = new HashSet<>();

        reached.add(beginWord);
        wordSet.remove(beginWord);

        int step = 1;
        while(!reached.isEmpty())
        {
            Set<String> nextReachedSet = new HashSet<>();
            for (String str: reached)
            {
                for (int i = 0; i < str.length(); i++)
                {
                    char[] chs = str.toCharArray();
                    for (char c = 'a'; c < 'z'; c++)
                    {
                        chs[i] = c;
                        String newStr = new String(chs);
                        if(wordSet.remove(newStr))
                        {
                            if(endWord.equals(newStr)) return step+1;
                            nextReachedSet.add(newStr);
                        }
                    }
                }

            }
            reached = nextReachedSet;
            step++;
        }
        return 0;
    }
}
