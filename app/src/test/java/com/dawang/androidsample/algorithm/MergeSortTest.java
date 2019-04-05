package com.dawang.androidsample.algorithm;

import org.junit.Test;

import java.util.Arrays;

public class MergeSortTest {


    @Test
    public void mergeMain(){
        int[] array = {9,8,7,6,5,4,3,2,1};
        mergeSort(array, 0, array.length-1);

        System.err.println(Arrays.toString(array));
    }

    public void mergeSort(int[] array, int start, int end){
        int mid = start + (end - start) / 2;

        if(start < end) {

            mergeSort(array, start, mid);
            mergeSort(array, mid+1, end);

            merge(array, start, end);
        }
    }


    private void merge(int[] array, int start, int end){
        int[] temp = new int[end - start + 1];
        int mid = start + (end - start) / 2;
        int i = start;
        int j = mid+1;
        int t = 0;

        while (i <= mid && j <= end){
            if(array[i] <= array[j]){
                temp[t++] = array[i++];
            } else {
                temp[t++] = array[j++];
            }
        }

        while (i <= mid){
            temp[t++] = array[i++];
        }


        while (j <= end){
            temp[t++] = array[j++];
        }


        t = 0;
        while (start <= end){
            array[start++] = temp[t++];
        }

    }
}
