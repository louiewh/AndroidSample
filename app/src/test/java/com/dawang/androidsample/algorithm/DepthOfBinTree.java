package com.dawang.androidsample.algorithm;

import com.dawang.androidsample.algorithm.base.TreeNode;

import org.junit.Test;

public class DepthOfBinTree {


    @Test
    public void test(){
        TreeNode root = new TreeNode(10);

        root.mLeft = new TreeNode(6);
        root.mRight = new TreeNode(15);

        root.mLeft.mLeft = new TreeNode(5);
        root.mLeft.mRight = new TreeNode(7);

        root.mRight.mLeft = new TreeNode(11);
        root.mRight.mRight = new TreeNode(16);

        int result = maxDepthOfTree(root);

        System.err.print(result);
    }

    int maxDepthOfTree(TreeNode root){

        if(root == null) return  0;

        int leftDept = maxDepthOfTree(root.mLeft);
        int rightDept = maxDepthOfTree(root.mRight);

        return leftDept > rightDept ? (leftDept+1):(rightDept+1);
    }
}
