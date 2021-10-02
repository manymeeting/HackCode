package greedy;

/**
 * Given a sorted integer array nums and an integer n, add/patch elements to the
 * array such that any number in the range [1, n] inclusive can be formed by the
 * sum of some elements in the array.
 * 
 * Return the minimum number of patches required.
 * 
 * 
 * 
 * Example 1:
 * 
 * Input: nums = [1,3], n = 6 Output: 1 Explanation: Combinations of nums are
 * [1], [3], [1,3], which form possible sums of: 1, 3, 4. Now if we add/patch 2
 * to nums, the combinations are: [1], [2], [3], [1,3], [2,3], [1,2,3]. Possible
 * sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6]. So we only need
 * 1 patch. Example 2:
 * 
 * Input: nums = [1,5,10], n = 20 Output: 2 Explanation: The two patches can be
 * [2, 4]. Example 3:
 * 
 * Input: nums = [1,2,2], n = 5 Output: 0
 */

// 用greedy策略，从0开始，遍历nums的同时记录当前能实现的sum，如果sum小于下一个数就说明有gap，需要动态的补数进去。
// 比如：如果当前的sum范围是[1,m]，且当前的数字num > m，我们应该打补丁m+1，这样sum范围扩充到[1，2m+1]
public class PatchingArray330 {
    public int minPatches(int[] nums, int n) {
        long patch = 0;
        int count = 0;
        int index = 0;
        // patch的意义：所有sum小于或等于patch的数，都已经被处理过（动态添加或者就已经在数组里）
        while (patch < n) {
            if (index < nums.length && patch + 1 >= nums[index]) {
                patch += nums[index];
                index++;
            } else {
                // 为什么加1，因为这样可以保证最终得到的所有sum没有gap
                patch += (patch + 1);
                count++;
            }
        }

        return count;
    }
}
