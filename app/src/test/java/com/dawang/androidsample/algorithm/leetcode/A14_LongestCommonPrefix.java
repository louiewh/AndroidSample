package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

public class A14_LongestCommonPrefix {

    @Test
    public void test() {
        String[] result = {
                "flower",
                "flow",
                "flight"
        };

        System.out.println(longestCommonPrefix(result));
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";

        int size = strs.length;
        int minlength = strs[0].length();

        for (int i = 0; i < size; i++) {
            int lengthSub = strs[i].length();
            if (lengthSub == 0) {
                return "";
            } else {
                minlength = minlength < lengthSub ? minlength : lengthSub;
            }
        }

        for (int k = 0; k < minlength; k++) {
            char cChar = strs[0].charAt(k);
            for (int i = 1; i < size; i++) {
                if (strs[i].charAt(k) != cChar) {
                    return strs[i].substring(0, i);
                }
            }
        }

        return strs[0].substring(0, minlength);
    }
}
