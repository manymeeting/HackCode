package backtracking;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Numbers can be regarded as product of its factors. For example,

 8 = 2 x 2 x 2;
 = 2 x 4.
 Write a function that takes an integer n and return all possible combinations of its factors.

 Note:

 You may assume that n is always positive.
 Factors should be greater than 1 and less than n.
 Example 1:

 Input: 1
 Output: []
 Example 2:

 Input: 37
 Output:[]
 Example 3:

 Input: 12
 Output:
 [
 [2, 6],
 [2, 2, 3],
 [3, 4]
 ]
 Example 4:

 Input: 32
 Output:
 [
 [2, 16],
 [2, 2, 8],
 [2, 2, 2, 4],
 [2, 2, 2, 2, 2],
 [2, 4, 4],
 [4, 8]
 ]*/

// backtracking解法，维护一个start，一个n，每次看n % i == 0，然后连续放i和n / i，加到结果集，然后移出n/i后调用递归
// 注意每层递归n = n / i
public class FactorCombinations254 {

    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> res = new LinkedList<>();
        helper(res, n, new LinkedList<>(), 2);
        return res;
    }

    private void helper(List<List<Integer>> res, int n, LinkedList<Integer> tempList, int start)
    {
        for (int i = start; i * i <=n; i++)
        {
            if(n % i == 0)
            {
                tempList.add(i);
                tempList.add(n / i);
                res.add(new LinkedList<>(tempList));

                tempList.removeLast();
                helper(res, n / i, tempList, i);
                tempList.removeLast();
            }
        }
    }


}
