package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class A46_Permutations {

    @Test
    public void test(){
        int a[]= {1, 2, 3};
        List<List<Integer>> resultList= new ArrayList();

        Perm(a,0,a.length -1, resultList);
        System.out.println(resultList.toString());

        resultList.clear();
        permBackTrace(a, 0, a.length, new ArrayList<>(), resultList);
        System.out.println(resultList.toString());
    }



    void Perm(int[] list , int start ,int end, List<List<Integer>> resultList){

        if(start == end){
            List<Integer> result = new ArrayList<>();
            for(int i = 0; i <= end; i++){
                result.add(list[i]);
            }
            resultList.add(result);
            return;
        } else {
            for (int i = start; i <= end; i++){
                swap(list, i, start);
                Perm(list, start+1, end, resultList);
                swap(list, i, start);
            }
        }
    }

    void permBackTrace(int[] list , int start ,int end, List<Integer> temp, List<List<Integer>> resultList){
        if(start == end){
            resultList.add(new ArrayList<>(temp));
        } else {

            for (int i = start; i < end; i++){
                swap(list, i, start);
                temp.add(list[start]);
                permBackTrace(list, start+1, end, temp, resultList);
                temp.remove(temp.size() -1);
                swap(list, i, start);
            }
        }
    }

    void swap(int[] list, int a, int b){
        int temp = list[a];
        list[a] = list[b];
        list[b] = temp;

    }
}
