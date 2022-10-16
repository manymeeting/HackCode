package datastruct.treeset;

import java.util.*;


/** 
 * 
 * Your country has an infinite number of lakes. Initially, all the lakes are empty, but when it rains over the nth lake, the nth lake becomes full of water. If it rains over a lake that is full of water, there will be a flood. Your goal is to avoid floods in any lake.

Given an integer array rains where:

rains[i] > 0 means there will be rains over the rains[i] lake.
rains[i] == 0 means there are no rains this day and you can choose one lake this day and dry it.
Return an array ans where:

ans.length == rains.length
ans[i] == -1 if rains[i] > 0.
ans[i] is the lake you choose to dry in the ith day if rains[i] == 0.
If there are multiple valid answers return any of them. If it is impossible to avoid flood return an empty array.

Notice that if you chose to dry a full lake, it becomes empty, but if you chose to dry an empty lake, nothing changes.

 

Example 1:

Input: rains = [1,2,3,4]
Output: [-1,-1,-1,-1]
Explanation: After the first day full lakes are [1]
After the second day full lakes are [1,2]
After the third day full lakes are [1,2,3]
After the fourth day full lakes are [1,2,3,4]
There's no day to dry any lake and there is no flood in any lake.

Example 3:

Input: rains = [1,2,0,1,2]
Output: []

[1 2 0 1 3]
find the 0s
use 0 to remove numbers to the left, then find if there are duplicates?
-1 -1 -1 -1 -1

1st 0: 1, 2
2nd 0: 1, 2

[1 2 0 1 3 0 2 0 0]
to dry [1 2]
to dry [1 3]
to dry [2]
we don't make the decision when we see 0, instead, we make a decision when see rains[i] > 0 and check if we have enough dry days.

[1 2 0 4 1 0 2 0 0]


Input
[1,0,2,3,0,1,2]
Output
[-1,2,-1,-1,1,-1,-1]
Expected
[-1,1,-1,-1,2,-1,-1]

firstSeenIndex<> 1->0, 2->2
dry day lndex [1, 4]

1 <= rains.length <= 105
0 <= rains[i] <= 109

思路：遍历一遍，每次遇到重复的lake就尝试找一下有没有dry day可以用。难点在于要考虑dry day和对应的lake出现的先后顺序。可以用TreeSet的ceiling来优化这个查找。
 */
public class AvoidFloodInTheCity1488 {

    // 解法2：用Treeset来优化查找比一个index大的最小dry day。复杂度nlog(n)
    public int[] avoidFlood2(int[] rains) {
        int[] res = new int[rains.length];
        Arrays.fill(res, -1);

        Map<Integer, Integer> fullLakeToIndex = new HashMap<>();
        TreeSet<Integer> dryDayIndexes = new TreeSet<>();

        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain > 0) {
                // Check if we need to choose a lake to dry
                if (!dryDayIndexes.isEmpty() && fullLakeToIndex.containsKey(rain)) {
                    // Try to dry this lake ahead
                    Integer toDryIndex = dryDayIndexes.ceiling(fullLakeToIndex.get(rain));

                    if (toDryIndex != null) {
                        res[toDryIndex] = rain;
                        dryDayIndexes.remove(toDryIndex);
                        fullLakeToIndex.remove(rain);
                    }
                    
                }
                // Will flood.
                if (fullLakeToIndex.containsKey(rain)) {
                    return new int[0];
                }

                fullLakeToIndex.put(rain, i);
                
            }
            if (rain == 0) {
                dryDayIndexes.add(i); 
            }
        }
        
        // As required to pass the tests
        for (int i : dryDayIndexes) {
             // Too many dry days, just dry 1st lake by default.
            res[i] = 1;
        }
        return res;
    }
    
    // 解法1：用hashmap和list来做，复杂度n^2
    public int[] avoidFlood(int[] rains) {
        
        int[] res = new int[rains.length];
        Arrays.fill(res, -1);

        Map<Integer, Integer> fullLakeToIndex = new HashMap<>();
        List<Integer> dryDayIndexes = new ArrayList<>();

        for (int i = 0; i < rains.length; i++) {
            int rain = rains[i];
            if (rain > 0) {
                // Check if we need to choose a lake to dry
                if (dryDayIndexes.size() > 0 && fullLakeToIndex.containsKey(rain)) {
                    // Try to dry this lake ahead
                    
                    int j = 0;
                    for(; j < dryDayIndexes.size(); j++) {
                        if (dryDayIndexes.get(j) > fullLakeToIndex.get(rain)) {
                            break;
                        }
                    }
                    if (j < dryDayIndexes.size()) {
                        res[dryDayIndexes.remove(j)] = rain;
                        fullLakeToIndex.remove(rain);
                    }
                    
                }
                // Will flood.
                if (fullLakeToIndex.containsKey(rain)) {
                    return new int[0];
                }

                fullLakeToIndex.put(rain, i);
                
            }
            if (rain == 0) {
                dryDayIndexes.add(i); 
            }
        }
        
        // As required to pass the tests
        while(!dryDayIndexes.isEmpty()) {
            // Too many dry days, just dry 1st lake by default.
            res[dryDayIndexes.remove(0)] = 1;
        }

        return res;
    }

    
}