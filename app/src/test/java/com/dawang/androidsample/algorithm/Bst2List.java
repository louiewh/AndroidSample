package com.dawang.androidsample.algorithm;


import com.dawang.androidsample.algorithm.base.TreeNode;

import org.junit.Test;


public class Bst2List {

    @Test
    public void test(){
        TreeNode root = new TreeNode(10);

        root.mLeft = new TreeNode(6);
        root.mRight = new TreeNode(15);

        root.mLeft.mLeft = new TreeNode(5);
        root.mLeft.mRight = new TreeNode(7);

        root.mRight.mLeft = new TreeNode(11);
        root.mRight.mRight = new TreeNode(16);

        TreeNode result = transfer(root);

        System.err.print(result);
    }


    TreeNode transfer(TreeNode root){
        if(root == null) return null;

        if(root.mLeft != null){

            TreeNode left = transfer(root.mLeft);
            while (left.mRight != null) left = left.mRight;

            left.mRight = root;
            root.mLeft = left;

        }

        if(root.mRight != null){
            TreeNode right = transfer(root.mRight);

            root.mRight = right;
            right.mLeft = root;
        }

        while (root.mLeft != null) root = root.mLeft;

        return  root;
    }
}
