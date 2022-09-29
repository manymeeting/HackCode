package bitmanipulation;

/**
Given a matrix consisting of 0s and 1s, we may choose any number of columns in the matrix and flip every cell in that column.  Flipping a cell changes the value of that cell from 0 to 1 or from 1 to 0.

Return the maximum number of rows that have all values equal after some number of flips.

 

Example 1:

Input: [[0,1],[1,1]]
Output: 1
Explanation: After flipping no values, 1 row has all values equal.
Example 2:

Input: [[0,1],[1,0]]
Output: 2
Explanation: After flipping values in the first column, both rows have equal values.
Example 3:

Input: [[0,0,0],[0,0,1],[1,1,0]]
Output: 2
Explanation: After flipping values in the first two columns, the last two rows have equal values.
 

Note:

1 <= matrix.length <= 300
1 <= matrix[i].length <= 300
All matrix[i].length's are equal
matrix[i][j] is 0 or 1

*/


// 思路：题意要求找出一行内数字全一样的行的个数，其实就是找在经过一系列的flip之后，能成为全1或全0的行的个数
// 可以利用XOR的操作，分别以每一行为基准，判断和其它所有行的关系，一旦遇到互补或全相等的行，就说明可以通过flip达到行内数字一致的状态
// 遍历每个基准行后，取最大一致行的行数即可

class FlipColForMaxEqualRows1072 {
	public int maxEqualRowsAfterFlips(int[][] matrix) {
    	int m = matrix.length;
    	int n = matrix[0].length;
    	int res = 1;

        for (int i = 0; i < m; i++) {
        	int sameCntByRow = 0; // 在以当前行为基准的情况下，互补或相同的行的个数
        	for (int j = 0; j < m; j++) {
        		int xorCnt = 0;
        		for (int k = 0; k < n; k++) {
                    xorCnt += matrix[j][k] ^ matrix[i][k];
        			
        		}
        		if(xorCnt == 0 || xorCnt == n) {
        			sameCntByRow++;
        		}
        	}
        	res = Math.max(res, sameCntByRow);
        }

        return res;


    }
}

