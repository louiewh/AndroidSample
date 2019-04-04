package com.dawang.androidsample.algorithm.swordoffer;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.ArrayList;

public class MaxInWindows {

    @Test
    public void test(){
        int[] array = {1, 2, 99, 837, 14, 113, 124, 131};
        ArrayList<Integer> arrayList = maxInWindows(array, 3);

        System.err.print(arrayList);
    }

    public ArrayList<Integer> maxInWindows(int [] num, int size){
        ArrayList<Integer> result = new ArrayList<>();
        ArrayDeque<Integer> deque = new ArrayDeque<>();

        if(num == null || num.length == 0 || size  == 0 || size > num.length ){
            return result;
        }

        for(int i = 0; i < num.length; i++){

            // 如果队列头元素不在滑动窗口中了，就删除头元素
            if(!deque.isEmpty() && deque.peekFirst() + size <= i){
                deque.pop();
            }

            // 如果当前数字大于队列尾，则删除队列尾，直到当前数字小于等于队列尾，或者队列空
            while(!deque.isEmpty() && num[deque.peekLast()] < num[i]){
                deque.removeLast();
            }

            deque.offer(i);

            if(i + 1 >= size){
                result.add(num[deque.peek()]);
            }
        }

        return result;
    }

}
