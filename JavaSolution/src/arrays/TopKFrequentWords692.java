package arrays;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.

 Your answer should be sorted by frequency from highest to lowest.
 If two words have the same frequency, then the word with the lower alphabetical order comes first.

 Example 1:
 Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 Output: ["i", "love"]
 Explanation: "i" and "love" are the two most frequent words.
 Note that "i" comes before "love" due to a lower alphabetical order.
 Example 2:
 Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 Output: ["the", "is", "sunny", "day"]
 Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 with the number of occurrence being 4, 3, 2 and 1 respectively.
 Note:
 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Input words contain only lowercase letters.
 Follow up:
 Try to solve it in O(n log k) time and O(n) extra space.

 */

// 先构造freq map，再用PriorityQueue来排列map里的entry，保持queue大小为k，最后从queue里拿出元素放到res里即可，
// 注意PriorityQueue在new时的定义（lamba）

public class TopKFrequentWords692 {
    public List<String> topKFrequent(String[] words, int k) {
        HashMap<String, Integer> freq = new HashMap<>();
        for (String word : words)
        {
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<Map.Entry<String, Integer>> queue = new PriorityQueue<>(
                (a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue()
        );

        for (Map.Entry<String, Integer> entry : freq.entrySet())
        {
            queue.offer(entry);
            if(queue.size() > k) queue.poll();
        }

        // prepare result
        LinkedList<String> res = new LinkedList<>();
        while(!queue.isEmpty())
        {
            res.addFirst(queue.poll().getKey());
        }

        return res;
    }
}
