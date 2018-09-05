package strings;

import java.util.ArrayList;
import java.util.List;

/**
 * In a string S of lowercase letters, these letters form consecutive groups of the same character.

 For example, a string like S = "abbxxxxzyy" has the groups "a", "bb", "xxxx", "z" and "yy".

 Call a group large if it has 3 or more characters.
 We would like the starting and ending positions of every large group.

 The final answer should be in lexicographic order.



 Example 1:

 Input: "abbxxxxzzy"
 Output: [[3,6]]
 Explanation: "xxxx" is the single large group with starting  3 and ending positions 6.
 Example 2:

 Input: "abc"
 Output: []
 Explanation: We have "a","b" and "c" but no large group.
 Example 3:

 Input: "abcdddeeeeaabbbcd"
 Output: [[3,5],[6,9],[12,14]]

 */

// 记录start和end，在ch发生变化时check，要注意循环结束后还要再check一次，来判断目标字串出现在末尾的情况

public class PositionsofLargeGroups830 {
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> res = new ArrayList<>();

        char currCh = S.charAt(0);
        int start = 0;
        int end = 0;
        for (int i = 0; i < S.length(); i++)
        {
            if(currCh == S.charAt(i))
            {
                end = i;
            }
            else
            {
                if(end - start >= 3)
                {
                    List<Integer> bounds = new ArrayList<>();
                    bounds.add(start);
                    bounds.add(end);
                    res.add(bounds);
                }

                currCh = S.charAt(i);
                start = i;
                end = i;
            }
        }

        if(end - start >= 3)
        {
            List<Integer> bounds = new ArrayList<>();
            bounds.add(start);
            bounds.add(end);
            res.add(bounds);
        }
        return res;
    }
}
