package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/*
* https://blog.csdn.net/yixianfeng41/article/details/55684782  回朔算法
*
*
* */
public class A131_PalindromePartition {

    @Test
    public void test(){

        List<List<String>> result = partition("aab");
        System.err.println(result);
    }

    public List<List<String>> partition(String s) {
        if(s == null || s.length() == 0) return  null;

        List<List<String>> resultList = new ArrayList<>();
        dfsPalindrome(s, resultList, new ArrayList<>(), 0);

        return resultList;
    }

    public void dfsPalindrome(String s, List<List<String>> result, List<String> temp, int start){
        if(start ==  s.length()){
            result.add(new ArrayList(temp));
            return;
        } else {

            for (int i = start; i < s.length(); i++) {
                if (isPalindrome(s, start, i)) {

                    temp.add(s.substring(start, i+1));
                    dfsPalindrome(s,  result, temp, i + 1);            //dfs的作用递归下去，不断切割字符串，
                    temp.remove(temp.size() - 1);
                }
            }
        }
    }

    boolean isPalindrome(String s, int start, int end){
        while (start <= end){
            if(! (s.charAt(start++) == s.charAt(end--))){
                return  false;
            }
        }

        return  true;

    }
}
