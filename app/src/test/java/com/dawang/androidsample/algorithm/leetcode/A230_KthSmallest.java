package com.dawang.androidsample.algorithm.leetcode;

import com.dawang.androidsample.algorithm.base.TreeNode;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class A230_KthSmallest {

    private int count = 0;

    @Test
    public void test(){
    }

    public int kthSmallest(TreeNode root, int k) {

        List<Integer> list = new ArrayList<>();
        kthSmallest(root, k, list);

        return list.get(k-1);
    }


    public boolean kthSmallest(TreeNode root, int k, List<Integer> result) {
        if(root == null) return false;

        if(root.left != null && kthSmallest(root.left, k, result)){
            return true;
        }

        result.add(root.val);
        if(result.size() == k) return true;

        if(root.right != null && kthSmallest(root.right, k, result)){
            return true;
        }

        return false;
    }
