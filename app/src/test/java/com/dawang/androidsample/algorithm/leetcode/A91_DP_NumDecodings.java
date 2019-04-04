package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

public class A91_DP_NumDecodings {
    @Test
    public void test() {
        int result = numDecodings("12");
        System.out.println(result);

        result = numDecodings("226");
        System.out.println(result);

        result = numDecodings("0");
        System.out.println(result);
    }

    public int numDecodings(String s) {

        int length = s.length();
        int[] dp = new int[length];

        int dig = getAnInt(s, 0);
        if(dig > 0){
            dp[0] = 1;
        } else {
            dp[0] = 0;
        }

        if(length == 1) return  dp[0];

        dig = getAnInt(s, 1) + (getAnInt(s, 0))*10;
        int num1 = getAnInt(s, 1);
        int num2 = getAnInt(s, 0);
        dig = num1 + num2*10;

        if(dig > 26 || num1 == 0){
            dp[1] = 1;
        } else {
            dp[1] = 2;
        }

        for(int i = 2; i < length; i++){
            num1 = getAnInt(s, 1);
            num2 = getAnInt(s, 0);
            dig = num1 + num2*10;
            if(num1 == 0){
                dp[i] = dp[i-2];
            } else if(dig > 26){
                dp[i] = dp[i-1];
            } else {
                dp[i] = dp[i-1] + dp[i -2];
            }
        }

        return dp[length-1];

    }

    private int getAnInt(String s, int i) {
        return s.charAt(i) - '0';
    }
}
