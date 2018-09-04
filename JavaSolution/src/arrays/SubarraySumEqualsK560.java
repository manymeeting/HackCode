package arrays;


import java.util.HashMap;

/**
 * Given an array of integers and an integer k, you need to find the total number of continuous subarrays
 * whose sum equals to k.

 Example 1:
 Input:nums = [1,1,1], k = 2
 Output: 2
 Note:
 The length of the array is in range [1, 20,000].
 The range of numbers in the array is [-1000, 1000] and the range of the integer k is [-1e7, 1e7].
 */

// 采用sumMap来记录每个sum和出现的次数，遍历一遍不断更新sum，同时res+=map.get(sum - k)，
// 注意要放一个初始值put(0, 1)，否则结果会少1

public class SubarraySumEqualsK560 {
    public int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> sumMap = new HashMap<>();

        int res = 0;
        int sum = 0;

        sumMap.put(0, 1); // 初始值，相当于当sum=k时，subarray数量初始为1
        for (int num: nums)
        {
            sum += num;
            if(sumMap.containsKey(sum - k))
            {
                res += sumMap.get(sum - k);
            }

            //不直接put（sum，1） 因为考虑到arr中可能有负数，导致一个sum多次出现
            sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}
