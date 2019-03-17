package com.dawang.androidsample.leetcode;

import org.junit.Test;

public class A2_TwoNumberAdd {


    @Test
    public void test(){
      ListNode node1 = new ListNode(2);
      node1.next = new ListNode(3);
      node1.next.next = new ListNode(4);

      ListNode node2 = new ListNode(5);
      node2.next = new ListNode(6);
      node2.next.next = new ListNode(7);

      ListNode result = addTwoNumbers(node1, node2);

      System.out.println(result);
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode resultNode = head;
        int temp = 0;

        while(l1 != null && l2 != null){
            int result = l1.val + l2.val + temp;
            ListNode node  = new ListNode(result % 10);
            temp = result / 10;
            if(head == null) {
                head = node;
                resultNode = head;
            } else {
                head.next = node;
                head = head.next;
            }

            l1 = l1.next;
            l2 = l2.next;
        }

        ListNode end = (l1 != null) ? l1:l2;
        while(end != null){
            int val = end.val + temp;
            head.next = new ListNode(val % 10);
            temp = val / 10;
            end = end.next;
            head = head.next;
        }


        if(temp != 0){
            head.next = new ListNode(temp);
        }


        return resultNode;
    }
}
