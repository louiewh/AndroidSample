package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class A140_WordBreak2 {

    @Test
    public void test(){
        List<String> wordDict = new ArrayList<>();

        wordDict.clear();
        wordDict.add("cat");
        wordDict.add("cats");
        wordDict.add("and");
        wordDict.add("sand");
        wordDict.add("dog");

        System.err.println(wordBreak("catsanddog", wordDict));
        System.err.println(wordBreakOk("catsanddog", wordDict));

    }

    HashMap<String, List<String>> hashMap = new HashMap<>();
    public List<String> wordBreakOk(String s, List<String> wordDict) {
        if(hashMap.containsKey(s)) {
            return hashMap.get(s);
        }

        List<String> list = new ArrayList<>();
        if(0 == s.length()){
            list.add("");
            return list;
        }

        for(String str : wordDict){
            if(s.startsWith(str)){
                List<String> subList = wordBreak(s.substring(str.length()), wordDict);

                for(String sub : subList){
                    StringBuilder builder = new StringBuilder(str);
                    builder.append(Objects.equals("", sub) ? "" : " ");
                    builder.append(sub);
                    list.add(builder.toString());
                }
            }
        }

        hashMap.put(s, list);
        return list;
    }


    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> resultList = new ArrayList<>();
        boolean[] dp = dpWordBreak(s, wordDict);

        dfsWordBreak(s, wordDict, dp, resultList, new ArrayList<>(), 0);
        return  resultList;
    }

    public void dfsWordBreak(String s, List<String> wordDict, boolean[] dp,  List<String> resultList, List<String> temp, int start){
        int length = s.length();
        if(start == length){
            int size = temp.size();

            String st = temp.get(0);
            st += " ";
            for (int i = 1; i< size; i++){
                st += temp.get(i);
                st += " ";
            }

            resultList.add(st.trim());
        } else {
            for(int i = start; i < length; i++){
                String sub = s.substring(start, i+1);
                if(dp[i+1] && contains(wordDict, sub)){
                    temp.add(sub);
                    dfsWordBreak(s, wordDict, dp, resultList, temp, i+1);
                    temp.remove(temp.size()-1);
                }
            }
        }
    }

    public boolean[] dpWordBreak(String s, List<String> wordDict){

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

        return dp;
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
