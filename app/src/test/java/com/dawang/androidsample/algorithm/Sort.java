package com.dawang.androidsample.algorithm;

import org.junit.Test;
import java.util.Arrays;

public class Sort {

    @Test
    public void test(){
        int[] a = {9,8,7,6,5,4,3,2,1};
        quickSort(a, 0, a.length-1);
        System.err.println(Arrays.toString(a));

        int[] a2 = {9,8,7,6,5,4,3,2,1};
        mergeSort(a2, 0, a2.length-1);
        System.err.println(Arrays.toString(a2));

        int[] a3 = {9,8,7,6,5,4,3,2,1};
        heapSort(a3);
        System.err.println(Arrays.toString(a3));
    }


    void quickSort(int[] array, int start, int end){
        if(start >= end) return;

        int mid = partition(array, start, end);
        quickSort(array, start, mid);
        quickSort(array, mid+1, end);
    }

    private int partition(int[] array, int start, int end) {

        int pivot = array[start];
        while (start < end) {
            while (start < end && pivot <= array[end]) {
                end--;
            }

            array[start] = array[end];
            while (start < end && pivot >= array[start]) {
                start++;
            }
            array[end] = array[start];
        }

        array[start] = pivot;

        return start;
    }

    void mergeSort(int[] array, int start, int end){

        if(start >= end ) return;

        int mid = (start + end ) / 2;
        mergeSort(array, start, mid);
        mergeSort(array, mid+1, end);

        merge(array, start, mid, end);
    }

    void merge(int[] array, int start, int mid, int end) {

        int i = start;
        int j = mid + 1;
        int[] copy = new int[array.length];
        int pos = start;
        while( i <= mid && j <= end ){
            if(array[i] < array[j]){
                copy[pos++] = array[i++];
            } else {
                copy[pos++] = array[j++];
            }
        }

        while (i <= mid){
            copy[pos++] = array[i++];
        }


        while (j <= end){
            copy[pos++] = array[j++];
        }


        for(i = start; i <= end; i++){
            array[i] = copy[i];
        }
    }

    void heapSort(int[] array){
        int length = array.length;
        for(int i = length/2-1; i >= 0; i--){
            adjustHeap(array, i, length);
        }

        for (int i = length -1; i >=0; i--){
            swap(array, 0, i);
            adjustHeap(array, 0, i);
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private void adjustHeap(int[] array, int i, int length) {
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
