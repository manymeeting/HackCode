package arrays;

import java.util.HashMap;

/**
 * Given a non-empty array of non-negative integers nums,
 * the degree of this array is defined as the maximum frequency of any one of its elements.

 Your task is to find the smallest possible length of a (contiguous) subarray of nums,
 that has the same degree as nums.

 Example 1:
 Input: [1, 2, 2, 3, 1]
 Output: 2
 Explanation:
 The input array has a degree of 2 because both elements 1 and 2 appear twice.
 Of the subarrays that have the same degree:
 [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 The shortest length is 2. So return 2.
 Example 2:
 Input: [1,2,2,3,1,4,2]
 Output: 6
 Note:

 nums.length will be between 1 and 50,000.
 nums[i] will be an integer between 0 and 49,999.
 */

/**
 * 思路：一遍loop取得所有元素的degree，用{num: [degree, startIdx, endIdx]}的一个map来记录
 * loop结束后遍历map，用Integer.MIN_VALUE 和 Integer.MAX_VALUE 来找最大的degree和最小的length
 * */
public class DegreeOfAnArray697 {

    public int findShortestSubArray(int[] nums) {

        HashMap<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++)
        {
            int num = nums[i];
            if(!map.containsKey(num))
            {
                map.put(num, new int[]{1, i, i});
            }
            else
            {
                int[] temp = map.get(num);
                temp[0]++;
                temp[2] = i;
            }
        }

        int degree = Integer.MIN_VALUE; int res = Integer.MAX_VALUE;
        for(int[] val : map.values())
        {
            if(val[0] > degree)
            {
                degree = val[0];
                res = val[2] - val[1] + 1;
            }
            if(val[0] == degree)
            {
                if(val[2] - val[1] + 1 < res)
                {
                    res = val[2] - val[1] + 1;
                }
            }
        }
        return res;
    }

}
