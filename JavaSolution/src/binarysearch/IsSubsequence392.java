package binarysearch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a string s and a string t, check if s is subsequence of t.

 You may assume that there is only lower case English letters in both s and t. t is potentially a very long (length ~= 500,000) string, and s is a short string (<=100).

 A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ace" is a subsequence of "abcde" while "aec" is not).

 Example 1:
 s = "abc", t = "ahbgdc"

 Return true.

 Example 2:
 s = "axc", t = "ahbgdc"

 Return false.

 Follow up:
 If there are lots of incoming S, say S1, S2, ... , Sk where k >= 1B, and you want to check one by one to see if T has its subsequence. In this scenario, how would you change your code?
 */

// 解法1：直接遍历t和s，用两个pointer来判断，复杂度为nk, k为t的长度
// 解法2：把t的所有ch和出现的index list存在map里，用二分法判断s中的ch能否在t中找到满足要求的index，复杂度nlogK

// 二分法应用总结：在一个有序列数中找符合某个条件的数（不一定等于序列中的一个数，可以是大小关系）

public class IsSubsequence392 {
    public boolean isSubsequence(String s, String t) {
        Map<Character, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if(!map.containsKey(ch)) {
                map.put(ch, new ArrayList<>());
            }
            map.get(ch).add(i);
        }

        int prev = -1; // prev记录t中目前能满足s到当前ch为止的序列为sub sequence的最左端的index
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(!map.containsKey(ch)) {
                return false;
            }
            List<Integer> idxList = map.get(ch);
            prev = binarySearch(prev, 0, idxList.size(), idxList);
            if(prev == -1) {
                return false;
            }
            prev++;

        }

        return true;

    }

    // 在list中找一个等于或刚刚比idx大的值
    private int binarySearch(int idx, int left, int right, List<Integer> list) {
        if(left < 0 || right > list.size()) {
            return -1;
        }
        while(left < right) {
            int mid = left + (right - left) / 2;
            if(idx > list.get(mid)) {
                left = mid + 1;
            }
            else {
                right = mid;
            }
        }

        return left == list.size() ? -1 : list.get(left);
    }
}
