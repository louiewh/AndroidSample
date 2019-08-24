package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.PriorityQueue;

public class A215_FindKthLargest {

    @Test
    public void test(){
        System.err.println(findKthLargest(new int[]{3,2,1,5,6,4}, 2));
    }

    public int findKthLargest(int[] nums, int k) {
        // init heap 'the smallest element first'
        PriorityQueue<Integer> heap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);

        // keep k largest elements in the heap
        for (int n: nums) {
            heap.add(n);
            if (heap.size() > k)
                heap.poll();
        }

        // output
        return heap.poll();
    }
}
