package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.HashMap;

public class A1_SumOfTwoNumber {


    @Test
    public void test(){
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum2(nums, target);

        System.out.println(result);
    }

    public int[] twoSum(int[] nums, int target) {
        int size = nums.length;
        for(int i = 0; i < size; i++){
            for(int j = i+1; j < size; j++){
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }

        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for(int i = 0; i< length; i++){
            map.put(nums[i], i);
        }

        for(int i = 0; i< length; i++){
            int temp = target - nums[i];
            if(map.containsKey(temp) && map.get(temp) != i){
                return new int[]{i, map.get(temp)};
            }
        }

        return null;
    }
}
