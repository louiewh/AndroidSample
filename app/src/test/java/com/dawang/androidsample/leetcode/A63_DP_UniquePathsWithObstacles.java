package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A63_DP_UniquePathsWithObstacles {

    @Test
    public void test() {
        int result = uniquePathsWithObstacles(new int[][]{
                {0, 0 ,0},
                {0, 1, 0},
                {0, 0, 0}
        });
        System.out.println(result);

//        result = uniquePathsWithObstacles(new int[][]{
//
//        });
//        System.out.println(result);
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int line = obstacleGrid.length;
        int raw = obstacleGrid[0].length;

        int[][] dp = new int[line][raw];

        for(int i = 0; i < line; i++){
            for (int j = 0; j < raw; j++){

                if(obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else if(i == 0){
                    if(j > 0 && dp[i][j-1] == 0){
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                } else if(j == 0){
                    if(i > 0 && dp[i-1][j] == 0){
                        dp[i][j] = 0;
                    } else {
                        dp[i][j] = 1;
                    }
                } else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }

        return dp[line -1][raw-1];

    }
}
