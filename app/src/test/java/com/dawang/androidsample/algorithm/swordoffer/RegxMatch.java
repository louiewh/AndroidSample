package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;

public class RegxMatch {

    @Test
    public void test(){
        boolean result = match("".toCharArray(), "".toCharArray());

        System.err.println(result);
    }

    public boolean match(char[] str, char[] pattern){

        if(str == null || pattern == null) return false;

        return matchCore(str, 0, pattern, 0);
    }

    private  boolean matchCore(char[] str, int i, char[] pattern, int j) {
        if(j == pattern.length) {
            // pattern遍历完了
            // 如果str也完了，返回true，不然false
            return str.length == i;
        }

        // 注意数组越界问题，一下情况都保证数组不越界
        if(j + 1 < pattern.length && pattern[j + 1] == '*') {
            if(str.length != i && (str[i] == pattern[j] || pattern[j] == '.')) {
                return matchCore(str, i, pattern, j + 2)
                        || matchCore(str, i + 1, pattern, j);
            } else {
                return matchCore(str,i, pattern,j + 2);
            }
        }

        // 下一个不是“*”，当前匹配
        if(str.length != i && (str[i] == pattern[j] || pattern[j] == '.')){
            return matchCore(str,i + 1,pattern,j + 1);
        }

        return false;
    }

}
