package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A5_DP_LongestPalindrome {


    @Test
    public void test(){
        String result = longestPalindrome("abcabcbb");
        System.out.println(result);

        result = longestPalindrome("bbbbb");
        System.out.println(result);

        result = longestPalindrome("adblsjfld");
        System.out.println(result);


        result = longestPalindromeDP("abcabcbb");
        System.out.println(result);

        result = longestPalindromeDP("bbbbb");
        System.out.println(result);

        result = longestPalindromeDP("adblsjfld");
        System.out.println(result);
    }

    public String longestPalindrome(String s) {
        if(s.equals("")) return "";

        int length = s.length();
        int start = 0;
        int end = 0;

        for(int i = 0; i < length; i++){
            for(int j = i + 1; j < length; j++){

                int temp1 = i;
                int temp2 = j;
                for(; temp1 <= temp2; temp1++, temp2--){
                    if(!(s.charAt(temp1) == s.charAt(temp2))){
                        break;
                    }
                }

                if (temp1 > temp2 && j - i > end - start){
                    start = i;
                    end = j;
                }
            }
        }

        return s.substring(start, end+1);
    }

    public String longestPalindromeDP(String s) {
        if (s.equals("")) return "";

        int length = s.length();
        int start = 0;
        int end = 0;
        boolean[][] dp = new boolean[length][length];

        for (int i = length -1; i>=0; i--){
            for(int j = i; j < length; j++){
                if(i == j){
                    dp[i][j] = true;
                } else if( j - i == 1){
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? true:false;
                } else if( j - i > 1) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1] ? true : false;
                }

                if(dp[i][j] && j-i > end -start){
                    start = i;
                    end =j;
                }
            }
        }

        return s.substring(start, end+1);
    }
}
