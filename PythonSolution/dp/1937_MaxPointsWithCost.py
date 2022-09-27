"""
You are given an m x n integer matrix points (0-indexed). Starting with 0 points, you want to maximize the number of points you can get from the matrix.

To gain points, you must pick one cell in each row. Picking the cell at coordinates (r, c) will add points[r][c] to your score.

However, you will lose points if you pick a cell too far from the cell that you picked in the previous row. For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will subtract abs(c1 - c2) from your score.

Return the maximum number of points you can achieve.

思路：可以容易想到第一层DP，但会得到一个 M x N x N 的时间复杂度。可以进一步做DP，对于每一行的每个位置来说，记录该点左侧的所有选择可能得到的最大值，
以及该点右侧的所有选择可能得到的最大值，在计算下一行时直接取对应位置二者的最大值，就可以避免不必要的重复计算。得到M x N的复杂度。
"""



class Solution(object):
    def maxPoints(self, points):
        """
        :type points: List[List[int]]
        :rtype: int
        """
        
        m, n = len(points), len(points[0])
        if m == 1: 
            return max(points[0])
        
        def fillLeftDp(prevDp):
            leftDp = [prevDp[0]] + [0] * (n - 1)
            for i in range(1, n):
                leftDp[i] = max(leftDp[i - 1] - 1, prevDp[i])
            return leftDp
        
        def fillRightDp(prevDp):
            rightDp = [0] * (n - 1) + [prevDp[-1]]
            for i in range(n - 2, -1, -1):
                rightDp[i] = max(rightDp[i + 1] - 1, prevDp[i])
            return rightDp

        
        prevDp = points[0]
        for i in range(m - 1):
            leftDp = fillLeftDp(prevDp)
            rightDp = fillRightDp(prevDp)
            currDp = [0] * n

            for j in range(n):
                currDp[j] = max(leftDp[j], rightDp[j]) + points[i + 1][j]
            # Copy and overwrite
            prevDp = currDp[:]
        
        return max(prevDp)