package com.dawang.androidsample.algorithm;

import com.dawang.androidsample.algorithm.base.ListNode;

import org.junit.Test;

public class ReverseList {


    @Test
    public void sortMain() {
        ListNode root = new ListNode(1);
        ListNode node = root;

        node.next = new ListNode(2);
        node = node.next;

        node.next = new ListNode(3);
        node = node.next;

        node.next = new ListNode(4);
        node = node.next;


        node.next = new ListNode(5);
        node = node.next;


        node.next = new ListNode(6);
        node = node.next;

        node.next = new ListNode(7);
        node = node.next;

        node.next = new ListNode(8);
        node = node.next;


        ListNode head = reverseList(root);
        ListNode head2 =head;

        while (head != null){
            System.err.print(head.mValue + " ");
            head = head.next;
        }
        System.err.println();


        head = reverseListRecursion(head2);
        while (head != null){
            System.err.print(head.mValue + " ");
            head = head.next;
        }
        System.err.println();
    }


    private ListNode reverseList(ListNode root){

        if(root == null) return root;

        ListNode head = null;
        while (root != null){
            ListNode tail = root.next;
            root.next = head;
            head = root;
            root = tail;
        }

        return head;
    }

    private ListNode reverseListRecursion(ListNode head){
        if(head == null || head.next == null){
            return head;
        }

        ListNode node = head.next;
        ListNode newHead = reverseListRecursion(node);
        node.next = head;
        head.next = null;

        return newHead;
    }

}
