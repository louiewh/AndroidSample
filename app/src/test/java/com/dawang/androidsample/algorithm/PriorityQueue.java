package com.dawang.androidsample.algorithm;

import org.junit.Test;

import java.util.Arrays;

public class PriorityQueue {


    @Test
    public void test(){
        int[] a = {9,8,7,6,5,4,3,2,1};
        // quickSort(a, 0, a.length-1);
        // System.err.println(Arrays.toString(a));

        // int[] a2 = {9,8,7,6,5,4,3,2,1};
        // mergeSort(a2, 0, a2.length-1);
        // System.err.println(Arrays.toString(a2));

        int[] a3 = {9,8,7,6,5,4,3,2,1};
        heapSort(a3);
        System.err.println(Arrays.toString(a3));
    }


    void heapSort(int[] array){
        int length = array.length;
        for(int i = length/2-1; i >= 0; i--){
            shiftDown(array, i, length);
        }

        for (int i = length -1; i >=0; i--){
            swap(array, 0, i);
            shiftDown(array, 0, i);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void shiftDown(int[] array, int i, int length) {
        for(int k = i*2+1; k < length; k = k*2+1){

            if(k+1 < length && array[k] < array[k+1]){
                k = k+1;
            }

            if(array[i] < array[k]){
                swap(array, i, k);
                i = k;
            } else {
                break;
            }
        }
    }
}
