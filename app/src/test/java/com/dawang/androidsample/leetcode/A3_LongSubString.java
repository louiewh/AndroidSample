package com.dawang.androidsample.leetcode;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class A3_LongSubString {

    @Test
    public void test(){
        int result = lengthOfLongestSubstring("abcabcbb");
        System.out.println(result);

        result = lengthOfLongestSubstring("bbbbb");
        System.out.println(result);

        result = lengthOfLongestSubstring("adblsjfld");
        System.out.println(result);
    }


    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Set<Character> set = new HashSet<>();
        int result = 0;
        int start = 0;
        int end = 0;
        while(start < length && end < length){
            if(!set.contains(s.charAt(end))){
                set.add(s.charAt(end++));
                // result = result > end -  start ? result: end - start;
                result = Math.max(result, end - start);
            } else {
                set.remove(s.charAt(start++));
            }
        }

        return result;
    }
}
