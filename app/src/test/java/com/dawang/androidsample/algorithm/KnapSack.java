package com.dawang.androidsample.algorithm;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class KnapSack {

    @Test
    public void test() {
        int[] arrayValue = {3000, 2000, 1500};
        int[] weightValue = {4, 3, 1};

        int[][] result = knapSack(4, arrayValue, weightValue);

        for (int i = 0; i < result.length; i++) {
            System.err.println(Arrays.toString(result[i]));
        }
        System.err.println();


        arrayValue = new int[]{1500, 2000, 3000};
        weightValue = new int[]{1, 3, 4};

        result = knapSack(4, arrayValue, weightValue);

        for (int i = 0; i < result.length; i++) {
            System.err.println(Arrays.toString(result[i]));
        }

        int[] list = getResult(result, arrayValue.length, 4, arrayValue, weightValue);
        System.err.println(Arrays.toString(list));

    }


    int[][] knapSack(int Cap, int[] valueArray, int[] weightArray){

        int line = valueArray.length;
        int raw = Cap;

        int[][] dp = new int[line+1][raw+1];

        for (int i  = 1; i <= line; i++ ){
            for (int j = 1; j <= raw; j++){
                if(weightArray[i - 1] > j){
                    dp[i][j] = dp[i-1][j];
                } else {
                    int valueY = dp[i-1][j-weightArray[i-1]] + valueArray[i-1];
                    int valueN = dp[i-1][j];
                    dp[i][j] = Math.max(valueY, valueN);
                }
            }
        }

        return dp;
    }

    int[] getResult(int[][] dp, int line, int raw, int[] valueArray, int[] weightArray){

        int size = line;
        int[] result = new int[line];

        while (line > 0 && raw > 0){
            int valueY = dp[line-1][raw-weightArray[line-1]] + valueArray[line-1];
            int valueN = dp[line-1][raw];
            if(dp[line][raw] == valueY){
                result[line -1] = valueArray[line-1];
                raw = raw - weightArray[line-1];
                line --;
            } else if (dp[line][raw] == valueN){
                line--;
            }
        }

        return result;
    }

}
