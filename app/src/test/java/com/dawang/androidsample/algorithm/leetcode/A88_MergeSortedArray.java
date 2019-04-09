package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.Arrays;

public class A88_MergeSortedArray {


    @Test
    public void test() {

        int[] nums1 = {1,2,3,0,0,0};
        int m = 3;
        int[] nums2 = {2,5,6};
        int n = 3;
        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }


    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int length = m + n;
        int first = 0;
        int second = 0;
        int flag = 0;
        int[] newArray = new int[m+n];


        while(first < m && second < n){
            if(nums1[first] < nums2[second]){
                newArray[flag++] = nums1[first++];
            } else {
                newArray[flag++] = nums2[second++];
            }
        }

        while(first < m){
            newArray[flag++] = nums1[first++];
        }

        while(second < n){
            newArray[flag++] = nums2[second++];
        }

        for(int i = 0; i < flag; i++){
            nums1[i] = newArray[i];
        }
    }
}
