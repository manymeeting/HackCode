package matrix;

/**
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right has the same element.

 Now given an M x N matrix, return True if and only if the matrix is Toeplitz.


 Example 1:

 Input:
 matrix = [
 [1,2,3,4],
 [5,1,2,3],
 [9,5,1,2]
 ]
 Output: True
 Explanation:
 In the above grid, the diagonals are:
 "[9]", "[5, 5]", "[1, 1, 1]", "[2, 2, 2]", "[3, 3]", "[4]".
 In each diagonal all elements are the same, so the answer is True.
 Example 2:

 Input:
 matrix = [
 [1,2],
 [2,2]
 ]
 Output: False
 Explanation:
 The diagonal "[1, 2]" has different elements.

 Note:

 matrix will be a 2D array of integers.
 matrix will have a number of rows and columns in range [1, 20].
 matrix[i][j] will be integers in range [0, 99].

 Follow up:

 What if the matrix is stored on disk, and the memory is limited such that you can only load at most one row of the matrix into the memory at once?
 What if the matrix is so large that you can only load up a partial row into the memory at once?

 */

// 写一个function来检查从某一点出发，一直往右下角延伸的path上所有点是否符合条件，
// 然后以最左列和最上边每一个点为start，看是否都符合
    
public class ToeplitzMatrix766 {
    public boolean isToeplitzMatrix(int[][] matrix) {
        if(matrix.length == 0) return true;

        int m = matrix.length;
        int n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            if(!isValidStart(i+1, 1, matrix[i][0], matrix)) {
                return false;
            }
        }

        for (int j = 0; j < n; j++) {
            if(!isValidStart(1, j+1, matrix[0][j], matrix)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidStart(int i, int j, int num, int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        while(i < m && j < n) {
            if(num != matrix[i][j]) return false;
            i++;
            j++;
        }

        return true;
    }
}
