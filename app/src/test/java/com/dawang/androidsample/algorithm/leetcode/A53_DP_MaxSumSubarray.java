package com.dawang.androidsample.algorithm.leetcode;

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

    /* https://www.cnblogs.com/coderJiebao/p/Algorithmofnotes27.html
     *
     步骤 1：令状态 dp[i] 表示以 A[i] 作为末尾的连续序列的最大和（这里是说 A[i] 必须作为连续序列的末尾）。
     步骤 2：做如下考虑：因为 dp[i] 要求是必须以 A[i] 结尾的连续序列，那么只有两种情况：

     这个最大和的连续序列只有一个元素，即以 A[i] 开始，以 A[i] 结尾。
     这个最大和的连续序列有多个元素，即从前面某处 A[p] 开始 (p<i)，一直到 A[i] 结尾。
     对第一种情况，最大和就是 A[i] 本身。
     对第二种情况，最大和是 dp[i-1]+ A[i]。

     于是得到状态转移方程：
　　　　　　dp[i] = max{A[i], dp[i-1]+A[i]}
     这个式子只和 i 与 i 之前的元素有关，且边界为 dp[0] = A[0]，由此从小到大枚举 i，即可得到整个 dp 数组。
     接着输出 dp[0]，dp[1]，...，dp[n-1] 中的最大子即为最大连续子序列的和。
     */

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
