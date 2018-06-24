import java.util.List;

/**
 * Given a triangle, find the minimum path sum from top to bottom.
 * Each step you may move to adjacent numbers on the row below.

 For example, given the following triangle

 [
 [2],
 [3,4],
 [6,5,7],
 [4,1,8,3]
 ]
 The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

 Note:

 Bonus point if you are able to do this using only O(n) extra space,
 where n is the total number of rows in the triangle.

 */

/**
 *
 *
 *
 * 隐含条件：从上到下，每一层只能向index+1的方向移动，从下到上则相反
 *

 动态规划：bottom up，对于k层的第i个元素
 minpath[k][i] = min( minpath[k+1][i], minpath[k+1][i+1]) + triangle[k][i];

 由于没必要存储所有level的min path，所以可以用一维数组，每次更新index位置的值即可：
 For the kth level:
 minpath[i] = min(minpath[i], minpath[i+1]) + triangle[k][i];


 */

public class Triangle120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] minlen = triangle.get(triangle.size()-1).stream().mapToInt(i -> Integer.valueOf(i)).toArray();

        for (int  layer = n - 2; layer >= 0; layer--)
        {
            for (int i = 0; i <= layer; i++) // 循环该层的每个元素（根据题意，元素数=层数index+1）
            {
                // Find the lesser of its two children, and sum the current value in the triangle with it.
                minlen[i] = Math.min(minlen[i], minlen[i+1]) + triangle.get(layer).get(i);
            }
        }
        return minlen[0];
    }
}
