package com.dawang.androidsample.algorithm;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class SumSequence {

    @Test
    public void test(){

        List<List<Integer>> result = sumSequence(20);
        System.err.print(result);
    }


    List<List<Integer>>  sumSequence(int k){
        int start = 1;
        int end = 1;

        List<List<Integer>> result = new ArrayList<>();

        while (start <= k && end <= k && start <= end){
            int sum = 0;
            List<Integer> list = new ArrayList<>();

            for (int i = start; i <= end; i++){
                sum += i;
                list.add(i);
            }

            if(sum == k){
                result.add(list);
                start++;
                end++;
            } else if(sum > k){
                start ++;
            } else if(sum < k){
                end++;
            }
        }

        return result;
    }
}
