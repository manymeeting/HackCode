package matrix;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix.
 * This matrix has the following properties:

 Integers in each row are sorted in ascending from left to right.
 Integers in each column are sorted in ascending from top to bottom.
 Example:

 Consider the following matrix:

 [
 [1,   4,  7, 11, 15],
 [2,   5,  8, 12, 19],
 [3,   6,  9, 16, 22],
 [10, 13, 14, 17, 24],
 [18, 21, 23, 26, 30]
 ]
 Given target = 5, return true.

 Given target = 20, return false.

 */
// 注意不能用二分法因为排列顺序不满足
// 从右上角开始，如果target大于当前数则说明target肯定在该行之后（row++），如果小于则说明target肯定在该列之前（col--）
// complexity: O(m+n)
public class SearchA2DMatrixII240 {

    public boolean searchMatrix(int[][] matrix, int target) {

        if(matrix == null || matrix.length < 1 || matrix[0].length < 1)
        {
            return false;
        }

        // start from top right corner
        int col = matrix[0].length-1;
        int row = 0;

        while (col >= 0 && row <= matrix.length-1)
        {
            if(target == matrix[row][col])
            {
                return true;
            }
            else if(target < matrix[row][col])
            {
                col--;
            }
            else
            {
                row++;
            }

        }
        return false;
    }
}
