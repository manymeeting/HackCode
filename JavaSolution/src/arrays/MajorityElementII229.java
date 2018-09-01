package arrays;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times.

 Note: The algorithm should run in linear time and in O(1) space.

 Example 1:

 Input: [3,2,3]
 Output: [3]
 Example 2:

 Input: [1,1,1,3,3,2,2,2]
 Output: [1,2]

 */

// 用count方式（遇到与任一candi相同则count++，都不同则count--），选出两个出现次数最多的，
// 然后再check是否满足出现次数 > n/3 的条件，
// 选两个是因为在满足 > n/3条件下最多只可能有两个数，
// 要注意最后要check是否两个candi相同


public class MajorityElementII229 {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();

        int candi1 = 0, candi2 = 0;
        int count1 = 0, count2 = 0;
        for (int i = 0; i < nums.length; i++)
        {
            int num = nums[i];
            if (candi1 == num)
            {
                count1++;
            }
            else if(candi2 == num)
            {
                count2++;
            }
            else if(count1 == 0)
            {
                candi1 = num;
                count1 = 1;
            }
            else if(count2 == 0)
            {
                candi2 = num;
                count2 = 1;
            }
            else
            {
                count1--;
                count2--;
            }
        }

        if(_count(nums, candi1) > nums.length / 3) res.add(candi1);
        if(candi2!=candi1 && _count(nums, candi2)  > nums.length / 3) res.add(candi2);
        return res;
    }

    private int _count(int[] nums, int target)
    {
        int count = 0;
        for (int i : nums)
        {
            if(i == target) count++;
        }
        return count;
    }

}
