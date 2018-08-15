package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of candidate numbers (candidates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.

 Each number in candidates may only be used once in the combination.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.

 Example 1:
 Input: candidates = [10,1,2,7,6,1,5], target = 8,
 A solution set is:
 [
 [1, 7],
 [1, 2, 5],
 [2, 6],
 [1, 1, 6]
 ]

 Example 2:
 Input: candidates = [2,5,2,1,2], target = 5,
 A solution set is:
 [
 [1,2,2],
 [5]
 ]
 */
public class CombinationSumII40 {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backTracking(res, candidates, new ArrayList<>(), target, 0);
        return res;
    }


    private void backTracking(List<List<Integer>> res, int[] candidates, List<Integer> tempList, int less, int start)
    {
        if(less < 0)
        {
            return;
        }
        if(less == 0)
        {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = start; i < candidates.length; i++)
        {
            int candi = candidates[i];
            // 避免由于candidates中的重复元素导致的结果集重复（比如 [1, 1, 2, 5, 6, 7, 10] sum=8，不判断的话会出现两个125和17）
            if(i > start && candi == candidates[i-1])
            {
                continue;
            }
            tempList.add(candi);
            backTracking(res, candidates, tempList, less - candi, i+1);
            tempList.remove(tempList.size()-1);

        }

    }


}
