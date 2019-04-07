package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class A15_3Sum {

    @Test
    public void test(){
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));

        nums = new int[]{-2,0,0,2,2};
        result = threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));

        nums = new int[]{-1,0,1,2,-1,-4};
        result = threeSum(nums);
        System.out.println(Arrays.toString(result.toArray()));
    }

    public List<List<Integer>> threeSum(int[] nums) {
        int length = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> listResult = new ArrayList<>();
        for (int i = 0; i < length - 2 ; i++){
            int j = i+1;
            int k = length - 1;

            if(i > 0 && nums[i] == nums[i-1]){
                continue;
            }

            while (j < k){
                int sum = nums[i] + nums[j] + nums[k];
                if(sum == 0){
                    ArrayList<Integer> list = new ArrayList();
                    list.add(nums[i]);
                    list.add(nums[j]);
                    list.add(nums[k]);

                    listResult.add(list);

                    while (j < k && nums[j] == nums[j + 1]) ++j;
                    while (j < k && nums[k] == nums[k - 1]) --k;

                    j++;
                    k--;
                } else if(sum > 0){
                    k--;
                } else {
                    j++;
                }
            }
        }

        return listResult;
    }
}
