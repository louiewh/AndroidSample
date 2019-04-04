package com.dawang.androidsample.algorithm;

import org.junit.Test;

public class MaxLengthSubSequence {

    @Test
    public void test(){

        int result = maxlengthSubSequence("ads", "adl");

        System.out.println(result);
    }

    int maxlengthSubSequence(String string1, String string2){

        int length1 = string1.length();
        int length2 = string2.length();
        int[][] dp = new int[length1+1][length2+1];

        for(int i = 0; i <= length1; i++){
            for (int j = 0; j <= length2; j++){
                if(i == 0 || j == 0){
                    dp[i][j] = 0;
                }else if (string1.charAt(i-1) == string2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                } else {
                    dp[i][j] =  Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }

        return dp[length1][length2];
    }
}
