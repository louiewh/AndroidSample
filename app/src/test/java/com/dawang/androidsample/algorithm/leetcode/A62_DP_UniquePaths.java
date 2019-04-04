package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

public class A62_DP_UniquePaths {

    @Test
    public void test() {
        int result = uniquePaths(3, 2);
        System.out.println(result);

        result = uniquePaths(7, 3);
        System.out.println(result);
    }

    public int uniquePaths(int m, int n) {

        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i == 0 || j == 0){
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[m-1][n-1];
    }
}
