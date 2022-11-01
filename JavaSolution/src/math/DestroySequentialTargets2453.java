package math;

import java.util.*;

/** 
 * 
 * You are given a 0-indexed array nums consisting of positive integers, representing targets on a number line. You are also given an integer space.

You have a machine which can destroy targets. Seeding the machine with some nums[i] allows it to destroy all targets with values that can be represented as nums[i] + c * space, where c is any non-negative integer. You want to destroy the maximum number of targets in nums.

Return the minimum value of nums[i] you can seed the machine with to destroy the maximum number of targets.

 

Example 1:

Input: nums = [3,7,8,1,1,5], space = 2
Output: 1
Explanation: If we seed the machine with nums[3], then we destroy all targets equal to 1,3,5,7,9,... 
In this case, we would destroy 5 total targets (all except for nums[2]). 
It is impossible to destroy more than 5 targets, so we return nums[3].

Constraints:

1 <= nums.length <= 10^5
1 <= nums[i] <= 10^9
1 <= space <= 10^9


思路：转化问题，可以想到跟mod有关，关键要看出所有具有相同num % space的值都可以归为一组被destroy。用一个map统计能被cover的num的个数，然后找最大count那个组里对应的最小num即可。
时间和空间复杂度都是N。
 */
public class DestroySequentialTargets2453 {
    public int destroyTargets(int[] nums, int space) {
        
        Map<Integer, Integer> modValueToCount = new HashMap<>();

        for (int num : nums) {
            int key = num % space;
            modValueToCount.put(key, modValueToCount.getOrDefault(key, 0) + 1);
        }        

        int maxCount = Collections.max(modValueToCount.values());

        int res = Integer.MAX_VALUE;
        for (int num : nums) {
            if (maxCount == modValueToCount.get(num % space)) {
                res = Math.min(res, num);
            }
        }
        return res;
    }
}
