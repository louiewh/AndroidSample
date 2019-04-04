package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

public class A64_DP_MinPathSum {

    @Test
    public void test() {
        int result = minPathSum(new int[][]{
                {1,3,1},
                {1,5,1},
                {4,2,1}
        });
        System.out.println(result);

        result = minPathSum(new int[][]{
                {1,2},
                {1,1}
        });
        System.out.println(result);
    }

    public int minPathSum(int[][] grid) {
        int line = grid.length;
        int raw = grid[0].length;

        int[][] dp = new int[line][raw];

        for(int i = 0; i < line; i++){
            for (int j = 0; j < raw; j++) {
                if(i == 0 && j == 0){
                    dp[i][j] = grid[i][j];
                } else if(i == 0){
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                } else if (j == 0){
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }

        return dp[line-1][raw-1];
    }
}
