package com.dawang.androidsample;

import org.junit.Test;

public class QuickSort {

    @Test
    public void sortMain() {
        ListNode head = new ListNode(2);
        head.next = new ListNode(1);

        sortList(head);
    }

    public ListNode sortList(ListNode head) {

        if (head == null) return null;

        int length = 0;
        ListNode node = head;
        while (node != null) {
            length++;
            node = node.next;
        }
        ListNode[] array = new ListNode[length];

        node = head;
        for (int i = 0; i < length; i++) {
            array[i] = node;
            node = node.next;
        }

        quickSort(array, 0, length - 1);

        head = array[0];
        node = array[0];
        for (int i = 1; i < length; i++) {
            node.next = array[i];
            node = node.next;
        }
        node.next = null;

        return head;
    }

    public void quickSort(ListNode[] array, int left, int right) {
        if (left < right) {                  //如果只有1个元素就不执行，2个及以上元素才会执行
            ListNode pivot = array[left];    //设置为基准值
            int low = left;                  //左指针
            int high = right;                //右指针
            while (low < high) {
                while (low < high && array[high].val >= pivot.val)    //右指针指向的值大于等于基准值
                    high--;                  //右指针左移，直到指向的值小于基准值
                array[low] = array[high];    //将左指针指向的值和该值交换

                while (low < high && array[low].val <= pivot.val)    //左指针指向的值小于等于基准值
                    low++;                   //左指针右移，直到指向的值大于基准值
                array[high] = array[low];    //将该值与右指针指向的值交换
            }

            array[low] = pivot;              //将基准值填入正确的位置，这样得到基准值左边的数据都小于它，右边同理
            quickSort(array, left, low - 1);
            quickSort(array, low + 1, right);
        }
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
