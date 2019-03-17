package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A1_SumOfTwoNumber {


    @Test
    public void test(){
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] result = twoSum(nums, target);

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
}
