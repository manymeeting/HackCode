package backtracking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of candidate numbers (candidates) (without duplicates) and a target number (target),
 * find all unique combinations in candidates where the candidate numbers sums to target.

 The same repeated number may be chosen from candidates unlimited number of times.

 Note:

 All numbers (including target) will be positive integers.
 The solution set must not contain duplicate combinations.
 Example 1:

 Input: candidates = [2,3,6,7], target = 7,
 A solution set is:
 [
 [7],
 [2,2,3]
 ]
 Example 2:

 Input: candidates = [2,3,5], target = 8,
 A solution set is:
 [
 [2,2,2,2],
 [2,3,3],
 [3,5]
 ]

 */

// 回溯法，注意for loop中的start位置每次递归要加，否则会出现重复的combination
public class CombinationSum39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        recursion(res, candidates, new ArrayList<>(), target, 0);
        return res;
    }

    public void recursion( List<List<Integer>> res, int[] candidates,
                           List<Integer> tempList, int less, int start)
    {
        if(less < 0) return;
        if(less == 0)
        {
            res.add(new ArrayList<>(tempList));
            return;
        }
        else
        {
            for (int i = start; i < candidates.length; i++)
            {

                int candi = candidates[i];
                tempList.add(candi);
                recursion(res, candidates, tempList, less-candi, i);
                tempList.remove(tempList.size()-1);
            }
        }

    }

}
