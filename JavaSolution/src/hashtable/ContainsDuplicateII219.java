package hashtable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

 Example 1:

 Input: nums = [1,2,3,1], k = 3
 Output: true
 Example 2:

 Input: nums = [1,0,1,1], k = 1
 Output: true
 Example 3:

 Input: nums = [1,2,3,1,2,3], k = 2
 Output: false
 */

// 记录每个数的index的list，遇到重复的计算abs距离

public class ContainsDuplicateII219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if(!map.containsKey(nums[i])) {
                map.put(nums[i], new ArrayList<>());
                map.get(nums[i]).add(i);
            }
            else {
                List<Integer> idxList = map.get(nums[i]);

                if(Math.abs(i - idxList.get(idxList.size()-1)) <= k) {
                    return true;
                }
                idxList.add(i);
            }
        }

        return false;
    }
}
