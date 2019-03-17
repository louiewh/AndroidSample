package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A80_DP_ClimbStairs {

    @Test
    public void test() {
        int result = climbStairs(10);
        System.out.println(result);

        result = climbStairs(100);
        System.out.println(result);
    }

    public int climbStairs(int n) {

        if(n == 1) return 1;
        if(n == 2) return 2;

        int sum1 = 1;
        int sum2 = 2;
        int result = 0;

        for(int i = 3; i <= n; i++){
            result = sum1 + sum2;
            sum1 = sum2;
            sum2 = result;
        }

        return result;
    }
}
