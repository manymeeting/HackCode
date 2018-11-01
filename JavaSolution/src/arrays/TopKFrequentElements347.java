package arrays;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *Given a non-empty array of integers, return the k most frequent elements.

 Example 1:

 Input: nums = [1,1,1,2,2,3], k = 2
 Output: [1,2]
 Example 2:

 Input: nums = [1], k = 1
 Output: [1]
 Note:

 You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 * */

// 利用桶排序的思想，先build一个出现频率的map，然后建立一个list的array（buckets），每一项代表对应频率下的数字list，
// 最后从尾部开始遍历buckets，往结果里放元素，直到个数>=k为止

public class TopKFrequentElements347 {
    public List<Integer> topKFrequent(int[] nums, int k) {

        // 1. build freq map
        HashMap<Integer, Integer> freq = new HashMap<>();
        for (int num : nums)
        {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // 2. build buckets on freq
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < nums.length + 1; i++) buckets.add(new ArrayList<>());
        for (int key: freq.keySet())
        {
            buckets.get(freq.get(key)).add(key);
        }

        // 3. gather results
        List<Integer> res = new ArrayList<>();
        for (int i = buckets.size()-1; i >= 0; i--)
        {
            res.addAll(buckets.get(i));
            if(res.size() >= k) return res;
        }

        return res;
    }

}
