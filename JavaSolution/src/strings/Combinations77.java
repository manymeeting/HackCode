package strings;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

 Example:

 Input: n = 4, k = 2
 Output:
 [
 [2,4],
 [3,4],
 [2,3],
 [1,2],
 [1,3],
 [1,4],
 ]

 */

// 采用回溯法backtracking，每次移动start位置，然后循环到尾部依次添加数字，当tempList size=k时加到结果集中
// 回溯法通用思想：在一个loop中递归，每次add元素，调用递归，删除最后一个元素

public class Combinations77 {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backTracking(res, new ArrayList<>(), 1, n, k);
        return res;
    }

    public void backTracking(List<List<Integer>> res, List<Integer> tempList, int start, int n, int k)
    {
        if(tempList.size() == k)
        {
            res.add(new ArrayList<>(tempList));
            return;
        }


        for (int i = start; i <= n; i++)
        {
            tempList.add(i);
            backTracking(res, tempList, i+1, n, k);
            tempList.remove(tempList.size()-1);
        }
    }

}
