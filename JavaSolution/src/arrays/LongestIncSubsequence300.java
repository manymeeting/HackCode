package arrays;


import java.util.ArrayList;
import java.util.List;

/**
 * Given an unsorted array of integers, find the length of longest increasing subsequence.

 Example:

 Input: [10,9,2,5,3,7,101,18]
 Output: 4
 Explanation: The longest increasing subsequence is [2,3,7,101], therefore the length is 4.
 Note:

 There may be more than one LIS combination, it is only necessary for you to return the length.
 Your algorithm should run in O(n2) complexity.
 Follow up: Could you improve it to O(n log n) time complexity?
 * */

// 思路：只要求返回目标seq的长度，没必要保证内容一定一致，所以维护一个有序的list，
// 每次大的数字加到末尾，小的数字用二分查找定位index最小且>=该数字的位置，用list.set覆盖对应数字，不影响seq的length

public class LongestIncSubsequence300 {
    public int lengthOfLIS(int[] nums) {
        List<Integer> seq = new ArrayList<>();
        for (int num : nums)
        {
            updateSeq(seq, num);
        }

        return seq.size();

    }

    private void updateSeq(List<Integer> seq, int val)
    {
        if(seq.size() == 0 || val > seq.get(seq.size()-1)) // 注意要check size是否为0，否则会outofbound
        {
            seq.add(val);
        }
        else
        {
            int insertIndex = binarySearchForEqualOrLarge(val, seq);
            seq.set(insertIndex, val);
        }
    }

    private int binarySearchForEqualOrLarge(int target, List<Integer> seq)
    {
        int lo = 0, hi = seq.size()-1;
        while(lo < hi)
        {
            int mid = lo + (hi - lo) / 2;
            if(seq.get(mid) < target)
            {
                lo = mid+1;
            }
            else
            {
                hi = mid;
            }
        }
        return lo;
    }


}
