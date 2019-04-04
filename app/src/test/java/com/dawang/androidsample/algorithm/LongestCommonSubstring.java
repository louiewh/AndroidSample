package com.dawang.androidsample.algorithm;

import org.junit.Test;

public class LongestCommonSubstring {

    @Test
    public void test(){

        int[][] result = lcs("adfadf", "adeg");

        System.err.println(result[result.length-1][result[0].length-1]);
        System.err.println(getLcs("adfadf", "afdeg", result));

    }
    private int mLine;
    private int mRaw;
    private int[][] lcs(String string1, String string2){
        int line = string1.length();
        int raw  = string2.length();

        int length = 0;
        int[][]dp = new int[line+1][raw+1];
        for(int i = 1; i <= line; i++){
            for(int j = 1; j <= raw; j++){

                if(string1.charAt(i-1) == string2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if(dp[i][j] > length){
                        length = dp[i][j];
                        mLine = i;
                        mRaw = j;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return dp;

    }


    String getLcs(String string1, String string2, int[][] dp){
        int line = mLine;
        int raw  = mRaw;

        int length = dp[line][raw];
        char[] result = new char[length];

        while (dp[line][raw] > 0){
            if (dp[line][raw] == dp[line -1][raw - 1] +1 ){
                result[length-1] = string1.charAt(line-1);
                line--;
                raw--;
                length--;
            }
        }

        return new String(result);
    }
}
