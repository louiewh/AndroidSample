package com.dawang.androidsample.algorithm;

import com.dawang.androidsample.algorithm.base.TreeNode;

import org.junit.Test;

public class DepthOfBinTree {


    @Test
    public void test(){
        TreeNode root = new TreeNode(10);

        root.left = new TreeNode(6);
        root.right = new TreeNode(15);

        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(7);

        root.right.left = new TreeNode(11);
        root.right.right = new TreeNode(16);

        int result = maxDepthOfTree(root);

        System.err.print(result);
    }

    int maxDepthOfTree(TreeNode root){

        if(root == null) return  0;

        int leftDept = maxDepthOfTree(root.left);
        int rightDept = maxDepthOfTree(root.right);

        return leftDept > rightDept ? (leftDept+1):(rightDept+1);
    }
}
