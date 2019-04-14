package com.dawang.androidsample.algorithm.leetcode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class A138_CopyRandomList {

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    }

    @Test
    public void test(){
        Node node1 = new Node(1, null, null);
        Node node2 = new Node(2, null, null);

        node1.next = node2;
        node1.random = node2;
        node2.random = node2;
        Node result = copyRandomList(node1);

        System.err.println(result);
    }

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node nodeHead = head;
        // 1 copy node
        while(head != null){
            Node node = new Node(head.val, head.next, null);
            head.next = node;
            head = node.next;
        }

        // 2 copy random
        head = nodeHead;
        while(head != null){
            Node next = head.next;
            if(head.random != null){
                if(head.random == head){
                    next.random = next;
                } else {
                    Node random = head.random.next;
                    next.random = random;
                }
            } else {
                next.random = null;
            }

            head = head.next.next;
        }

        // 3
        head = nodeHead;
        nodeHead = nodeHead.next;
        Node result = nodeHead;
        while(head != null){
            Node next = head.next.next;
            if(result == null){
                result = head.next;
            } else {
                result.next = head.next;
            }

            head.next = next;
            head = next;
        }

        return nodeHead;
    }
}
