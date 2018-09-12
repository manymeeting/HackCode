package strings;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string S, check if the letters can be rearranged so that two characters that are adjacent to each other are not the same.

 If possible, output any possible result.  If not possible, return the empty string.

 Example 1:

 Input: S = "aab"
 Output: "aba"
 Example 2:

 Input: S = "aaab"
 Output: ""
 Note:

 S will consist of lowercase letters and have length in range [1, 500].

 */

// 用PriorityQueue，每次拿第一个元素，如果跟当前sb的最后一个相等，就再拿第二个，
// append之后count--，依然大于0时重新放到queue里
// 注意check impossible的条件 count > (S.length() + 1) / 2
// 另外一个trick是用PriorityQueue的类型设计为int[]，第一个存char的int值，第二个存cnt，采用其他结构则处理比较复杂

public class ReorganizeString767 {
    public String reorganizeString(String S) {

        // create map for char count
        Map<Character, Integer> map = new HashMap<>();
        for(char c : S.toCharArray())
        {
            int count = map.getOrDefault(c, 0) + 1;

            // check if it's impossible to get a solution
            if(count > (S.length() + 1) / 2) return "";
            map.put(c, count);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (char c: map.keySet())
        {
            pq.add(new int[]{c, map.get(c)});
        }

        // build the result
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty())
        {
            int[] selected = pq.poll();
            if(sb.length() == 0 || sb.charAt(sb.length()-1) != selected[0])
            {
                sb.append((char)selected[0]);
                if(--selected[1] > 0)
                {
                    pq.add(selected);
                }
            }
            else
            {
                // poll a second one to make it different
                int[] second = pq.poll();
                sb.append((char) second[0]);
                if(--second[1] > 0)
                {
                    pq.add(second);
                }
                pq.add(selected); // add the first one back
            }
        }

        return sb.toString();

    }
}
