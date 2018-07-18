package matrix;

/***
 * Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in-place.

 Example 1:

 Input:
 [
 [1,1,1],
 [1,0,1],
 [1,1,1]
 ]
 Output:
 [
 [1,0,1],
 [0,0,0],
 [1,0,1]
 ]
 Example 2:

 Input:
 [
 [0,1,2,0],
 [3,4,5,2],
 [1,3,1,5]
 ]
 Output:
 [
 [0,0,0,0],
 [0,4,5,0],
 [0,3,1,0]
 ]
 Follow up:

 A straight forward solution using O(mn) space is probably a bad idea.
 A simple improvement uses O(m + n) space, but still not the best solution.
 Could you devise a constant space solution?

 */

// 用每行和每列的第一个元素记录该行该列中是否有0，由于左上角的元素是行列交汇点，只能存一个值（比如第0列中是否有0），
// 因此需要一个变量来存另一个值，同时循环时有一个维度需要从index = 1开始
public class SetMatrixZeros73
{
    public void setZeroes(int[][] matrix) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int col0Indicator = 1;
        // set indicators
        for (int i = 0; i < rows; i++)
        {
            if(matrix[i][0] == 0)
            {
                col0Indicator = 0;
            }

            for(int j = 1; j < cols; j++)
            {
                if(matrix[i][j] == 0)
                {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        // 此处需要倒序遍历，否则第一行和第一列有个别0会导致整行整列被set为0，进而导致所有元素都被set为0
        // clear
        for (int i = rows - 1; i >= 0 ; i--)
        {
            for(int j = cols - 1 ; j >= 1; j--)
            {
                if(matrix[i][0] == 0 || matrix[0][j] == 0)
                {
                    matrix[i][j] = 0;
                }
            }
            // check and set the first col
            if(col0Indicator == 0)
            {
                matrix[i][0] = 0;
            }
        }


    }
}
