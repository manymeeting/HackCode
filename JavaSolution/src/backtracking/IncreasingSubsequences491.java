package backtracking;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Given an integer array, your task is to find all the different possible increasing subsequences of the given array, and the length of an increasing subsequence should be at least 2 .

 Example:
 Input: [4, 6, 7, 7]
 Output: [[4, 6], [4, 7], [4, 6, 7], [4, 6, 7, 7], [6, 7], [6, 7, 7], [7,7], [4,7,7]]
 Note:
 The length of the given array will not exceed 15.
 The range of integer in the given array is [-100,100].
 The given array may contain duplicates, and two equal integers should also be considered as a special case of increasing sequence.

 */

// 常规backtracking，注意几个细节，
// 1.tempList.get(tempList.size()-1)之前一定要检查list是否为空，
// 2.每次递归的start是i+1而不是start+1

public class IncreasingSubsequences491 {
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        allSubsequences(res, nums, new ArrayList<>(), 0);
        return res;

    }

    private void allSubsequences(List<List<Integer>> res, int[] nums, List<Integer> tempList, int start)
    {
        if(tempList.size() >= 2)
        {
            res.add(new ArrayList<>(tempList));
        }
        Set<Integer> used = new HashSet<>();
        for (int i = start; i < nums.length; i++)
        {
            if(used.contains(nums[i])) continue;
            if(tempList.size() == 0 || nums[i] >= tempList.get(tempList.size()-1))
            {
                used.add(nums[i]);
                tempList.add(nums[i]);
                allSubsequences(res, nums, tempList, i+1);
                tempList.remove(tempList.size()-1);
            }

        }

    }

}
