package datastruct;

import java.util.*;

/**
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.

 Example:
 Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

 Input: word1 = “coding”, word2 = “practice”
 Output: 3
 Input: word1 = "makes", word2 = "coding"
 Output: 1
 Note:
 You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 */

// 用Map<String, List<Integer>> 来记录每个String所有出现的index，
// 比较两个string的距离时，分别拿出两个list，用two pointer来遍历，更新min distance

public class ShortestWordDistII244 {
    Map<String, List<Integer>> idxMap;
    public ShortestWordDistII244(String[] words) {
        idxMap = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if(idxMap.containsKey(word)) {
                idxMap.get(word).add(i);
            }
            else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                idxMap.put(word, list);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> list1 = idxMap.get(word1);
        List<Integer> list2 = idxMap.get(word2);
        int i = 0, j = 0;
        int minDist = Integer.MAX_VALUE;
        while(i < list1.size() && j < list2.size()) {

            // 每次移动value小的一方的index

            if(list1.get(i) < list2.get(j)) {
                minDist = Math.min(minDist, list2.get(j) - list1.get(i));
                i++;
            }
            else {
                minDist = Math.min(minDist, list1.get(i) - list2.get(j));
                j++;
            }
        }

        return minDist;
    }

}
/**
 * Your WordDistance object will be instantiated and called as such:
 * WordDistance obj = new WordDistance(words);
 * int param_1 = obj.shortest(word1,word2);
 */
