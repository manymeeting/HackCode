/***
 *
 Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.

 Note that the row index starts from 0.
 Example:

 Input: 3
 Output: [1,3,3,1]
 Follow up:

 Could you optimize your algorithm to use only O(k) extra space?

 */

import java.util.ArrayList;
import java.util.List;

// 两层循环，逐个更新数值前要用一个copy的list来记录上一层list
public class PascalsTriangleII119 {

    public List<Integer> getRow(int rowIndex) {
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i <= rowIndex; i++)
        {
            res.add(1);

            ArrayList<Integer> copy = new ArrayList<>(res);

            for(int j = 1; j < i; j++)
            {
                res.set(j, copy.get(j-1) + copy.get(j));
            }
        }

        return res;
    }
}
