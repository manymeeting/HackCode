package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.



 Given an integer n, return all distinct solutions to the n-queens puzzle.

 Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

 Example:

 Input: 4
 Output: [
 [".Q..",  // Solution 1
 "...Q",
 "Q...",
 "..Q."],

 ["..Q.",  // Solution 2
 "Q...",
 "...Q",
 ".Q.."]
 ]
 Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above.
 */

// dfs遍历所有可能，按行递归，在每次行中遍历列来找到valid的col，注意用一个array来表示所有queen的位置，
// [".Q..",
//  "...Q",
//  "Q...",
//  "..Q."],
// 对应的1位数组为{1，3，0，2}

public class NQueens51 {
    public List<List<String>> solveNQueens(int n) {

        int[] queenList = new int[n];
        List<List<String>> res = new ArrayList<>();
        placeQueen(n, queenList, 0, res);
        return res;
    }


    private void placeQueen(int n, int[] queenList, int row, List<List<String>> res) {
        if (row == n) {
            res.add(renderBoard(queenList));
            return;
        }

        for (int i = 0; i < n; i++) { // 尝试当前行的每一列，一旦填上就开始填下一行
            if(isValid(queenList, row, i)) {
                queenList[row] = i;
                placeQueen(n, queenList, row+1, res);
            }
        }
    }


    private List<String> renderBoard(int[] queenList) {
        List<String> board = new ArrayList<>();
        for (int i = 0; i < queenList.length; i++) {
            StringBuilder rowSb = new StringBuilder();
            for (int j = 0; j < queenList.length; j++) {
                rowSb.append(queenList[i] == j ? "Q" : ".");
            }

            board.add(rowSb.toString());
        }

        return board;
    }

    private boolean isValid(int[] queenList, int row, int col) {
        for (int i = 0; i < row; i++) {
            int previousQueenCol = queenList[i];
            if(previousQueenCol == col) { // 和新加入的Q处于同一列
                return false;
            }

            // 计算推导：y2-y1 / x2-x1 = 1或-1，
            // i: y1, col: y2, previousQueenCol: x1, row: y2

            if(row - i == col - previousQueenCol) { // 在新加入的Q的右对角线上
                return false;
            }

            if(row - i == previousQueenCol - col) { // 在新加入的Q的左对角线上
                return false;
            }
        }

        return true;
    }
}
