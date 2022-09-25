"""
You are given a 0-indexed integer array nums. A pair of indices (i, j) is a bad pair if i < j and j - i != nums[j] - nums[i].

Return the total number of bad pairs in nums.

思路：转换问题，找出valid的pair的数量，然后从总Pairs数量中减去。
此处有一个dp的概念，每次新找出一个之前出现过的nums[i] - i的对应的index，会和之前所有同样计算结果的n个位置组成n个新的pairs，所以要用dp去计对应的pair数量。

"""

class Solution(object):
    def countBadPairs(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        
        # nums[i] - i = nums[j] - j

        validCount = 0
        totalCount = len(nums) * (len(nums) - 1) // 2

        calcResultToPairsCount = {}

        for i, num in enumerate(nums):
            calcResult = i - num
            
            if calcResult in calcResultToPairsCount:
                existingPairsCount = calcResultToPairsCount.get(calcResult)
                validCount += existingPairsCount
                calcResultToPairsCount[calcResult] = existingPairsCount + 1
            else:
                calcResultToPairsCount[calcResult] = 1

        return totalCount - validCount