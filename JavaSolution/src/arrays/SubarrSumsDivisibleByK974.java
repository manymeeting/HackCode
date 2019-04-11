package arrays;

import java.util.*;
/**
Given an array A of integers, return the number of (contiguous, non-empty) subarrays that have a sum divisible by K.

 

Example 1:

Input: A = [4,5,0,-2,-3,1], K = 5
Output: 7
Explanation: There are 7 subarrays with a sum divisible by K = 5:
[4, 5, 0, -2, -3, 1], [5], [5, 0], [5, 0, -2, -3], [0], [0, -2, -3], [-2, -3]
 

Note:

1 <= A.length <= 30000
-10000 <= A[i] <= 10000
2 <= K <= 10000
*/


// 解法2：用余数的分布来计算
/**
通过推导可看出，如果
sum[i]=p*K+ri
sum[j]=q*K+rj
那么 sum[j]-sum[i]=(q-p)*K + rj-ri，因为rj和ri都是除k的余数，所以只要满足rj=ri，那么sum[j]-sum[i]是K的倍数
因此用一个map（可以用array代替，因为长度是固定的K）来记录余数的分布，再用组合公式即可计算出可能的subarr个数（分别两两组合）
*/
class SubarrSumsDivisibleByK974 {
    public int subarraysDivByK(int[] A, int K) {

        int[] modFreqMap = new int[K];
        int sum = 0;


        for (int i = 0; i < A.length; i++) {
        	int num = A[i];
        	sum += num;
        	int mod = sum % K;
        	if(mod < 0) mod = mod + K; // java中会出现余数为负的情况，比如-1/5余-1，需要统一为正的（+K即可，相当于商-1余4）
        	modFreqMap[mod]++;
        }

        int count = 0;
        // 用组合公式（n个元素中两两组合）计算count
        for (int modCnt : modFreqMap) {
        	if(modCnt > 1) {
        		count += (modCnt * (modCnt-1)) / 2 ; 
        	}	
        }
        // mod等于0时，不用组合就可以算作一个答案
        count+= modFreqMap[0];

        return count;
    }
}

// 解法1：用prefix sum遍历所有可能，时间复杂度n^2
// class SubarrSumsDivisibleByK974 {
//     public int subarraysDivByK(int[] A, int K) {
//         int[] preSum = new int[A.length];
//         preSum[0] = A[0];
//         for (int i = 1; i < A.length; i++) {
//         	preSum[i] = preSum[i-1] + A[i];
//         }

//         // 从每个i开始，尝试以i为开头，结尾从i一直变化到len-1为止，每个区间是否能整除K
//         int count = 0;
//         for (int i = 0; i < preSum.length; i++ ) {
//             if(preSum[i] % K == 0) count++; // 不要漏掉0-i的这一段
            
//         	for (int j = i+1; j < preSum.length; j++) {
//         		if((preSum[j] - preSum[i]) % K == 0) {
//         			count++;
//         		}
//         	}
//         }

//         return count;
//     }
// }