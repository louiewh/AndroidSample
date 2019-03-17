package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A53_DP_MaxSumSubarray {

    @Test
    public void test() {
        int result = maxSubArray(new int[]{-1, -3, 0, -5});
        System.out.println(result);

        result = maxSubArray(new int[]{-1, -3, 6, -5});
        System.out.println(result);


        result = maxSubArrayDP(new int[]{-1, -3, 0, -5});
        System.out.println(result);

        result = maxSubArrayDP(new int[]{-2,1,-3,4,-1,2,1,-5,4});
        System.out.println(result);
    }

    public int maxSubArray(int[] nums) {
        int length = nums.length;
        int result = nums[0];
        int sum = 0;

        for(int i = 0; i < length; i++){
            sum += nums[i];
            if(sum > result){
                result = sum;
            }

            if (sum < 0){
                sum = 0;
            }
        }

        return result;
    }

    public int maxSubArrayDP(int[] nums) {
        int length = nums.length;
        int[] dp = new int[length];
        int result = nums[0];
        dp[0] = nums[0];

        for(int i = 1; i < length; i++){

            dp[i] = nums[i] + Math.max(dp[i-1], 0);
            if(result < dp[i]){
                result = dp[i];
            }
        }

        return result;
    }
}
