package backtracking;

import java.util.*;

/**
 * Given a collection of integers that might contain duplicates, nums,
 * return all possible subsets (the power set).

 Note: The solution set must not contain duplicate subsets.

 Example:

 Input: [1,2,2]
 Output:
 [
 [2],
 [1],
 [1,2,2],
 [2,2],
 [1,2],
 []
 ]*/

// 要注意先排序，这样才能有效去重

public class SubsetII90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums); // need sort first to help us check dup later
        helper(res, 0, new LinkedList<>(), nums);
        res.add(new LinkedList<>());
        return res;
    }

    private void helper(List<List<Integer>> res, int start, LinkedList<Integer> tempList, int[] nums)
    {

        Set<Integer> used = new HashSet<>();
        for (int i = start; i < nums.length; i++)
        {
            if(used.add(nums[i]))
            {
                tempList.add(nums[i]);
                res.add(new LinkedList<>(tempList));
                helper(res, i+1, tempList, nums);
                tempList.removeLast();
            }
        }

    }

}
