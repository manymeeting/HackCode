package twopointer;

import java.util.*;

/**
Find the length of the longest substring T of a given string (consists of lowercase letters only) such that every character in T appears no less than k times.

Example 1:

Input:
s = "aaabb", k = 3

Output:
3

The longest substring is "aaa", as 'a' is repeated 3 times.
Example 2:

Input:
s = "ababbc", k = 2

Output:
5

The longest substring is "ababb", as 'a' is repeated 2 times and 'b' is repeated 3 times.
*/

// Sliding window的O(N)解法：
// 思路出发点：这里repeat不要求连续，所以如果不加额外的限制条件就无法判断什么时候需要移动window的左边界，
// 由于str里全是字母，可以从1-26来指定当前window中允许出现的unique字母的数量，从而控制window移动，同时确保cover所有的可能性
// 固定26次loop，复杂度为N

class LgstSubstrAtLeastKRptCh395 {
	public int longestSubstring(String s, int k) {
        int max = 0;
        int[] freqMap = new int[26];


        for (int u = 1; u <= 26; u++) {
        	Arrays.fill(freqMap, 0); // 每次指定的unique字母数量变化时，清空freqmap和所有状态变量
        	int i = 0; int j = 0;
        	int freqGtKCnt = 0; // 当前有多少个unique letter的出现次数大于等于k
        	int currUniqueCnt = 0;
        	while(j < s.length()) {
        		if(currUniqueCnt <= u) {
        			char chR = s.charAt(j);
        			int idx = (int) (chR - 'a');
        			freqMap[idx]++;

        			if(freqMap[idx] == 1) {
        				currUniqueCnt++;
        			}
        			if(freqMap[idx] == k) {
        				freqGtKCnt++;
        			}

        			j++;
        		}
        		else {
        			char chL = s.charAt(i);
        			int idx = (int) (chL - 'a');
        			freqMap[idx]--;
        			if(freqMap[idx] == 0) {
        				currUniqueCnt--;
        			}
        			if(freqMap[idx] == k-1) {
        				freqGtKCnt--;
        			}

        			i++;

        		}


        		if(currUniqueCnt == freqGtKCnt) { // All unique letters has freq >= k
        			max = Math.max(max, j - i);
        		}
        	}
        }

        return max;
    }
}