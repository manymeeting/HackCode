package stack;

import java.util.*;

/** 
 * 
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.
 

Example 1:

Input: s = "bcabc"
Output: "abc"
Example 2:

Input: s = "cbacdcbc"
Output: "acdb"


思路：看着简单实际上比较难，需要看出关键的两个基本约束条件：
1. 原string中出现的每个char都要包含在最终的string里，
2. 最终的string里每个char只能出现一次。


为了达到上述要求，需要一个类似于greedy的思路，借助于stack(deque)来完成
1. 小的char要尽可能的在前。（每次对比stack顶部char和当前char的大小）
2. 每次处理一个char的时候，确保已遍历过的序列中没有更优解（greedy）。
3. 在抛弃一个char的时候一定要确保该char最终还是能出现在结果里（用lastOccurIndex）

 */
public class RemoveDuplicateLetters316 {
    
    public String removeDuplicateLetters(String s) {

        Map<Character, Integer> lastOccurIndex = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            lastOccurIndex.put(s.charAt(i), i);
        }

        Deque<Character> deque = new ArrayDeque<>();
        Set<Character> seen = new HashSet<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            if (!seen.contains(ch)) {
                while(!deque.isEmpty() && ch < deque.peek() && lastOccurIndex.get(deque.peek()) > i) {
                    seen.remove(deque.removeFirst());
                }
                deque.addFirst(ch);
                seen.add(ch);
            }
            
        }

        StringBuilder sb = new StringBuilder();
        while(!deque.isEmpty()) {
            sb.append(deque.removeLast());
        }
        return sb.toString();
    }
        
}
