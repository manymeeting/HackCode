package backtracking;


import java.util.ArrayList;
import java.util.List;

/**
 * Given a collection of distinct integers, return all possible permutations.

 Example:

 Input: [1,2,3]
 Output:
 [
 [1,2,3],
 [1,3,2],
 [2,1,3],
 [2,3,1],
 [3,1,2],
 [3,2,1]
 ]

 */

// 标准的backtracking写法，注意要避免结果集中出现重复数字
    
public class Permutations46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        backTrack(res, new ArrayList<>(), nums);
        return res;
    }

    public void backTrack(List<List<Integer>> res, List<Integer> tempList, int[] nums)
    {
        if(tempList.size() == nums.length) {
            res.add(new ArrayList<>(tempList));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if(tempList.contains(nums[i])) continue; // 避免出现[1,2,2..]这样的重复
            tempList.add(nums[i]);
            backTrack(res, tempList, nums);
            tempList.remove(tempList.size() - 1);

        }
    }


}
