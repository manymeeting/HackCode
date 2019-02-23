package dp;

// 跟house robber1 类似，只需要把nums拆成两个部分，1-N-1，2-N，然后重复robber1的操作后取最大即可

public class HouseRobberII213 {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int[] nums1 = new int[nums.length - 1];
        int[] nums2 = new int[nums.length - 1];
        for (int i = 0; i < nums.length - 1; i++) nums1[i] = nums[i];
        for (int i = 1; i < nums.length; i++) nums2[i-1] = nums[i];


        int[] dp = new int[nums1.length + 1];
        dp[0] = 0;
        dp[1] = nums1[0];
        for (int i = 2; i < nums1.length + 1; i++) {
            dp[i] = Math.max(dp[i-2] + nums1[i-1], dp[i-1]);
        }

        int max1 = dp[nums1.length];

        dp = new int[nums2.length + 1];
        dp[0] = 0;
        dp[1] = nums2[0];
        for (int i = 2; i < nums2.length + 1; i++) {
            dp[i] = Math.max(dp[i-2] + nums2[i-1], dp[i-1]);
        }

        int max2 = dp[nums2.length];

        return Math.max(max1, max2);
    }
}
