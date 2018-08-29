package backtracking;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**Given a collection of numbers that might contain duplicates, return all possible unique permutations.

 Example:

 Input: [1,1,2]
 Output:
 [
 [1,1,2],
 [1,2,1],
 [2,1,1]
 ]
 */

//采用swap+backtracking的思路，每次交换一个元素，start idx++，递归，再交换回来
//为了避免结果集出现重复，要用一个Set来记录当前递归层级中用过的元素
//idea from: https://leetcode.com/problems/permutations-ii/discuss/18648/Share-my-Java-code-with-detailed-explanantion
public class PermutationsII47 {

    public List<List<Integer>> permuteUnique(int[] nums) {
        if(nums.length == 0){
            return new ArrayList<>();
        }

        List<List<Integer>> res = new ArrayList<>();
        permutation(nums, 0, res);
        return res;
    }

    private void permutation(int[] nums, int start, List<List<Integer>> res)
    {
        if(start == nums.length)
        {
            ArrayList<Integer> newPerm = new ArrayList<>();
            for (int x : nums) newPerm.add(x);
            res.add(newPerm);
            return;
        }

        HashSet<Integer> used = new HashSet();
        for (int i = start; i < nums.length; i++)
        {
            int num = nums[i];
            if(used.add(num))
            {
                swap(nums, start, i);
                permutation(nums, start+1, res);
                swap(nums, start, i);
            }
        }

    }

    private void swap(int[] nums, int idxA, int idxB)
    {
        int temp = nums[idxA];
        nums[idxA] = nums[idxB];
        nums[idxB] = temp;
    }


}
