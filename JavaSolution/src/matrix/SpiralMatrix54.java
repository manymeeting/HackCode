package matrix;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

 Example 1:

 Input:
 [
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
 ]
 Output: [1,2,3,6,9,8,7,4,5]
 Example 2:

 Input:
 [
 [1, 2, 3, 4],
 [5, 6, 7, 8],
 [9,10,11,12]
 ]
 Output: [1,2,3,4,8,12,11,10,9,5,6,7]

 */


// 维护四个边界值，int up = 0, left = 0, down = m - 1, right = n - 1;
// 用while+四遍for loop，遍历顺序为：上面一整行，右边除去上下边界，下面一整行，左边除去上下边界，
// 每次while结束后收缩四个边界值

public class SpiralMatrix54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;

        int m = matrix.length;
        int n = matrix[0].length;
        int up = 0, left = 0, down = m - 1, right = n - 1;

        while (res.size() < m * n) {

            // 每个for loop中都要再次判断size是否已满

            for (int i = left; i <= right && res.size() < m * n; i++) {
                res.add(matrix[up][i]);
            }

            for (int i = up + 1; i <= down - 1 && res.size() < m * n; i++) {
                res.add(matrix[i][right]);
            }

            for (int i = right; i >= left && res.size() < m * n; i--) {
                res.add(matrix[down][i]);
            }

            for (int i = down - 1; i >= up + 1 && res.size() < m * n; i--) {
                res.add(matrix[i][left]);
            }

            left++; right--; down--; up++;
        }

        return res;
    }
}
