package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

//https://blog.csdn.net/yixianfeng41/article/details/55684782
public class A78_Subsets {

    @Test
    public void test() {
        List<List<Integer>> result = subsets(new int[]{1, 2, 3});
        System.out.println(result);

        result = subsetsBackTrace(new int[]{1, 2, 3});
        System.out.println(result);
    }


    public List<List<Integer>> subsets(int[] nums) {
         List<List<Integer>> result = new ArrayList<>();
         int length = nums.length;
         result.add(new ArrayList<>());
         for(int i = 0; i < length; i++){
             int count = result.size();
             for(int j = 0; j < count; j++){
                 List<Integer> list = new ArrayList<>(result.get(j));
                 list.add(nums[i]);
                 result.add(list);
             }
         }

         return result;
     }

    public List<List<Integer>> subsetsBackTrace(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();

        return subsetsDfs(nums, 0, temp, result);
    }

    public List<List<Integer>> subsetsDfs(int[] nums, int start, List<Integer> temp, List<List<Integer>> result) {
        result.add(new ArrayList<>(temp));
        if(start == nums.length){
            return result;
        } else {
            for(int i = start; i < nums.length; i++){
                temp.add(nums[i]);
                subsetsDfs(nums, i+1, temp, result);
                temp.remove(temp.size()-1);
            }
        }

        return result;
    }

}
