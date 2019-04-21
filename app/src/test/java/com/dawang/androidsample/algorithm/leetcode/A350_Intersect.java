package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class A350_Intersect {

    @Test
    public void test() {
        int[] result = intersect(new int[]{1,2,2,1}, new int[]{2, 2});

        System.out.println(Arrays.toString(result));
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if(nums1 == null || nums1.length == 0) return null;
        if(nums2 == null || nums2.length == 0) return null;
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < nums1.length; i++){
            Integer value = map.get(nums1[i]);
            map.put(nums1[i], value == null ? 1: ++value);
        }

        ArrayList<Integer> list = new ArrayList<>();
        for(int j = 0; j < nums2.length; j++){
            Integer value = map.get(nums2[j]);
            if(map.containsKey(nums2[j]) && value > 0){
                list.add(nums2[j]);
                map.put(nums2[j], --value);
            }
        }

        int length = list.size();
        int[] array = new int[length];
        for(int k = 0; k < length; k++){
            array[k] = list.get(k);
        }

        return array;
    }
}
