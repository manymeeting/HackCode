package matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:

 Integers in each row are sorted from left to right.
 The first integer of each row is greater than the last integer of the previous row.
 Example 1:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 3
 Output: true
 Example 2:

 Input:
 matrix = [
 [1,   3,  5,  7],
 [10, 11, 16, 20],
 [23, 30, 34, 50]
 ]
 target = 13
 Output: false
 */

// 二分查找，通过mid_val = matrix[mid / col][mid % col]来分割
// 注意在更新边界时要移动一位，begin = mid + 1; 否则会出现死循环（mid = (begin + end) / 2，导致begin和mid只差1时会相等）
// end每次更新时-1，为了让终止时（beign=end后）可以跳出while循环

public class SearchA2DMatrix74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int begin = 0;
        int end = row * col - 1;

        while(begin <= end)
        {
            int mid = (begin + end) / 2;
            int mid_val = matrix[mid / col][mid % col];
            if(target == mid_val)
            {
                return true;
            }
            else if(mid_val < target)
            {
                // Add one, otherwise can be dead loop
                begin = mid + 1;
                continue;
            }
            else
            {
                end = mid - 1;
                continue;
            }
        }
        return false;
    }
}
