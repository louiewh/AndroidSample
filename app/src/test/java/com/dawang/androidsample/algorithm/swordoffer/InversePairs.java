package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;

/*
* 在数组中的两个数字，如果前面一个数字大于后面的数字，
* 则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
* 并将P对1000000007取模的结果输出。 即输出P%1000000007
*/

public class InversePairs {

    int[] arrays = new int[]{
            364,637,341,406,747,995,234,971,571,219,993,
            407,416,366,315,301,601,650,418,355,460,505,
            360,965,516,648,727,667,465,849,455,181,486,
            149,588,233,144,174,557,67, 746,550,474,162,
            268,142,463,221,882,576,604,739,288,569,256,
            936,275,401,497, 82,935,983,583,523,697,478,
            147,795,380,973,958,115,773,870,259,655,446,
            863,735,784,3,  671,433,630,425,930, 64,266,
            235,187,284,665,874,80,45,848,38,811,267,575};

    @Test
    public void test(){

        int count = InversePairs(arrays);
        System.err.println(count % 1000000007);
    }

    public int InversePairs(int [] array) {

        int[] temp = new int[array.length];

        return  mergeSort(array, temp, 0, array.length - 1);
    }


    int  mergeSort(int[] array, int[] temp, int start, int end){
        if(start >= end) return 0;

        int middle = (start + end) / 2;

        int leftCount = mergeSort(array, temp, start, middle);
        int rightCount = mergeSort(array, temp, middle+1, end);
        int count = merge(array, temp, start, middle, end);


        return leftCount + rightCount + count;
    }

    int  merge(int[] array, int[] temp, int start, int middle, int end){

        int i = start;
        int j = middle + 1;

        int index = start;

        int count = 0;
        while (i <= middle && j <= end){
            if(array[i] <= array[j]){
                temp[index++] = array[i++];
            } else {
                count += middle - i + 1;
                temp[index++] = array[j++];
            }
        }

        while (i <= middle){
            temp[index++] = array[i++];
        }

        while (j <= end){
            temp[index++] = array[j++];
        }

        for (int k = start; k <= end; k++){
            array[k] = temp[k];
        }

        return count;
    }
}
