package com.dawang.androidsample.algorithm;


import com.dawang.androidsample.algorithm.base.TreeNode;

import org.junit.Test;


public class Bst2List {

    @Test
    public void test(){
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(6);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);

        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(16);

        TreeNode result = transfer(root);

        System.err.print(result);
    }


    TreeNode transfer(TreeNode root){
        if(root == null) return null;

        if(root.left != null){

            TreeNode left = transfer(root.left);
            while (left.right != null) left = left.right;

            left.right = root;
            root.left = left;

        }

        if(root.right != null){
            TreeNode right = transfer(root.right);

            root.right = right;
            right.left = root;
        }

        while (root.left != null) root = root.left;

        return  root;
    }
}
