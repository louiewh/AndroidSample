package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class A139_WordBreak {
    @Test
    public void test(){
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        boolean result = dpWordBreak("leetcode", wordDict);
        System.err.println(result);

        wordDict.clear();
        wordDict.add("aaaa");
        wordDict.add("aaa");

        result = dpWordBreak("aaaaaaa", wordDict);
        System.err.println(result);
    }

    public boolean wordBreak(String s, List<String> wordDict) {

        return dfsWordBreak(s, wordDict, 0);
    }

    public boolean dfsWordBreak(String s, List<String> wordDict, int start){
        int length = s.length();
        if(start == length){
            return true;
        } else {

            for(int i = start; i < length; i++){
                String sub = s.substring(start, i+1);
                if(contains(wordDict, sub)){

                    if(dfsWordBreak(s, wordDict, i+1)){
                        return  true;
                    }
                }
            }
        }

        return false;
    }

    public boolean dpWordBreak(String s, List<String> wordDict){

        int length = s.length();

        boolean[] dp = new boolean[length+1];
        dp[0] = true;

        for (int i = 0; i < length; i++){
            for (int j = 0; j <=i; j++){
                String sub = s.substring(j, i+1);
                if(dp[j] && contains(wordDict, sub)){
                    dp[i+1] = true;
                }
            }
        }

        return dp[length];
    }


    boolean contains(List<String> wordDict, String string){
        int length = wordDict.size();
        for(int i = 0; i < length; i++){
            if(wordDict.get(i).equals(string)){
                return true;
            }
        }

        return false;
    }
}
