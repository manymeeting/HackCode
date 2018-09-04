package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a set of distinct integers, nums, return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: nums = [1,2,3]
 Output:
 [
 [3],
 [1],
 [2],
 [1,2,3],
 [1,3],
 [2,3],
 [1,2],
 []
 ]

 */

// backtracking, 要记得加空list作为空元素
public class Subsets78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        helper(res, 0, new ArrayList<>(), nums);
        res.add(new ArrayList<>()); // add empty element
        return res;
    }

    private void helper(List<List<Integer>> res, int start, List<Integer> tempList, int[] nums)
    {
        for (int i = start; i < nums.length; i++)
        {
            tempList.add(nums[i]);
            res.add(new ArrayList<>(tempList));
            helper(res, i+1, tempList, nums);
            tempList.remove(tempList.size()-1);

        }

    }

}
